package com.alex.hoo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.hoo.db.repository.UserRepository
import com.alex.hoo.viewmodel.MeModel

class MeModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MeModel(repository) as T
    }
}