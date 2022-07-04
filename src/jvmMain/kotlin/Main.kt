// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

class AppState {
    var text by mutableStateOf("")
    val message: String
        get(){
            return if(this.text.isNotEmpty()) "Hello ${this.text}" else ""
        }
    val buttonState: Boolean
        get() = this.text.isNotEmpty()
}

@Composable
@Preview
fun App(state: AppState) {
    MaterialTheme {
        Column {
            TextField(
                value = state.text,
                onValueChange = {
                    state.text = it
                },
                label = { Text(text = "Name")},
                placeholder = { Text(text = "Write your name here")}
            )
            Text(text = state.message)
            Button(onClick = { state.text = "" }, enabled = state.buttonState) {
                Text("Clean")
            }
        }
    }

}

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "My Notes Application") {
            val state = AppState()
            App(state)
        }
    }
}