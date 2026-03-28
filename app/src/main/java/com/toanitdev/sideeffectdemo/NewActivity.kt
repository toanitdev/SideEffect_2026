package com.toanitdev.sideeffectdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.toanitdev.sideeffectdemo.ui.theme.SideEffectDemoTheme

class NewActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()



    setContent {
      SideEffectDemoTheme {
        Scaffold {
          Column(Modifier.padding(it)) {
            Text("New Activity")

          }
        }
      }
    }
  }
}
