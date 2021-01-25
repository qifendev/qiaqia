package site.qifen.qiaqia.data;

import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import site.qifen.qiaqia.App

@Database(entities = [User::class,  Message::class], version = 1)
abstract class QiaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao


    companion object {
        val instance = Room.databaseBuilder(App.context, QiaDatabase::class.java, "qiaqia.db")
            .allowMainThreadQueries()
            .build()
    }

}