package site.qifen.qiaqia.socket

import android.text.TextUtils
import com.google.gson.Gson
import site.qifen.qiaqia.App
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.data.QiaDatabase
import site.qifen.qiaqia.fromMine
import site.qifen.qiaqia.readPreference
import java.io.DataOutputStream
import java.io.StringReader
import java.lang.Exception
import java.net.Socket
import java.util.*

object SocketOwner {
    var socket: Socket? = null


    fun send(messageTo: String, messageData: String, messageType: Int = 2, messageState: Int = 1) {
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

                if (!TextUtils.equals(messageTo, App.username))
                    QiaDatabase.instance.messageDao().insertMessage(message)


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.start()
    }


}