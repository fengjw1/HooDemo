package com.alex.hoo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alex.hoo.db.dao.FavouriteShoeDao
import com.alex.hoo.db.dao.ShoeDao
import com.alex.hoo.db.dao.UserDao
import com.alex.hoo.db.data.FavouriteShoe
import com.alex.hoo.db.data.Shoe
import com.alex.hoo.db.data.User

@Database(entities = [User::class, Shoe::class, FavouriteShoe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase(){

    abstract fun userDao(): UserDao

    abstract fun shoeDao(): ShoeDao

    abstract fun favouriteShoeDao(): FavouriteShoeDao

    companion object{
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase{
            return instance?: synchronized(this){
                instance?: buildDataBase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase{
            return Room.databaseBuilder(context, AppDataBase::class.java,
                "jetPackDemo-database")
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                    }
                }).build()
        }

    }

}