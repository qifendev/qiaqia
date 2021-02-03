package site.qifen.qiaqia


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import site.qifen.qiaqia.activity.MainActivity
import site.qifen.qiaqia.activity.ShowNewsActivity
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.data.QiaDatabase
import site.qifen.qiaqia.data.User
import java.util.*


val sharedPreferences: SharedPreferences =
    App.context.getSharedPreferences("qiaqia", Context.MODE_PRIVATE)


fun writePreference(key: String, value: String) {
    val edit = sharedPreferences.edit()
    edit.putString(key, value)
    edit.apply()
}

fun readPreference(key: String): String {
    return sharedPreferences.getString(key, "").toString()
}

fun t(text: String) {
    Toast.makeText(App.context, text, Toast.LENGTH_LONG).show()
}

fun edit(text: String): Editable = Editable.Factory.getInstance().newEditable(text)


fun notEmpty(text: String?): Boolean = text != null && !TextUtils.isEmpty(text)


fun <T : TextView> text(view: T) = view.text.toString()


fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}


fun px2dp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}


fun px2sp(context: Context, pxValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

fun sp2px(context: Context, spValue: Float): Int {
    val fontScale = context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}


@RequiresApi(Build.VERSION_CODES.O)
fun createNotification(
    title: String,
    content: String,
    id: String,
    isService: Boolean
): Notification {
    var intent = Intent(App.context, MainActivity::class.java)
    if (!isService) {
        intent = Intent(App.context, ShowNewsActivity::class.java)
        intent.putExtra("to", title)
    }
    return NotificationCompat.Builder(App.context, id)
        .setContentTitle(title)
        .setContentText(content)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(BitmapFactory.decodeResource(App.context.resources, R.mipmap.ic_launcher))
        .setContentIntent(
            PendingIntent.getActivity(
                App.context,
                0,
                intent,
                0
            )
        )
        .setVibrate(longArrayOf(1000, 1000, 1000))
        .setWhen(System.currentTimeMillis())
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .build()
}

@RequiresApi(Build.VERSION_CODES.O)
fun createChannel(name: String, id: String): NotificationChannel {
    return NotificationChannel(
        id,
        name,
        NotificationManager.IMPORTANCE_HIGH
    )
}

fun random(): Int {
    return Random().nextInt(89999999) + 10000000
}

// true自己发送给别人的  false别人发送给自己的
fun fromMine(message: Message) = when {
    message.messageFrom == App.username -> true
    message.messageTo == App.username -> false
    message.messageFrom == message.messageTo -> true
    else -> false
}















