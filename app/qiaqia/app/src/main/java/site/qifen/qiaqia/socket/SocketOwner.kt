package site.qifen.qiaqia.socket

import android.text.TextUtils
import com.google.gson.Gson
import site.qifen.qiaqia.*
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.data.QiaDatabase
import java.io.DataOutputStream
import java.io.StringReader
import java.lang.Exception
import java.net.Socket
import java.util.*

object SocketOwner {
    var socket: Socket? = null


    fun send(
        messageTo: String,
        messageData: String,
        messageType: Int = 2,
        messageState: Int = 0
    ) {
        val message = Message(
            App.username,
            messageTo,
            Date().time,
            messageType,
            messageData,
            messageState
        )
        Thread {
            try {
                val dataOutputStream = DataOutputStream(socket?.getOutputStream())
                dataOutputStream.writeUTF(
                    Gson().toJson(message).toString()
                )
                dataOutputStream.flush()

                if (!TextUtils.equals(messageTo, App.username) && messageType == MESSAGE_FRIEND)
                    QiaDatabase.instance.messageDao().insertMessage(message)

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.start()
    }


}