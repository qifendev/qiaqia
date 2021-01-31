package site.qifen.qiaqia.socket

import android.annotation.TargetApi
import android.app.*
import android.content.Intent
import android.os.*
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import site.qifen.qiaqia.*
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.data.QiaDatabase
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException
import java.lang.Exception
import java.net.Socket
import java.util.*

class MessageService : Service(), SocketLifecycle {

    private lateinit var notificationManager: NotificationManager


    private lateinit var socket: Socket


    var socketLifecycle: SocketLifecycle? = null


    @Volatile
    var connect = true


    override fun onCreate() {
        super.onCreate()
        initService()
    }


    override fun onBind(intent: Intent): IBinder {
        return IBinder()
    }

    inner class IBinder : Binder() {

        fun getSocket(): Socket {
            return socket
        }

    }

    override fun onConnect(socket: Socket) {
//        socketLifecycle?.onConnect(socket)
        SocketOwner.socket = socket
    }

    override fun onError(exception: Exception) {
//        socketLifecycle?.onError(exception)
        if (exception is EOFException) {
            exception.printStackTrace()
            initService()
        }
    }

    override fun onMessage(message: Message) {
//        socketLifecycle?.onMessage(message)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = createChannel("Message", "message")
            notificationManager.createNotificationChannel(channel)
            val notification: Notification =
                createNotification(
                    message.messageTo,
                    message.messageData,
                    "message", false
                )
            notificationManager.notify(random(), notification)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun initService() {
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = createChannel(channelName, channelId)
        notificationManager.createNotificationChannel(channel)
        val notification: Notification = createNotification("恰恰", "恰恰运行中", channelId, true)
        startForeground(1, notification)
        val socketThread = SocketThread()
        socketThread.start()


    }


    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
        connect = false
    }


    inner class Send(socket: Socket) : Thread() {

        override fun run() {
            super.run()
            val dataOutputStream = DataOutputStream(socket.getOutputStream())
            dataOutputStream.writeUTF(
                Gson().toJson(
                    Message(
                        App.token,
                        "858810078@qq.com",
                        Date().time,
                        2,
                        "哈哈哈哈哈哈",
                        1
                    )
                ).toString()
            )
            dataOutputStream.flush()
        }
    }


    inner class SocketThread : Thread() {


        override fun run() {
            super.run()
            try {
                while (connect) {
                    socket = Socket(socketHost, socketPort)
                    val dataOutputStream = DataOutputStream(socket.getOutputStream())
                    dataOutputStream.writeUTF(
                        Gson().toJson(
                            Message(
                                App.token,
                                "",
                                Date().time,
                                1,
                                "",
                                1
                            )
                        ).toString()
                    )
                    dataOutputStream.flush()

                    this@MessageService.onConnect(socket)


                    while (connect) {
                        val textMessage = DataInputStream(socket.getInputStream()).readUTF()
                        val message =
                            Gson().fromJson(textMessage, Message::class.java)
                        this@MessageService.onMessage(message)
                        println("socket read: " + Gson().toJson(textMessage))
                        QiaDatabase.instance.messageDao().insertMessage(message)
                        if (message.messageType == 2) {

                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                println("socket logout")
                this@MessageService.onError(e)
                if (e is EOFException) {
                    socket.close()
                }
            }
        }
    }

}

//
//} catch (eof: EOFException) {
//    eof.printStackTrace()
//    println("eof exception")
//} catch (connect: ConnectException) {
//    connect.printStackTrace()
//    println("connect exception")
//} catch (se: SocketException) {
//    se.printStackTrace()
//    println("socket exception")