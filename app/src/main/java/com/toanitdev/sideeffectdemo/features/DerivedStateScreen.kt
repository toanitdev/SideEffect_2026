package com.toanitdev.sideeffectdemo.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun DerivedStateScreen() {

  val checkedList = remember { mutableStateListOf(false, false, false) }
  val buttonCheckOutState = remember {
    derivedStateOf {
      checkedList.count { it } >= 2
    }
  }

  Scaffold {
    Column(Modifier.padding(it).padding(16.dp)) {
      Text("Chọn ít nhất 2 item để hiển thị nút xác nhận thanh toán")


      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Checkbox(checked = checkedList[0], onCheckedChange = { isChecked ->
            checkedList[0] = isChecked
          })
          Text("Cơm")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          Checkbox(checked = checkedList[1], onCheckedChange = { isChecked ->
            checkedList[1] = isChecked
          })
          Text("Canh")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          Checkbox(checked = checkedList[2], onCheckedChange = { isChecked ->
            checkedList[2] = isChecked
          })
          Text("Nước uống")
        }


        Spacer(Modifier.height(16.dp))
        Button(enabled = buttonCheckOutState.value, onClick = {

        }) {
          Text("Checkout")
        }
      }

    }
  }
}
