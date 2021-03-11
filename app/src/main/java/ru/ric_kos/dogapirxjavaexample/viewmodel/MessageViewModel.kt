package ru.ric_kos.dogapirxjavaexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.ric_kos.dogapirxjavaexample.model.domain.Message
import ru.ric_kos.dogapirxjavaexample.model.repository.MessageRepository
import ru.ric_kos.dogapirxjavaexample.model.domain.Result
class MessageViewModel(private val messageRepo: MessageRepository) : ViewModel() {
    private var disposable: Disposable? = null
    val answer by lazy {
        MutableLiveData<Result<Message>>()
    }
    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
    fun loadMessage() {
        disposable =
            messageRepo.getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { value -> answer.setValue(Result.Success(value))},
                    { error -> answer.setValue(Result.Failure(error))},
                    { println("Getting answer completed")}
                )
    }
}