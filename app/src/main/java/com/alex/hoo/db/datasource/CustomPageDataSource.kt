package com.alex.hoo.db.datasource

import androidx.paging.PagingSource
import com.alex.hoo.db.data.Shoe
import com.alex.hoo.db.repository.ShoeRepository
import javax.xml.transform.Source

private const val SHOE_START_INDEX = 0

class CustomPageDataSource (private val shoeRepository: ShoeRepository):
    PagingSource<Int, Source>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Shoe> {
        val pos = params.key?: SHOE_START_INDEX //为空默认是0
        val startIndex = pos*params.loadSize +1
        val endIndex = (pos+1)*params.loadSize
        return try {
            Thread.sleep(5000)
            // 从数据库拉去数据
            val shoes = shoeRepository.getPageShoes(startIndex.toLong(), endIndex.toLong())
            LoadResult.Page(shoes,
                if (pos <= SHOE_START_INDEX) null else pos-1,
                if (shoes.isNullOrEmpty()) null else pos+1)
        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }

}