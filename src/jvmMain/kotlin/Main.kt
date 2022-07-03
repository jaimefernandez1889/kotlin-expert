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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("") }
    /*val buildMessage = fun (text: String): String {
        return if(text.isNotEmpty()) "Hello $text" else ""
    }
    val message = buildMessage(text)*/
    val message = when {
        text.isNotEmpty() -> "Hello $text"
        else -> { "" }
    }
    val buttonState = message.isNotEmpty()

    MaterialTheme {
        Column {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text(text = "Name")},
                placeholder = { Text(text = "Write your name here")}
            )
            Text(text = message)
            Button(onClick = { text = "" }, enabled = buttonState) {
                Text("Clean")
            }
        }
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "My Notes Application") {
        App()
    }
}