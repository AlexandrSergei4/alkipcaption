package com.alki.aplkication

import com.alki.aplkication.data.Note
import com.alki.aplkication.data.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryNotesRepository : NotesRepository {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    override val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    override suspend fun addNote(title: String) {
        val trimmed = title.trim()
        if (trimmed.isBlank()) return
        val nextId = (_notes.value.maxOfOrNull { it.id } ?: 0) + 1
        val newNote = Note(id = nextId, title = trimmed)
        _notes.update { listOf(newNote) + it }
    }

    override suspend fun deleteNote(id: Long) {
        _notes.update { current -> current.filterNot { it.id == id } }
    }
}
