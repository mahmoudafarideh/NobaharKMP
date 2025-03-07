package m.a.nobahar.api.repository

import m.a.nobahar.api.contract.BookApi
import m.a.nobahar.api.model.toPoetBookInfo
import m.a.nobahar.domain.model.PoetBookInfo
import m.a.nobahar.domain.repository.BookRepository

class BookRepositoryImp(private val bookApi: BookApi) : BookRepository {
    override suspend fun getBook(id: Long): PoetBookInfo {
        return bookApi.getBook(id).toPoetBookInfo()
    }
}