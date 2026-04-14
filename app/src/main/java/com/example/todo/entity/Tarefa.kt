package com.example.todo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tarefa (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val conteudo: String,
    val concluida: Boolean = false
)