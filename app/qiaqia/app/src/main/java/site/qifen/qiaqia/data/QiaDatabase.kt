package site.qifen.qiaqia.data;

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import site.qifen.qiaqia.App


@Database(entities = [User::class, Message::class, Friend::class, Group::class], version = 1)
abstract class QiaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun friendDao(): FriendDao
    abstract fun groupDao(): GroupDao
    abstract fun messageDao(): MessageDao


    companion object {
        private val migration1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("alter table user add column userNickName varchar(20)")
            }
        }
        val instance = Room.databaseBuilder(App.context, QiaDatabase::class.java, "qiaqia.db")
            .allowMainThreadQueries()
            .addMigrations(migration1_2)
            .build()
    }

}