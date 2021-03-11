package ru.ric_kos.dogapirxjavaexample.model.repository

import ru.ric_kos.dogapirxjavaexample.model.data.MessageApiDto
import ru.ric_kos.dogapirxjavaexample.model.domain.Message

fun MessageApiDto.toDomain() = Message(
    imageUrl = message,
    status = status
)