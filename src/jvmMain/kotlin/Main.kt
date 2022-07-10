// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.concurrent.thread

class AppState {
    var state = mutableStateOf(UiState())
    data class UiState(val notes: List<Note> = emptyList(), val loading: Boolean = false)

    fun loadNotes() {
        thread {
            state.value = UiState(loading = true)
            getNotes(10) { state.value = UiState(notes = it, loading = false) }
        }
    }
}

@Composable
@Preview
fun App(appstate: AppState) {

    /***
     * JF, para evitar que cada vez que appstate se recomponga por algun cambio de estado
     * vuelva a ejecutar loadNotes, le anidiamos LaunchedEffect. Este ultimo solo se ejecutara
     * cuando su parametro cambie de estado, y como es fijo true, solo se ejecutara una vez
     */
    LaunchedEffect(true){
        appstate.loadNotes()
    }

    MaterialTheme {
        Box(contentAlignment = Alignment.Center){
            if(appstate.state.value.loading){
                CircularProgressIndicator()
            }
            NotesList(appstate.state.value.notes)
        }
    }

}
@Composable
private fun NotesList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) { note ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.8F)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row {
                        Text(
                            text = note.title,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.h6
                        )
                        if (note.type == Note.Type.AUDIO) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Text(text = note.description)
                }
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