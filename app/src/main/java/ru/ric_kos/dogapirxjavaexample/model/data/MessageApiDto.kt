package ru.ric_kos.dogapirxjavaexample.model.data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class MessageApiDto(
    @SerializedName("message")
    @Expose
    val message: String,
    @SerializedName("status")
    @Expose
    val status: String
)
