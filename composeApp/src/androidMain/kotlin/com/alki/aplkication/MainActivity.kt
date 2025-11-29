package com.alki.aplkication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.alki.aplkication.room.RoomNotesRepository
import com.alki.aplkication.ui.NotesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContext = applicationContext
        setContent {
            val repository = remember { RoomNotesRepository(appContext) }
            NotesScreen(repository)
        }
    }
}

@Composable
private fun NotesScreen(repository: RoomNotesRepository) {
    Surface(color = MaterialTheme.colorScheme.background) {
        NotesApp(repository = repository)
    }
}
