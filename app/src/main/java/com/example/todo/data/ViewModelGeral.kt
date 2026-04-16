package com.example.todo.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.dao.TarefaDao
import com.example.todo.entity.Tarefa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewModelGeral (private val dao: TarefaDao) : ViewModel() {
    val tarefas = dao.listar().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun tarefaPorId(id: Int): Flow<Tarefa> {
        return dao.buscarPorId(id).filterNotNull()
    }

    fun adicionar (conteudo: String) {
        viewModelScope.launch {
            dao.inserir(Tarefa(conteudo = conteudo))
        }
    }

    fun editar (tarefa: Tarefa, novoConteudo: String) {
        viewModelScope.launch {
            dao.atualizar(tarefa.copy(conteudo = novoConteudo))
        }
    }

    fun concluir (tarefa: Tarefa) {
        viewModelScope.launch {
            dao.atualizar(tarefa.copy(concluida = !tarefa.concluida))
        }
    }

    fun deletar (tarefa: Tarefa) {
        viewModelScope.launch {
            dao.remover(tarefa)
        }
    }
}