package m.a.nobahar.api.helper

import kotlinx.serialization.DeserializationStrategy
import org.w3c.dom.Node
import org.w3c.dom.get
import org.w3c.dom.parsing.DOMParser
import kotlin.js.Json
import kotlin.js.json

actual fun <T> parseXML(deserializer: DeserializationStrategy<T>, string: String): T {
    val parser = DOMParser()
    val doc = parser.parseFromString(string, "application/xml")
    val jsonString = doc.documentElement!!.nodeToJson().jsonToString()
    val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
    }
    return json.decodeFromString(deserializer, jsonString)
}

fun Node.nodeToJson(): Json {
    val obj = mutableMapOf<String, Any>()

    val children = childNodes
    for (i in 0 until children.length) {
        val child = children[i]!!
        if (child.nodeType == Node.ELEMENT_NODE) {
            val key = child.nodeName
            val value: Any =
                if (child.childNodes.length == 1 && child.firstChild?.nodeType == Node.TEXT_NODE) {
                    val textContent = child.textContent ?: ""
                    textContent.toIntOrNull() ?: textContent // Convert to Int if possible
                } else {
                    child.nodeToJson()
                }

            if (obj.containsKey(key)) {
                // Handle multiple elements with the same name as an array
                val existing = obj[key]
                if (existing is MutableList<*>) {
                    (existing as MutableList<Any>).add(value)
                } else {
                    obj[key] = mutableListOf(existing, value)
                }
            } else {
                obj[key] = value
            }
        }
    }
    return json(*obj.toList().toTypedArray())
}

private fun Json.jsonToString(): String {
    return JSON.stringify(this, null, 4) // Pretty print with 4 spaces
}