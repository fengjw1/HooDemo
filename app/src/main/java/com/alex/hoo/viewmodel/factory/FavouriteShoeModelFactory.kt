package com.alex.hoo.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.hoo.db.repository.FavouriteShoeRepository
import com.alex.hoo.db.repository.ShoeRepository
import com.alex.hoo.viewmodel.DetailModel

class FavouriteShoeModelFactory(
    private val shoeRepository: ShoeRepository
    , private val favouriteShoeRepository: FavouriteShoeRepository
    , private val shoeId: Long
    , private val userId: Long
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailModel(shoeRepository, favouriteShoeRepository, shoeId, userId) as T
    }
}