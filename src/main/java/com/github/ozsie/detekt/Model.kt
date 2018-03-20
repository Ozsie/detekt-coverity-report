package com.github.ozsie.detekt

data class Report(val header: Header = Header(), val sources: Set<Source>, val issues: List<Issue>)

data class Header(val version: Int = 1, val format: String = "cov-import-results input")

data class Source(val file: String, val encoding: String? = null)

data class Issue(var checker: String, var extra: String, var file: String, var function: String? = null,
                 var subcategory: String, val properties: Properties? = null, val events: List<Event>)

data class Properties(val type: String, val category: String, val impact: String, val cwe: Int? = null,
                      val longDescription: String, val localEffect: String, val issueKind: String)

data class Event(val tag: String, val description: String, val file: String? = null, val linkUrl: String? = null,
        val linkText: String? = null, val line: Int, val main: Boolean? = null)
