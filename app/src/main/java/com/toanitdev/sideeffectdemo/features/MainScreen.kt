package com.toanitdev.sideeffectdemo.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Preview
@Composable
fun MainScreen(navController: NavController? = null) {
  Scaffold {
    LazyVerticalGrid(
      columns = GridCells.Fixed(3),
      modifier = Modifier.padding(it),
      verticalArrangement = Arrangement.spacedBy(12.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp),
      contentPadding = PaddingValues(12.dp)
    ) {
      item {
        RectButton({
          navController?.navigate("launch_effect_screen")
        }) {
          Text("Launch Effect", fontSize = 17.sp)
        }
      }
      item {
        RectButton({
          navController?.navigate("side_effect_screen")
        }) {
          Text("Side Effect", fontSize = 17.sp)
        }
      }
      item {
        RectButton({
          navController?.navigate("recompose_screen")
        }) {
          Text("Recompose", fontSize = 15.sp)
        }
      }
      item {
        RectButton({
          navController?.navigate("derived_state_screen")
        }) {
          Text("Derived State", fontSize = 15.sp)
        }
      }
      item {
        RectButton({
          navController?.navigate("remember_update_state_screen")
        }) {
          Text("Remember Update State", fontSize = 15.sp)
        }
      }
      item {
        RectButton({
          navController?.navigate("snapshot_flow_screen")
        }) {
          Text("Snapshot Flow", fontSize = 15.sp)
        }
      }
      item {
        RectButton({
          navController?.navigate("product_state_screen")
        }) {
          Text("Product State", fontSize = 15.sp)
        }
      }
      item {
        RectButton({
          navController?.navigate("scope_function_screen")
        }) {
          Text("Scope Functions", fontSize = 15.sp)
        }
      }
    }
  }
}

@Composable
fun RectButton(onClick: () -> Unit, content: @Composable () -> Unit) {
  Surface(
    shape = RectangleShape, modifier = Modifier
      .aspectRatio(1f)
      .clickable(enabled = true, onClick = onClick),
    color = MaterialTheme.colorScheme.primary
  ) {
    Box(modifier = Modifier.padding(12.dp), contentAlignment = Alignment.Center) {
      content()
    }
  }
}

