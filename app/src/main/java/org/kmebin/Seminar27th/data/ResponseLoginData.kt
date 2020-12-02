package org.kmebin.Seminar27th.data

data class ResponseLoginData (
    val status : Int,
    val success : Boolean,
    val data : Data,
    val message : String
) {
    data class Data(
        val email : String,
        val password : String,
        val userName : String
    )
}