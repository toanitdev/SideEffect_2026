package com.toanitdev.sideeffectdemo.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun LaunchEffectScreen() {
  var textState1 by remember { mutableStateOf("") }
  var textState2 by remember { mutableStateOf("") }
  var countState by remember { mutableStateOf(0) }


  LaunchedEffect(Unit) {
    delay(1000)
    textState1 = Random.nextInt().toString()

  }

  LaunchedEffect(countState) {
    delay(1000)
    textState2 = "${countState} item"
  }

  Scaffold {
    Column(modifier = Modifier.padding(it)) {
      Text("Launch Effect Screen", fontSize = 21.sp)
      Spacer(modifier = Modifier.height(16.dp))

      Text("Chạy một lần khi composable được khởi tạo: ${textState1}")
      Text("Count state: ${countState}")
      Button({
        countState++
      }) {
        Text("Increase count state")
      }
      Text("Chạy count state phụ thuộc thay đổi: ${textState2}")
    }
  }
}


@Preview
@Composable
fun LaunchEffectScreenPreview() {
    LaunchEffectScreen()
}