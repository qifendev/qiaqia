package site.qifen.qiaqia.data

import androidx.room.Entity
import androidx.room.PrimaryKey


//socket传输的消息
@Entity
data class Message(
    @PrimaryKey(autoGenerate = true)
    var messageId: Int? = null,//消息id
    var messageFrom: String,//消息发送者
    var messageTo: String,//消息接收者
    var messageTime: Long, //消息时间
    var messageType: Int,// 1确认身份  2私聊   3群聊
    var messageData: String,//数据内容
    var messageState: Int//消息状态
) {
    constructor(
        messageFrom: String,//消息发送者
        messageTo: String,//消息接收者
        messageTime: Long, //消息时间
        messageType: Int,// 1确认身份  2私聊   3群聊
        messageData: String,//数据内容
        messageState: Int//消息状态
    ) : this(
        null,
        messageFrom,
        messageTo,
        messageTime,
        messageType,
        messageData,
        messageState
    )


}

//服务器返回的消息
data class Result<D>(
    var code: Int,
    var message: String,
    var data: D
)


//用户表
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int,
    var userPassword: String,
    var userNickName: String?,
    var userSex: Int,
    var userAvatar: String?,
    var userSignature: String?,
    var userMail: String?,
    var userFixTime: Long,
    var userPhone: String?,
    var userCanLogin: Long,
    var userNotCanLoginInfo: String?
)


@Entity
data class Friend(
    @PrimaryKey(autoGenerate = true)
    var friendId //唯一id
    : Int, var friendFrom //用户1
    : String?, var friendTo //用户2
    : String?, var friendFromName //用户1对用户2昵称
    : String?, var friendToName //用户2对用户1昵称
    : String?, var friendState //状态
    : Int, var friendTime //时间
    : Long, var friendFixTime //状态时间
    : Long
)


@Entity
data class Group(
    @PrimaryKey(autoGenerate = true)
    var groupId //唯一id
    : Int, var groupName //群名
    : String?, var groupHold //群持有者id
    : String?, var groupNickName //群名
    : String?, var groupSignature //群签名
    : String?, var groupSlave //群友id
    : String?, var groupTag //标示
    : Int, var groupFixTime: Long,
    var groupTime: Long
)






