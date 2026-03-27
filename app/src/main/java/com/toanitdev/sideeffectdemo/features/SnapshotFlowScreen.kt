package com.toanitdev.sideeffectdemo.features

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay


@Preview
@Composable
fun SnapshotFlowScreen() {
  val countState = remember { mutableStateOf(0) }
  val snapshotFlow = snapshotFlow { countState.value }
//Khi nào nên dùng snapshotFlow
//
//Dùng khi bạn muốn biến Compose State thành Flow để theo dõi, ví dụ:
//
//theo dõi listState.firstVisibleItemIndex
//theo dõi text search thay đổi để debounce
//theo dõi một state của UI rồi gọi side effect ngoài Compose
  LaunchedEffect(Unit) {

    snapshotFlow.collect { value ->
      delay(2000)
      Log.d("ToanLog", "Collected value: $value")
    }
  }

  Scaffold {
    Column(modifier = Modifier.padding(it)
      ) {
      Text("Count: ${countState.value}")

      Button({
        countState.value++
      }) {
        Text("Increase count")
      }
    }
  }
}