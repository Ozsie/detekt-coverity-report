package com.github.ozsie.detekt
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.OutputReport
import java.io.File

class CoverityReport : OutputReport() {

    override val ending: String = "json"

    override fun render(detektion: Detektion): String? {
        val sources = HashSet<Source>()
        val issues = ArrayList<Issue>()

        detektion.findings.forEach { _, findings ->
            findings.forEach {
                val f = File(it.location.file)
                sources.add(Source(file = f.absolutePath))
                val events = ArrayList<Event>()
                events.add(Event(tag = it.id, description = it.issue.description,
                        file = f.absolutePath, line = it.location.source.line))
                issues.add(Issue(checker = it.id, extra = it.id + "_var", file = f.absolutePath,
                        subcategory = it.issue.severity.name, events = events))
            }
        }

        return Report(sources = sources, issues = issues).toJson()
    }
}

fun Report.toJson(): String {
    return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(Report::class.java)
            .toJson(this)
}
