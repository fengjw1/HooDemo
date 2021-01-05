package com.alex.hoo.db

import android.content.Context
import com.alex.hoo.db.repository.UserRepository

class RepositoryProvider {

    fun providerUserRepository(context: Context): UserRepository{
        return UserRepository()
    }

}