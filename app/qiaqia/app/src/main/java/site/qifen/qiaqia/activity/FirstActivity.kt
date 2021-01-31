package site.qifen.qiaqia.activity

import android.content.Intent
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.qifen.qiaqia.*
import site.qifen.qiaqia.http.ApiService
import site.qifen.qiaqia.http.HttpRetrofit
import java.lang.Exception

class FirstActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initLayout(): Int = R.layout.activity_first


    override fun initView() {
        super.initView()
        if (notEmpty(App.token)) {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val token =
                        ApiService.create(HttpRetrofit::class.java).token(App.token)
                    if (token.code == 200) {
                        startMessageService()
                        startActivity(Intent(this@FirstActivity, MainActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@FirstActivity, LoginActivity::class.java))
                        finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    t(e.message.toString())
                    startActivity(Intent(this@FirstActivity, MainActivity::class.java))
                    finish()
                }
            }
        } else {
            startActivity(Intent(this@FirstActivity, LoginActivity::class.java))
            finish()
        }


    }


    override fun enableFull() = true


    override fun initData() {

    }
}