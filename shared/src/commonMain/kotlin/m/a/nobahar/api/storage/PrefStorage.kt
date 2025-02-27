package m.a.nobahar.api.storage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import m.a.nobahar.domain.storage.LocalStorage
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class PrefStorage(private val settings: Settings) : LocalStorage {

    override suspend fun <T : Any> getData(key: String, clazz: KClass<T>, defaultValue: T?): T? {
        return withContext(Dispatchers.Default) {
            getData(key, defaultValue, clazz)
        }
    }

    private fun <T : Any> getData(key: String, defaultValue: T?, clazz: KClass<T>): T? {
        if (!settings.contains(key)) return defaultValue
        return when (clazz) {
            Int::class ->
                settings.getInt(key, (defaultValue ?: 0) as Int) as T

            Long::class ->
                settings.getLong(key, (defaultValue ?: 0L) as Long) as T

            String::class ->
                settings.getString(key, (defaultValue as String?).orEmpty()) as T?

            Boolean::class ->
                settings.getBoolean(key, (defaultValue ?: false) as Boolean) as T

            else -> throw IllegalArgumentException()
        }
    }

    override suspend fun <T : Any> setData(key: String, clazz: KClass<T>, value: T?) {
        withContext(Dispatchers.Default) {
            updateData(value, key, clazz)
        }
    }

    private fun <T : Any> updateData(value: T?, key: String, clazz: KClass<T>) {
        if (value == null) {
            settings.remove(key)
        } else {
            when (clazz) {
                Int::class -> settings.putInt(key, value as Int)
                String::class -> settings.putString(key, value as String)
                Long::class -> settings.putLong(key, value as Long) as T
                Boolean::class -> settings.putBoolean(key, value as Boolean)
                else -> throw IllegalArgumentException()
            }
        }
    }
}