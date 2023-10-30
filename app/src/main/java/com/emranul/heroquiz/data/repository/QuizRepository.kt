package com.emranul.heroquiz.data.repository

import com.emranul.heroquiz.data.model.ResponseQuizList
import com.emranul.heroquiz.data.network.Api
import com.emranul.heroquiz.data.network.Resource
import javax.inject.Inject

class QuizRepository @Inject constructor(private var api: Api) {
    suspend fun quizList():Resource<ResponseQuizList> {
        val response = try {
            api.quizList();
        } catch (e: Exception) {
            return  Resource.Error("Error ${e.message}")
        }
        return  Resource.Success(response)
    }
}