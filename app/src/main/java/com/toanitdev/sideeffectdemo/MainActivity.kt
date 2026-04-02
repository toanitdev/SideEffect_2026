package com.toanitdev.sideeffectdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toanitdev.sideeffectdemo.features.DerivedStateScreen
import com.toanitdev.sideeffectdemo.features.FlowScreen
import com.toanitdev.sideeffectdemo.features.LaunchEffectScreen
import com.toanitdev.sideeffectdemo.features.MainScreen
import com.toanitdev.sideeffectdemo.features.ProductStateScreen
import com.toanitdev.sideeffectdemo.features.RecomposeScreen
import com.toanitdev.sideeffectdemo.features.RememberUpdateStateScreen
import com.toanitdev.sideeffectdemo.features.ScopeFunctionScreen
import com.toanitdev.sideeffectdemo.features.SideEffectScreen
import com.toanitdev.sideeffectdemo.features.SnapshotFlowScreen
import com.toanitdev.sideeffectdemo.ui.theme.SideEffectDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()



    setContent {
      SideEffectDemoTheme {
        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(lifecycleOwner) {
          val observer = LifecycleEventObserver { _, event ->
            Log.d("ComposeActivityLifecycle", "Event: $event")
          }

          lifecycleOwner.lifecycle.addObserver(observer)

          onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
          }
        }

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "main_screen") {

          composable("main_screen") {
            MainScreen(navController)
          }

          composable("launch_effect_screen") {
            LaunchEffectScreen()
          }

          composable("side_effect_screen") {
            SideEffectScreen()
          }

          composable("recompose_screen") {
            RecomposeScreen()
          }
          composable("derived_state_screen") {
            DerivedStateScreen()
          }
          composable("remember_update_state_screen") {
            RememberUpdateStateScreen()
          }
          composable("snapshot_flow_screen") {
            SnapshotFlowScreen()
          }
          composable("product_state_screen") {
            ProductStateScreen()
          }
          composable("scope_function_screen") {
            ScopeFunctionScreen()
//            LaunchedEffect(Unit) {
//
//              val intent = Intent(this@MainActivity, NewActivity::class.java)
//              startActivity(intent)
//            }
          }
          composable("flow_screen") {
            FlowScreen()
          }
        }
      }
    }
  }
}
