package com.alki.aplkication.presentation

import com.alki.aplkication.data.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val notes = repository.notes

    fun addNote(title: String) {
        scope.launch {
            repository.addNote(title)
        }
    }

    fun deleteNote(id: Long) {
        scope.launch {
            repository.deleteNote(id)
        }
    }

    fun onCleared() {
        scope.cancel()
    }
}
