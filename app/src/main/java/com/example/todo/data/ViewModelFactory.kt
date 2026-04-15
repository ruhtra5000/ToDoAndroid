package com.example.todo.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.dao.TarefaDao

class ViewModelFactory(
    private val dao: TarefaDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelGeral(dao) as T
    }
}