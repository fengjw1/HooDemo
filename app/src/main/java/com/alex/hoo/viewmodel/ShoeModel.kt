package com.alex.hoo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.alex.hoo.common.createPager
import com.alex.hoo.db.datasource.CustomPageDataSource
import com.alex.hoo.db.repository.ShoeRepository

class ShoeModel constructor(private val shoeRepository: ShoeRepository) : ViewModel() {

    /**
     * @param config 分页的参数
     * @param pagingSourceFactory 单一数据源的工厂，在闭包中提供一个PageSource即可
     * @param remoteMediator 同时支持网络请求和数据库请求的数据源
     * @param initialKey 初始化使用的key
     */
    var shoes = Pager(config = PagingConfig(
        pageSize = 20
        , enablePlaceholders = false
        , initialLoadSize = 20
    ), pagingSourceFactory = { CustomPageDataSource(shoeRepository) }).flow

    fun setBrand(br: String) {
        if (br == ALL) {
            shoes = Pager(config = PagingConfig(
                pageSize = 20
                , enablePlaceholders = false
                , initialLoadSize = 20
            ), pagingSourceFactory = { CustomPageDataSource(shoeRepository) }).flow
        } else {
            val array: Array<String> =
                when (br) {
                    NIKE -> arrayOf("Nike", "Air Jordan")
                    ADIDAS -> arrayOf("Adidas")
                    else -> arrayOf(
                        "Converse", "UA"
                        , "ANTA"
                    )
                }
            shoes = shoeRepository.getShoesByBrand(array).createPager(20, 20).flow
        }
    }

    companion object {
        const val ALL = "所有"
        const val NIKE = "Nike"
        const val ADIDAS = "Adidas"
        const val OTHER = "other"
    }
}