package ru.ric_kos.dogapirxjavaexample.model.repository

import ru.ric_kos.dogapirxjavaexample.model.data.MessageApiService
import ru.ric_kos.dogapirxjavaexample.model.data.RetrofitClient

object FactoryMessageRepo {
private var messageRepoImpl : MessageRepository? = null
fun getMessageRepo() : MessageRepository {
if (messageRepoImpl == null){
    messageRepoImpl = MessageRepositoryImpl(messageApiService = RetrofitClient.getService(
        MessageApiService::class.java))
}
    return  messageRepoImpl as MessageRepository
}
}