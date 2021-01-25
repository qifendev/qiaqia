package site.qifen.qiaqia.socket

import site.qifen.qiaqia.data.Message
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket

interface SocketLifecycle {

    fun onConnect(socket: Socket)

    fun onError(exception: Exception)

    fun onMessage(message: Message)

}