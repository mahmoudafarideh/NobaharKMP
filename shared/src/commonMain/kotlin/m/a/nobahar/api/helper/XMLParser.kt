package m.a.nobahar.api.helper

import kotlinx.serialization.DeserializationStrategy

expect fun <T> parseXML(deserializer: DeserializationStrategy<T>, string: String): T