package com.github.ozsie.detekt.model

data class Report(val header: Header = Header(), val sources: Set<Source>, val issues: List<Issue>)