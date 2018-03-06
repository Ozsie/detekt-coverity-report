package com.github.ozsie.detekt.model

data class Properties(
    val type: String,
    val category: String,
    val impact: String,
    val cwe: Int?,
    val longDescription: String,
    val localEffect: String,
    val issueKind: String
)