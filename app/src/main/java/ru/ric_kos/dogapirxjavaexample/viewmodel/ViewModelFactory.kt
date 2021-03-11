package ru.ric_kos.dogapirxjavaexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ric_kos.dogapirxjavaexample.model.repository.FactoryMessageRepo

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(MessageViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return MessageViewModel(
                    FactoryMessageRepo.getMessageRepo()
                ) as T
            }

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
