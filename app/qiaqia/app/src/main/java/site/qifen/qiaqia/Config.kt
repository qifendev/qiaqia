package site.qifen.qiaqia

/**
 * app所有配置
 */


const val socketHost = "192.168.1.46"
const val socketPort = 9999

const val serverHost = "http://192.168.1.46:7000"


const val channelId = "MessageService"
const val channelName = "MessageService"


//状态
const val MESSAGE_FAIL = -1 //发送消息失败

const val MESSAGE_NOT_READ = 0 //发送消息成功但是对方未在线

const val MESSAGE_READ = 1 //发送消息成功并且已读


//类型
const val MESSAGE_TOKEN = 1 //确认身份

const val MESSAGE_FRIEND = 2 //私聊

const val MESSAGE_GROUP = 3 //群聊

//const val MESSAGE_ADD_FRIEND = 4 //加好友
//
//const val MESSAGE_ADD_GROUP = 5 //加群

