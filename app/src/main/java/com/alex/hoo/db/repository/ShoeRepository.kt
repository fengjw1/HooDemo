package com.alex.hoo.db.repository

import com.alex.hoo.db.dao.ShoeDao
import com.alex.hoo.db.data.Shoe

class ShoeRepository private constructor(private val shoeDao: ShoeDao){

    /**
     * 通过id的范围寻找鞋子
     */
    fun getPageShoes(startIndex: Long, endIndex: Long): List<Shoe> =
        shoeDao.findShoesByIndexRange(startIndex, endIndex)

    /**
     * 获得全部的鞋子
     */
    fun getAllShoes() = shoeDao.getAllShoesLD()

    /**
     * 通过品牌寻找鞋子
     */
    fun getShoesByBrand(brand: Array<String>) = shoeDao.findShoesByBrandLD(brand)

    /**
     * 通过id寻找鞋子
     */
    fun getShoeById(id: Long) = shoeDao.findShoeByIdLD(id)

    /**
     * 通过用户id寻找鞋子
     */
    fun getShoesByUserId(userId: Long) = shoeDao.findShoesByUserId(userId)

    /**
     * 增加鞋子
     */
    fun insertShoes(shoes: List<Shoe>) = shoeDao.insertShoes(shoes)

    companion object{
        @Volatile
        private var instance: ShoeRepository? = null

        fun getInstance(shoeDao: ShoeDao): ShoeRepository =
            instance?: synchronized(this){
                instance?: ShoeRepository(shoeDao).also {
                    instance = it
                }
            }
    }

}