package com.toanitdev.sideeffectdemo.features

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun RecomposeScreen() {
  // Một composable được recomposition khi có sự thay đổi về state hoặc khi có một sự kiện nào đó xảy ra mà yêu cầu composable phải được vẽ lại.
  // Scope recomposition là phạm vi của composable mà sẽ được recomposition khi có sự thay đổi. Khi một composable được recomposition, tất cả các composable con của nó cũng sẽ được recomposition,
  //  trừ khi chúng sử dụng remember hoặc các cơ chế tối ưu hóa khác để tránh recomposition không cần thiết.

  // Xác định recomposition scope là quan trọng để tối ưu hóa hiệu suất của ứng dụng, vì recomposition có thể tốn tài nguyên nếu không được quản lý đúng cách.
  // 1. Scope recompose được xác định bởi composable function. Khi một composable function được gọi, nó tạo ra một scope recomposition cho tất cả các composable con bên trong nó.
  // 2. Tìm composable gần nhất với nơi dùng state bên trên trong cây composable sẽ là scope recomposition. Điều này có nghĩa là nếu một composable con thay đổi state, chỉ composable đó và các composable con của nó sẽ được recomposition, trong khi các composable khác sẽ không bị ảnh hưởng.
  // 3. inline composable function sẽ không tạo ra scope recomposition riêng biệt. Thay vào đó, chúng sẽ được recomposition cùng với composable cha của chúng. Điều này có nghĩa là nếu một composable inline thay đổi state, tất cả các composable cha của nó cũng sẽ bị recomposition.
  // 4. Muốn tối ưu hoá hiệu xuất nên xác định được scope recomposition để tránh việc recomposition không cần thiết. Nếu một composable có nhiều state và chỉ một phần nhỏ của nó thay đổi, nên tách phần đó ra thành một composable riêng biệt để giảm scope recomposition và cải thiện hiệu suất.

  var count by remember { mutableStateOf(0) }
  var count1 by remember { mutableStateOf(0) }


  Log.d("ToanLog", "ToanLog: Recomposing RecomposeScreen (1)")
  Scaffold {
    // Scaffold là mổi recomposition scope gần nhất nên khi thay đổi nó sẽ recompose lại Scaffold và tất cả các composable con bên trong nó, nhưng không ảnh hưởng đến các composable khác bên ngoài Scaffold. Điều này giúp tối ưu hóa hiệu suất bằng cách giới hạn phạm vi recomposition chỉ trong Scaffold và các composable con của nó, thay vì toàn bộ cây composable.
    Column(Modifier.padding(it)) {
      Log.d("ToanLog", "ToanLog: Recomposing RecomposeScreen(2)")
      // Text là nơi dùng state
      Text("Count(1) $count")
      Button({
        count++
      }) {
        Text("Increase count (1)")
      }
      Surface {
        // Surface là mổi recomposition scope gần nhất nên khi thay đổi nó sẽ recompose lại Surface và tất cả các composable con bên trong nó, nhưng không ảnh hưởng đến các composable khác bên ngoài Surface. Điều này giúp tối ưu hóa hiệu suất bằng cách giới hạn phạm vi recomposition chỉ trong Surface và các composable con của nó, thay vì toàn bộ cây composable.
        Log.d("ToanLog", "ToanLog: Recomposing RecomposeScreen(3)")
        // Column là inline composable function nên nó sẽ không tạo ra scope recomposition riêng biệt. Thay vào đó, nó sẽ được recomposition cùng với composable cha của nó, trong trường hợp này là Surface. Điều này có nghĩa là nếu một composable con bên trong Column thay đổi state, tất cả các composable cha của nó (Surface) cũng sẽ bị recomposition.
        Column {
          // Text là nơi dùng state
          Text("Count(2) $count1")
          Button({
            count1++
          }) {
            Text("Increase count (2)")
          }
        }
      }
    }
  }

}