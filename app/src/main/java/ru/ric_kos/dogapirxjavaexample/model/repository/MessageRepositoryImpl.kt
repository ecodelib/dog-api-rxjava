package ru.ric_kos.dogapirxjavaexample.model.repository

import ru.ric_kos.dogapirxjavaexample.model.data.MessageApiService
import io.reactivex.Observable
import ru.ric_kos.dogapirxjavaexample.model.domain.Message

class MessageRepositoryImpl (private val messageApiService : MessageApiService) : MessageRepository {
    override fun getMessage(): Observable<Message> {
        return messageApiService.getMessage().map{ it.toDomain()}
    }
}