package com.emranul.heroquiz.data.model


import com.google.gson.annotations.SerializedName

data class ResponseQuizList(
    @SerializedName("questions")
    val questions: List<Question>?
)