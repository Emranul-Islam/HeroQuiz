package com.emranul.heroquiz.data.model


import com.google.gson.annotations.SerializedName

data class Answers(
    @SerializedName("A")
    val a: String?,
    @SerializedName("B")
    val b: String?,
    @SerializedName("C")
    val c: String?,
    @SerializedName("D")
    val d: String?
)