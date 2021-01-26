package com.alex.hoo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.alex.hoo.common.BaseConstant
import com.alex.hoo.db.dao.FavouriteShoeDao
import com.alex.hoo.db.dao.ShoeDao
import com.alex.hoo.db.dao.UserDao
import com.alex.hoo.db.data.FavouriteShoe
import com.alex.hoo.db.data.Shoe
import com.alex.hoo.db.data.User
import com.alex.hoo.utils.AppPrefsUtils
import com.alex.hoo.worker.ShoeWorker

@Database(entities = [User::class, Shoe::class, FavouriteShoe::class], version = 2, exportSchema = false)
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

                        val isFirstLaunch = AppPrefsUtils.getBoolean(BaseConstant.IS_FIRST_LAUNCH)
                        if (isFirstLaunch){
                            // 读取鞋的集合
                            val request = OneTimeWorkRequestBuilder<ShoeWorker>().build()
                            WorkManager.getInstance().enqueue(request)
                        }
                    }
                }).build()
        }

    }

}