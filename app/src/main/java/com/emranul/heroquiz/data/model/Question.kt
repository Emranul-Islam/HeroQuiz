package com.emranul.heroquiz.data.model


import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("question")
    val question: String?,
    @SerializedName("answers")
    val answers: Answers?,
    @SerializedName("questionImageUrl")
    val questionImageUrl: String?,
    @SerializedName("correctAnswer")
    val correctAnswer: String?,
    @SerializedName("score")
    val score: Int?
)