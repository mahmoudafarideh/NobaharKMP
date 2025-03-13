package m.a.nobahar.api.helper

import kotlinx.serialization.DeserializationStrategy
import nl.adaptivity.xmlutil.serialization.XML
import org.w3c.dom.parsing.DOMParser

actual fun <T> parseXML(deserializer: DeserializationStrategy<T>, string: String): T {
    val parser = DOMParser()
    val doc = parser.parseFromString(string, "application/xml")
    return // TODO:  
}