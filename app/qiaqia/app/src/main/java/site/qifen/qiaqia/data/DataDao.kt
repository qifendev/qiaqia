package site.qifen.qiaqia.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.selects.select
import site.qifen.qiaqia.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(userList: List<User>)

    @Insert
    fun insertUser(vararg user: User)

    @Query("delete from user where userMail = :userMail")
    fun dropUser(userMail: String)

    @Query("select * from user where userMail = :userMail and userFixTime = :fixTime")
    fun ifEqUser(userMail: String, fixTime: Long): Boolean

    @Query("select * from user where userMail = :userMail")
    fun ifExistsUserMail(userMail: String): Boolean

}

@Dao
interface MessageDao {
    @Insert
    fun insertMessage(message: Message)

    @Query("select * from message")
    fun allMessage(): LiveData<List<Message>>

    @Query("select * from message  group by messageTo  having max(messageId)")
    fun mainNewsMessage(
//        messageFriend: Int = MESSAGE_FRIEND, messageGroup: Int = MESSAGE_GROUP
    ): LiveData<List<Message>>

//    @Query("select distinct messageTo from message where max(messageId)")
//    fun mainNewsMessage1(): LiveData<List<Message>>

    @Query("select * from message where messageFrom = :messageTo or messageTo = :messageTo and messageTo != messageFrom and messageType = :messageFriend or messageType = :messageGroup order by messageId asc")
    fun detailMessage(
        messageTo: String,
        messageFriend: Int = MESSAGE_FRIEND,
        messageGroup: Int = MESSAGE_GROUP
    ): LiveData<List<Message>>

    @Query("select * from message where messageFrom = :messageFrom and messageTo = :messageTo and messageTo = messageFrom and messageType = :messageFriend or messageType = :messageGroup  order by messageId asc")
    fun detailMineMessage(
        messageTo: String,
        messageFrom: String = App.username,
        messageFriend: Int = MESSAGE_FRIEND,
        messageGroup: Int = MESSAGE_GROUP
    ): LiveData<List<Message>>

//    @Query("select * from message where messageType = :messageAddFriend or messageType = :messageAddGroup")
//    fun notifyMessage(
//        messageAddFriend: Int = MESSAGE_ADD_FRIEND,
//        messageAddGroup: Int = MESSAGE_ADD_GROUP
//    ): LiveData<List<Message>>


//    @Query("select * from message")
//    fun findAllMessage(): DataSource.Factory<Int, Message>

}

@Dao
interface FriendDao {

}


@Dao
interface GroupDao {

}




