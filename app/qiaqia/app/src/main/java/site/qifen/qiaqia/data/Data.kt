package site.qifen.qiaqia.data

import androidx.room.Entity
import androidx.room.Ignore
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
    @PrimaryKey
    var userId: Int,
    var userPassword: String,
    var userSex: String,
    var userAvatar: String,
    var userSignature: String,
    var userMail: String,
    var userPhone: String,
    var userCanLogin: Int,
    var userNotCanLoginInfo: String
)
