package com.alex.hoo.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import com.alex.hoo.db.data.Shoe

/**
 * 鞋子的Dao
 */
@Dao
interface ShoeDao {

    @Query("SELECT * FROM shoe WHERE id between :startIndex AND :endIndex ORDER BY id ASC")
    fun findShoesByIndexRange(startIndex: Long, endIndex: Long) : List<Shoe>

    @Query("SELECT * FROM shoe")
    fun getAllShoesLD(): PagingSource<Int, Shoe>

    @Query("SELECT * FROM shoe WHERE id = :id")
    fun findShoeByIdLD(id: Long): LiveData<Shoe>

    @Query("SELECT * FROM shoe WHERE shoe_brand IN (:brand)")
    fun findShoesByBrandLD(brand: Array<String>): PagingSource<Int, Shoe>

    @Query("SELECT shoe.id,shoe.shoe_name,shoe.shoe_description,shoe.shoe_price,shoe.shoe_brand,shoe.shoe_imgUrl " +
            "FROM shoe " +
            "INNER JOIN fav_shoe ON fav_shoe.shoe_id = shoe.id " +
            "WHERE fav_shoe.user_id = :userId")
    fun findShoesByUserId(userId: Long): LiveData<List<Shoe>>

    // 增加一双鞋子
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe)

    // 增加多双鞋子
    // 除了List之外，也可以使用数组
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoes(shoes: List<Shoe>)

    // 删除一双鞋子
    @Delete
    fun deleteShoe(shoe: Shoe)

    // 删除多个鞋子
    // 参数也可以使用数组
    @Delete
    fun deleteShoes(shoes: List<Shoe>)

    // 更新一双鞋
    @Update
    fun updateShoe(shoe: Shoe)

    // 更新多双鞋
    // 参数也可以是集合
    @Update
    fun updateShoes(shoes: Array<Shoe>)

}