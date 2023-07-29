package com.ncs.onetask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ncs.onetask.models.TodoItem
import com.ncs.onetask.retrofit.TodoApi
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoApi: TodoApi) {
    private val todoLiveData= MutableLiveData<List<TodoItem>>()
    val todo: LiveData<List<TodoItem>>
        get() = todoLiveData


    suspend fun getTodo(){

        val result=todoApi.getTodo()
        if(result.isSuccessful && result.body() != null) {
            todoLiveData.postValue(result.body()!!)
        }
    }
}