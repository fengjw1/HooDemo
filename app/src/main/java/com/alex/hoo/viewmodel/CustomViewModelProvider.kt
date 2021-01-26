package com.alex.hoo.viewmodel

import android.content.Context
import com.alex.hoo.common.BaseConstant
import com.alex.hoo.db.RepositoryProvider
import com.alex.hoo.db.repository.FavouriteShoeRepository
import com.alex.hoo.db.repository.ShoeRepository
import com.alex.hoo.db.repository.UserRepository
import com.alex.hoo.utils.AppPrefsUtils
import com.alex.hoo.viewmodel.factory.LoginModelFactory
import com.alex.hoo.viewmodel.factory.MeModelFactory
import com.alex.hoo.viewmodel.factory.RegisterModelFactory
import com.alex.hoo.viewmodel.factory.ShoeModelFactory
import com.alex.hoo.viewmodel.factory.FavouriteShoeModelFactory
import com.joe.jetpackdemo.viewmodel.factory.FavouriteModelFactory

object CustomViewModelProvider {

    fun providerRegisterModel(context: Context): RegisterModelFactory{
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return RegisterModelFactory(repository)
    }

    fun providerLoginModel(context: Context): LoginModelFactory{
        val repository = RepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(repository, context)
    }

    fun providerShoeModel(context: Context): ShoeModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(repository)
    }

    fun providerFavouriteModel(context: Context): FavouriteModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        val userId:Long = AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
        return FavouriteModelFactory(repository,userId)
    }

    fun providerMeModel(context: Context): MeModelFactory {
        val repository:UserRepository = RepositoryProvider.providerUserRepository(context)
        return MeModelFactory(repository)
    }

    /**
     * @shoeId 鞋子的Id
     * @userId 用户的Id
     */
    fun providerDetailModel(context: Context, shoeId: Long, userId: Long): FavouriteShoeModelFactory {
        val repository: ShoeRepository = RepositoryProvider.providerShoeRepository(context)
        val favShoeRepository: FavouriteShoeRepository = RepositoryProvider.providerFavouriteShoeRepository(context)
        return FavouriteShoeModelFactory(repository, favShoeRepository, shoeId, userId)
    }
}