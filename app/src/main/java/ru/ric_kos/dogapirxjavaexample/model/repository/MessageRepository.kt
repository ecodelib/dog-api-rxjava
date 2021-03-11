package ru.ric_kos.dogapirxjavaexample.model.repository
import io.reactivex.Observable
import ru.ric_kos.dogapirxjavaexample.model.domain.Message

interface MessageRepository {
fun getMessage() : Observable<Message>
}