package com.ncs.onetask.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncs.onetask.models.TodoItem
import com.ncs.onetask.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {


    val todoLiveData : LiveData<List<TodoItem>>
        get() = repository.todo
    var items= emptyList<TodoItem>()
    init {
        viewModelScope.launch{
            repository.getTodo()
        }
        returnTODO()
    }
    fun returnTODO(){
        todoLiveData.observeForever { quotes->
            items=quotes
        }

    }
}