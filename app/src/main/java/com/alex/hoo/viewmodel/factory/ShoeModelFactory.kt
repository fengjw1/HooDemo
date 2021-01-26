package com.alex.hoo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.hoo.db.repository.ShoeRepository
import com.alex.hoo.viewmodel.ShoeModel

class ShoeModelFactory(
    private val repository: ShoeRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoeModel(repository) as T
    }
}