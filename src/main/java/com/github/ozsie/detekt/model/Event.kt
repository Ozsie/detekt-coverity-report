package com.github.ozsie.detekt.model

data class Event(
    val tag: String,
    val description: String,
    val file: String?,
    val linkUrl: String?,
    val linkText: String?,
    val line: Int,
    val main: Boolean?
)
