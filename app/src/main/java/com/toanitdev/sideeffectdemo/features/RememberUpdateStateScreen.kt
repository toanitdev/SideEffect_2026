package com.toanitdev.sideeffectdemo.features

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Preview
@Composable
fun RememberUpdateStateScreen() {
  var countState = remember{
    mutableStateOf(0)
  }
  var count2 = 0
  Scaffold {
    Column(Modifier.padding(it).padding(16.dp)) {
      Text("Remember update state screen")

      Text("Count : ${countState.value}")

      Button({
        countState.value++
        count2++
      }) {
        Text("Increase count")
      }

      // ❗ capture giá trị tại thời điểm compose
      val capturedCount = countState.value

      // nếu trong lambda có sử dụng countState.value thì nó sẽ capture giá trị tại thời điểm lambda được gọi, chứ không phải tại thời điểm compose. Điều này có nghĩa là nếu countState.value thay đổi sau khi lambda được tạo ra, lambda sẽ sử dụng giá trị mới của countState.value khi nó được gọi, chứ không phải giá trị cũ đã capture tại thời điểm compose.
      // nhưng nếu dùng giá trị trường thì nó sẽ capture giá trị tại thời điểm compose, nên khi countState.value thay đổi thì capturedCount vẫn giữ nguyên giá trị cũ đã capture tại thời điểm compose, chứ không bị ảnh hưởng bởi sự thay đổi của countState.value sau đó.
      ShowCountSection(text = capturedCount, onShow = {

        Log.d("ToanLog", "Count is ${capturedCount}")
        Log.d("ToanLog", "Count is ${countState.value}")
        Log.d("ToanLog", "--------------------")
      })
    }
  }
}

@Composable
fun ShowCountSection(text: Int, onShow: () -> Unit) {
  val currentOnShow by rememberUpdatedState(onShow)
  LaunchedEffect(Unit) {
    delay(5000)
    currentOnShow()
    onShow()
  }
  Text("Count section: $text")
}
