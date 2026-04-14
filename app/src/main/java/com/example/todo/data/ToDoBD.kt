package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.dao.TarefaDao
import com.example.todo.entity.Tarefa

@Database(entities = [Tarefa::class], version = 1)
abstract class ToDoBD : RoomDatabase() {
    abstract fun tarefaDao(): TarefaDao
}