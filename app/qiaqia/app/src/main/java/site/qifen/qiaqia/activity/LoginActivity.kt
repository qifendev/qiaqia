package site.qifen.qiaqia.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_forget.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.*
import site.qifen.qiaqia.*
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.http.UserRetrofit
import site.qifen.qiaqia.http.ApiService
import site.qifen.qiaqia.socket.MessageService
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket
import java.util.*


class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        loginName.text = edit(readPreference("username"))
        loginPass.text = edit(readPreference("password"))


    }


    override fun initView() {
        super.initView()
        loginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginForget.setOnClickListener {
            startActivity(Intent(this, ForgetActivity::class.java))
        }
    }

    override fun initLayout(): Int = R.layout.activity_login

    override fun initData() {
        login.setOnClickListener {
            val name = loginName.text.toString()
            val pass = loginPass.text.toString()
            if (notEmpty(name) && notEmpty(pass)) {
                CoroutineScope(Dispatchers.Main).launch {
                    load()
                    try {
                        val login = ApiService.create(UserRetrofit::class.java).login(name, pass)
                        if (login.code == 200) {

                            App.token = login.data
                            App.username = name
                            App.password = pass
                            startMessageService()
                            writePreference("token", login.data)
                            writePreference("username", name)
                            writePreference("password", pass)
                            unload()

                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        t(login.message)
                        unload()
                    } catch (e: Exception) {
                        t(e.message.toString())
                        unload()
                    }
                    unload()
                }

            } else {
                t("账号密码不能为空")
            }
        }

    }


}














