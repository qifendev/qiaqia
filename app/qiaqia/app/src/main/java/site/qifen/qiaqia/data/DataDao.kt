package site.qifen.qiaqia.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import site.qifen.qiaqia.App

@Dao
public interface UserDao {

}

@Dao
public interface MessageDao {
    @Insert
    fun insertMessage(message: Message)

    @Query("select * from message")
    fun allMessage(): LiveData<List<Message>>

    @Query("select * from message group by messageTo having max(messageId)")
    fun mainNewsMessage(): LiveData<List<Message>>

    @Query("select * from message where messageFrom = :messageTo or messageTo = :messageTo and messageTo != messageFrom order by messageId asc")
    fun detailMessage(messageTo: String): LiveData<List<Message>>

    @Query("select * from message where messageFrom = :messageFrom and messageTo = :messageTo and messageTo = messageFrom order by messageId asc")
    fun detailMineMessage(messageTo: String,messageFrom: String = App.username): LiveData<List<Message>>

}


//master   8588

//from     8588   to     2654
//from     2654   to     8588







