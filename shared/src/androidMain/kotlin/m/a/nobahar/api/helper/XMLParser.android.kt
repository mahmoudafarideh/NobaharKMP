package m.a.nobahar.api.helper

import kotlinx.serialization.DeserializationStrategy

actual fun <T> parseXML(
    deserializer: DeserializationStrategy<T>,
    string: String
): T {
    TODO("Not yet implemented")
}