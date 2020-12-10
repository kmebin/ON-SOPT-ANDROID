package org.kmebin.Seminar27th.data

data class ResponseDummyData(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<DummyData>,
    val support: Support
){
    data class DummyData(
        val id: Int,
        val email: String,
        val first_name: String,
        val last_name: String,
        val avatar: String
    )

    data class Support(
        val url: String,
        val text: String
    )
}