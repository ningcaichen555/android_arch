package com.example.android_arch

/**
 * @author caichen
 * @Description TODO
 * @createTime 2020年10月21日 11:28:00
 */
data class ArticleEntity(
    val code: Int,
    val `data`: List<Data>,
    val flexiPageDto: FlexiPageDto,
    val message: String
)

data class Data(
    val articleId: String,
    val author: String,
    val bake1: Any,
    val bake2: Any,
    val commentCount: Int,
    val content: String,
    val createTime: Long,
    val likeCount: Int,
    val originUrl: String,
    val pubTime: Long,
    val subjects: String,
    val title: String,
    val type: Any,
    val updateTime: Long,
    val viewCount: Int,
    val wordCount: Int
)

data class FlexiPageDto(
    val page: Int,
    val rowCount: Int,
    val rp: Int,
    val totalPage: Int
)