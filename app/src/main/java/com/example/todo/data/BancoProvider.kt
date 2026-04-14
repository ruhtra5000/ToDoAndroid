package com.example.todo.data

import android.content.Context
import androidx.room.Room

object BancoProvider {
    @Volatile
    private var INSTANCIA: ToDoBD? = null

    fun getBanco(context: Context): ToDoBD {
        return INSTANCIA ?: synchronized(this) {
            val instancia = Room.databaseBuilder(
                context = context.applicationContext,
                klass = ToDoBD::class.java,
                name = "todo-db"
            ).build()

            INSTANCIA = instancia
            instancia
        }
    }
}