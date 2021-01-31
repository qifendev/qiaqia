package site.qifen.qiaqia.activity

import android.content.*
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.library.bean.BarConfig
import kotlinx.android.synthetic.main.toolbar.*
import site.qifen.qiaqia.*
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.data.QiaDatabase
import site.qifen.qiaqia.socket.MessageService
import site.qifen.qiaqia.socket.SocketLifecycle
import site.qifen.qiaqia.socket.SocketOwner
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket
import java.util.*

abstract class BaseActivity : AppCompatActivity(), SocketLifecycle {


    val TAG: String
        get() = this.javaClass.name


    lateinit var messageService: MessageService.IBinder


    open fun enableFull(): Boolean {
        return false
    }

    fun hideKeyBoard(view: View) {
        val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (input.isActive) {
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    private lateinit var loadPop: LoadingDialog

    private val toolbar: Toolbar?
        get() = findViewById(R.id.toolbar)

    lateinit var logoutReceiver: LogoutReceiver

    private lateinit var serviceConnection: ServiceConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayout())
        initBase()
        initView()
        initData()


    }

    private fun initBase() {
        ActivityCollector.addActivity(this)

        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder) {
                messageService = service as MessageService.IBinder
//                messageService.socketLifecycle(this@BaseActivity)
            }

            override fun onServiceDisconnected(name: ComponentName?) {

            }
        }


    }


    override fun onConnect(socket: Socket) {
//        Send(socket).start()
    }

    override fun onError(exception: Exception) {

    }

    override fun onMessage(message: Message) {

    }


    inner class Send(var socket: Socket) : Thread() {

        override fun run() {
            super.run()
            val dataOutputStream = DataOutputStream(socket.getOutputStream())
            dataOutputStream.writeUTF(
                Gson().toJson(
                    Message(
                        readPreference("token"),
                        "858810078@qq.com",
                        Date().time,
                        2,
                        "我是消息我发送来了",
                        1
                    )
                ).toString()
            )
            dataOutputStream.flush()
        }
    }


    open fun showToolbarBack(): Boolean {
        return false
    }


    open fun initView() {
        loadPop = LoadingDialog(this)

        setSupportActionBar(toolbar)
        val showToolbarBack = showToolbarBack()
        supportActionBar?.setDisplayHomeAsUpEnabled(showToolbarBack)//添加默认的返回图标
        supportActionBar?.setHomeButtonEnabled(showToolbarBack) //设置返回键可用
        supportActionBar?.setDisplayShowTitleEnabled(true) //设置显示标题


        if (enableFull()) {
            val config = BarConfig.newInstance()
                .colorRes(R.color.ic_background)
            UltimateBarX.with(this)
                .config(
                    config
                )
                .applyStatusBar()
            UltimateBarX.with(this)
                .config(
                    config
                )
                .applyNavigationBar()
        }


    }


    abstract fun initLayout(): Int

    abstract fun initData()

    fun load() = if (loadPop.isShowing) Unit else loadPop.show()

    fun unload() = if (loadPop.isShowing) loadPop.dismiss() else Unit


    fun setScreenRotate(screenRotate: Boolean) {
        requestedOrientation = if (screenRotate) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //设置竖屏模式
        } else {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }


    override fun onResume() {
        super.onResume()
        logoutReceiver = LogoutReceiver()
        registerReceiver(logoutReceiver, IntentFilter("site.qifen.qiaqia.LOGOUT"))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(logoutReceiver)
    }

    inner class LogoutReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            AlertDialog.Builder(context).apply {
                setTitle("异常")
                setMessage("退出登录")
                setCancelable(false)
                setPositiveButton("确认") { _, _ ->
                    ActivityCollector.finishAll()
                    context.startActivity(Intent(context, LoginActivity::class.java))
                }
                show()
            }
        }
    }


    fun sendLogout() {
        sendBroadcast(Intent("site.qifen.qiaqia.LOGOUT"))
    }


    fun startMessageService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, MessageService::class.java))
        } else {
            startService(Intent(this, MessageService::class.java))
        }

        bindService(
            Intent(this, MessageService::class.java),
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )

    }


}













