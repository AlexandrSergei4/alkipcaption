package com.alki.aplkication.data

import kotlinx.coroutines.flow.StateFlow

interface NotesRepository {
    val notes: StateFlow<List<Note>>

    suspend fun addNote(title: String)

    suspend fun deleteNote(id: Long)
}
