package com.ncs.onetask.retrofit

import com.ncs.onetask.models.Result
import com.ncs.onetask.models.TodoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoApi {
    @GET("get.php")
    suspend fun getTodo() : Response<List<TodoItem>>
    @POST("add.php")
    suspend fun addTodo(@Query("todo")todo:String,
                        @Query("isChecked")isChecked:Int) : Response<Result>


}