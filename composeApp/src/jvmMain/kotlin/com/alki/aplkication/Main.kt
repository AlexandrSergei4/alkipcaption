package com.alki.aplkication

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.alki.aplkication.ui.NotesApp

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Room KMP Notes") {
        NotesApp(repository = InMemoryNotesRepository())
    }
}
