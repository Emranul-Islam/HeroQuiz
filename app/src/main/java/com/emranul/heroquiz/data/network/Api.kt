package com.emranul.heroquiz.data.network

import com.emranul.heroquiz.data.model.ResponseQuizList
import retrofit2.Response
import retrofit2.http.GET

interface Api {


    @GET("quiz.json")
    suspend fun quizList():ResponseQuizList


}