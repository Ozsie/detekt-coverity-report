package com.github.ozsie.detekt.model

data class Issue(
        var checker: String,
        var extra: String,
        var file: String,
        var function: String?,
        var subcategory: String,
        val properties: Properties?,
        val events: List<Event>
)