package com.alex.hoo.viewmodel

import android.content.Context
import com.alex.hoo.db.RepositoryProvider
import com.alex.hoo.db.data.User
import com.alex.hoo.db.repository.UserRepository
import com.alex.hoo.viewmodel.factory.LoginModelFactory
import com.alex.hoo.viewmodel.factory.RegisterModelFactory

object CustomViewModelProvider {

    fun providerRegisterModel(context: Context): RegisterModelFactory{
        val repository: UserRepository = RepositoryProvider.providerUserRepository(context)
        return RegisterModelFactory(repository)
    }

    fun providerLoginModel(context: Context): LoginModelFactory{
        val repository = RepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(repository, context)
    }

}