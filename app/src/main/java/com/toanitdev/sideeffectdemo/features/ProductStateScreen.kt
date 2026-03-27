package com.toanitdev.sideeffectdemo.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ProductStateScreen() {

  val result = produceState("Loading...") {
    value = fetchProductFromNetwork()
  }

  Scaffold() {
    Column(Modifier.padding(it)) {
      Text("Value: ${result.value}")
    }
  }
}


suspend fun fetchProductFromNetwork(): String {
  //Giả lập delay khi fetch data từ network
  kotlinx.coroutines.delay(3000)
  return "Product from network"
}