package com.example.todo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.entity.Tarefa
import kotlinx.coroutines.flow.Flow

//Comunicação com o Banco (Data Access Object)
@Dao
interface TarefaDao {

    @Insert
    suspend fun inserir (tarefa: Tarefa)

    @Update
    suspend fun atualizar (tarefa: Tarefa)

    @Delete
    suspend fun remover (tarefa: Tarefa)

    @Query("SELECT * FROM Tarefa ORDER BY id DESC")
    fun listar (): Flow<List<Tarefa>>

    @Query("SELECT * FROM Tarefa WHERE id = :id")
    fun buscarPorId (id: Int): Flow<Tarefa?>
}