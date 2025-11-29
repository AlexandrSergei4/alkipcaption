package com.alki.aplkication.room

import android.content.Context
import androidx.room.Room
import com.alki.aplkication.data.Note
import com.alki.aplkication.data.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RoomNotesRepository(context: Context) : NotesRepository {
    private val database = Room.databaseBuilder(
        context.applicationContext,
        NoteDatabase::class.java,
        "notes.db"
    ).fallbackToDestructiveMigration().build()

    private val dao = database.noteDao()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override val notes: StateFlow<List<Note>> = dao.observeNotes()
        .map { entities -> entities.map { it.toNote() } }
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    override suspend fun addNote(title: String) {
        val trimmed = title.trim()
        if (trimmed.isBlank()) return
        dao.insert(NoteEntity(title = trimmed))
    }

    override suspend fun deleteNote(id: Long) {
        dao.delete(id)
    }

    private fun NoteEntity.toNote(): Note = Note(id = id, title = title)
}
