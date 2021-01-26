package com.alex.hoo.viewmodel

import androidx.lifecycle.*
import com.alex.hoo.db.data.Shoe
import com.alex.hoo.db.repository.ShoeRepository

class FavouriteModel constructor(shoeRepository: ShoeRepository, userId:Long) : ViewModel() {

    // 鞋子集合的观察类
    val shoes: LiveData<List<Shoe>> = shoeRepository.getShoesByUserId(userId)

}