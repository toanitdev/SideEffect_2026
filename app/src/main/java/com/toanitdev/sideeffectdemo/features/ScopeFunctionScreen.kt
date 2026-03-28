package com.toanitdev.sideeffectdemo.features

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
fun ScopeFunctionScreen() {



  Scaffold {
    Column(modifier = Modifier.padding(it)) {
      Button({
        scopeFunction()
      }) {
        Text("Run")
      }
    }
  }
}


fun scopeFunction() {
  val list = listOf(1, 2, 3, 4, 5)
  val string: String  = "hello world"
  val optionalString: String?  = null
  val movieA: Movie = Movie(1, "Inception", "A mind-bending thriller", 8.8f)
  val movieB: Movie? = Movie(2, "Interstellar", "A space exploration epic", 8.6f)
  println("=== Let ===")
  // let
  optionalString?.let {
    println("Optional string is not null: $it")
  }

  println("=== Also ===")
  // also
  val mA = movieA.also {
    it.title = "Inception (2010)"
    println("Movie title: ${it.title}")
    println("Movie hash: ${it.hashCode()}")
    // giá trị bên trong có thể thay đổi
    // trả về đối tượng ban đầu cùng kiểu. không giống run có thê trả về bất kỳ kiểu nào
  }
  println("Movie hash after also: ${mA.hashCode()}")


  println("=== Run ===")

  // có thể dùng với Safe call operator
  val result = mA.run {
    println("Movie title in run: $title")
    println("Movie description in run: $description")
    println("Movie vote average in run: $voteAverage")
    description = "Mô Tả đã được thay đổi trong run"
    "${title} - ${description} - ${voteAverage}" // Giá trị trả về của run sẽ là giá trị của expression cuối cùng trong block
  }.also {
    println("Có thể chain thêm các hàm scope function khác sau run, vì run trả về giá trị của expression cuối cùng trong block, nên có thể sử dụng also để thực hiện thêm các thao tác khác trên đối tượng hoặc giá trị đã được trả về từ run.")
  }

  // có thể dùng với Safe call operator
  val result22 = movieB?.run {
    println("Movie title in run: $title")
    println("Movie description in run: $description")
    println("Movie vote average in run: $voteAverage")
    description = "Mô Tả đã được thay đổi trong run"
    "${title} - ${description} - ${voteAverage}" // Giá trị trả về của run sẽ là giá trị của expression cuối cùng trong block
  }.also {
    println("Có thể chain thêm các hàm scope function khác sau run, vì run trả về giá trị của expression cuối cùng trong block, nên có thể sử dụng also để thực hiện thêm các thao tác khác trên đối tượng hoặc giá trị đã được trả về từ run.")
  }
  println("Movie in run: $result")
  println("Movie in run: ${mA.description}")

  println("=== Apply ===")
  mA.apply {
    description = "Đã đổi thông tin mô tả của phim"
    // println(it) // không dùng được
  }
  println("Movie description after apply: ${mA.description}")

  println("=== Apply ===")



  // dùng không chặng được với safety
  val resultMovie = with(mA) {
    description = "Đã đổi thông tin mô tả của phim lần nữa"
    // println(it) // không dùng được
    "this" // Giá trị trả về của with sẽ là đối tượng mà nó đang hoạt động trên, trong trường hợp này là mA
  }.also {
    println("có thể chain thêm các hàm scope function khác sau with, vì with trả về đối tượng mà nó đang hoạt động trên, nên có thể sử dụng also để thực hiện thêm các thao tác khác trên đối tượng đã được trả về từ with.")
  }
// dùng không chặng được với safety
  val resultMovie2 = with(movieB) {
    // rât phức tạp
    this?.description = "Đã đổi thông tin mô tả của phim lần nữa"
    // println(it) // không dùng được
    "this" // Giá trị trả về của with sẽ là đối tượng mà nó đang hoạt động trên, trong trường hợp này là mA
  }.also {
    println("có thể chain thêm các hàm scope function khác sau with, vì with trả về đối tượng mà nó đang hoạt động trên, nên có thể sử dụng also để thực hiện thêm các thao tác khác trên đối tượng đã được trả về từ with.")
  }

  println("result Movie" + resultMovie)
  println("mA: ${mA.description}" )





}

data class Movie(
  var id: Int,
  var title: String,
  var description: String,
  var voteAverage: Float
)



class VM : ViewModel(){
  fun doSomething() {
    viewModelScope.launch(Dispatchers.IO) {
      // Thực hiện công việc bất đồng bộ ở đây
    }
  }
}
