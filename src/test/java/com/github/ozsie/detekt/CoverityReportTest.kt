package com.github.ozsie.detekt

import org.junit.Assert
import org.junit.Test

class CoverityReportTest {

    private val emptyReport = "{\"header\":{\"version\":1,\"format\":\"cov-import-results input\"},\"sources\":[],\"issues\":[]}"

    @Test
    fun toJsonTest() {
        val json = Report(issues = emptyList(), sources = emptySet()).toJson()
        Assert.assertEquals(emptyReport, json)
    }
}