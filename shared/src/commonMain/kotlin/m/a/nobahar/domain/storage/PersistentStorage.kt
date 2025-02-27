package m.a.nobahar.domain.storage

import kotlin.reflect.KClass

interface LocalStorage {
    suspend fun <T : Any> getData(key: String, clazz: KClass<T>, defaultValue: T?): T?
    suspend fun <T : Any> setData(key: String, clazz: KClass<T>, value: T?)
}

inline fun <reified T : Any> LocalStorage.data(key: String, defaultValue: T) =
    LocalStorageDelegate(this, key, T::class, defaultValue)

inline fun <reified T : Any> LocalStorage.optional(key: String, defaultValue: T? = null) =
    LocalStorageDelegate(this, key, T::class, defaultValue)

class LocalStorageDelegate<T : Any>(
    private val localStorage: LocalStorage,
    private val key: String,
    private val clazz: KClass<T>,
    private val defaultValue: T?
) {
    suspend fun getValue() = localStorage.getData(key, clazz, defaultValue)
    suspend fun updateValue(newValue: T) = localStorage.setData(key, clazz, newValue)
}