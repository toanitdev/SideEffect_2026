package com.toanitdev.sideeffectdemo.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Preview
@Composable
fun FlowScreen() {
  var number = remember { mutableStateOf(2) }
  var sharedFlow = remember { MutableSharedFlow<Int>() }
  var stateFlow = remember { MutableStateFlow<Int>(0) }
  var snackbarHostState = remember { SnackbarHostState() }
  LaunchedEffect(Unit) {
    //Collection của sharedFlow sẽ nhận được tất cả giá trị được emit, kể cả giá trị giống nhau, nên nếu bạn muốn luôn nhận được giá trị mới nhất dù nó giống với giá trị cũ thì có thể dùng sharedFlow.emit(value) để emit giá trị mới vào sharedFlow.
    sharedFlow.collect(){
      snackbarHostState.showSnackbar(message = it.toString())
    }
    //Collection của stateFlow sẽ luôn có giá trị mới nhất,
    //Tuy nhiên giá trị mới giống giấ trị cũ thì sẽ không được emit,\
    // nên nếu bạn muốn luôn nhận được giá trị mới nhất dù nó giống với giá trị cũ thì
    // có thể dùng stateFlow.emit(stateFlow.value) để emit lại giá trị hiện tại của stateFlow.
    stateFlow.collect(){
      snackbarHostState.showSnackbar(message = it.toString())
    }
  }

  Scaffold(
    snackbarHost = {
      SnackbarHost(snackbarHostState)
    }
  ) {
    Column(modifier = Modifier.padding(it)) {
      Text("Flow Screen")
      Text("Number: ${number.value}")
      Button({
        number.value = number.value + number.value
        CoroutineScope(Dispatchers.Main).launch {
          sharedFlow.emit(2)
        }
      }) {
        Text("ShareFlow Calculate")
      }
      Text("Number: ${number.value}")
      Button({
        number.value = number.value + number.value
        CoroutineScope(Dispatchers.Main).launch {
          stateFlow.emit(2)
        }
      }) {
        Text("ShareFlow Calculate")
      }
    }
  }
}