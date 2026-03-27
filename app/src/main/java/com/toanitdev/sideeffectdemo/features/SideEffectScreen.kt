package com.toanitdev.sideeffectdemo.features

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun SideEffectScreen() {

  SideEffect {

    // SideEffect sẽ được gọi sau khi composable hoàn thành việc recomposition,
    // bất kể có thay đổi state hay không. Điều này có nghĩa là mỗi lần composable được vẽ lại,
    // SideEffect sẽ được thực thi.
    Log.d("ToanLog", "ToanLog: SideEffectScreen called")
  }
  Scaffold {
    Log.d("ToanLog", "ToanLog: Recomposing SideEffectScreen")
    var textState by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(it)) {
      Text("Launch Effect Screen", fontSize = 21.sp)
      Spacer(modifier = Modifier.height(16.dp))

      Button({
        textState++
      }) {
        Text("Increase count state")
      }
      LabelCompose(textState.toString())
    }
  }
}


@Composable
fun LabelCompose(text: String) {
  SideEffect {
    //Lưu ý: việc sử dụng SideEffect trong một composable con như LabelCompose sẽ khiến SideEffect được gọi mỗi khi
    // composable đó được recomposition, điều này có thể dẫn đến việc thực thi SideEffect nhiều lần
    // nếu composable đó thường xuyên được vẽ lại. Do đó, cần cân nhắc kỹ lưỡng khi sử dụng SideEffect trong các compos
    Log.d("ToanLog", "ToanLog: LabelCompose SideEffect called")
  }
  Column {

    Text("Text Count : ${text}")

  }

  Log.d("ToanLog", "ToanLog: Recomposing LabelCompose with text: $text")
}
