package org.kmebin.Seminar27th.data

data class ResponseKakaoData(
	val meta: Meta,
	val documents: List<Document>
){
	data class Meta(
		val total_count: Int,
		val pageable_count: Int,
		val is_end: Boolean
	)

	data class Document(
		val datetime: String,
		val contents: String,
		val title: String,
		val url: String
	)
}