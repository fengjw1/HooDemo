package com.alex.hoo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.hoo.db.data.FavouriteShoe
import com.alex.hoo.db.data.Shoe
import com.alex.hoo.db.repository.FavouriteShoeRepository
import com.alex.hoo.db.repository.ShoeRepository
import kotlinx.coroutines.launch

class DetailModel constructor(
        shoeRepository: ShoeRepository,
        private val favouriteShoeRepository: FavouriteShoeRepository,
        private val shoeId: Long,
        val userId: Long
) :
    ViewModel() {

    // 鞋
    val shoe: LiveData<Shoe> = shoeRepository.getShoeById(shoeId)

    // 收藏记录
    val favouriteShoe: LiveData<FavouriteShoe?> = favouriteShoeRepository.findFavouriteShoe(userId, shoeId)

    // 收藏一双鞋
    fun favourite() {
        viewModelScope.launch {
            favouriteShoeRepository.createFavouriteShoe(userId,shoeId)
        }
    }
}