package com.github.ozsie.detekt
import com.github.ozsie.detekt.model.Event
import com.github.ozsie.detekt.model.Issue
import com.github.ozsie.detekt.model.Report
import com.github.ozsie.detekt.model.Source
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.OutputReport
import java.io.File

class CoverityReport : OutputReport() {

    override var fileName: String = "coverity"
    override val ending: String = "json"

    override fun render(detektion: Detektion): String? {
        val sources = HashSet<Source>()
        val issues = ArrayList<Issue>()

        detektion.findings.keys.forEach {
            val findings = detektion.findings.get(it)
            findings?.forEach {
                val f = File(it.location.file)
                sources.add(Source(
                        file = f.absolutePath,
                        encoding = null))
                val events = ArrayList<Event>()
                events.add(Event(
                        tag = it.id,
                        description = it.issue.description,
                        file = f.absolutePath,
                        line = it.location.source.line,
                        linkText = null,
                        linkUrl = null,
                        main = null
                ))
                issues.add(Issue(
                        checker = it.id,
                        extra = it.id + "_var",
                        file = f.absolutePath,
                        function = null,
                        subcategory = it.issue.severity.name,
                        properties = null,
                        events = events))
            }
        }

        return toJson(Report(sources = sources, issues = issues))
    }

    fun toJson(report: Report): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val reportAdapter = moshi.adapter(Report::class.java)

        return reportAdapter.toJson(report)
    }

}
