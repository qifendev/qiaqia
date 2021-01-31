package site.qifen.qiaqia.activity

import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_forget.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.qifen.qiaqia.*
import site.qifen.qiaqia.http.ApiService
import site.qifen.qiaqia.http.HttpRetrofit
import java.lang.Exception

class ForgetActivity : BaseActivity() {


    var mailToken: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetBack.setOnClickListener {
            finish()
        }
    }

    override fun initLayout(): Int = R.layout.activity_forget

    override fun initData() {


        forgetCodeBtn.setOnClickListener {
            if (notEmpty(text(forgetName))) {
                CoroutineScope(Dispatchers.Main).launch {
                    load()
                    try {
                        val mail = ApiService.create(HttpRetrofit::class.java)
                            .mail(text(forgetName))
                        if (mail.code == 200) {
                            object : CountDownTimer(60000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    forgetCodeBtn.text =
                                        edit((millisUntilFinished / 1000).toString() + " 后重发")
                                }

                                override fun onFinish() {
                                    forgetCodeBtn.text = edit("重新发送")
                                    forgetCodeBtn.isEnabled = true
                                }
                            }.start()
                            forgetCodeBtn.isEnabled = false
                            mailToken = mail.data
                            unload()
                        }
                        t(mail.message)
                        unload()
                    } catch (e: Exception) {
                        t(e.message.toString())
                        unload()
                    }
                }
            } else {
                t("邮箱不能为空")
            }

        }


        forget.setOnClickListener {
            if (notEmpty(text(forgetName)) && notEmpty(text(forgetCode)) && text(
                    forgetPass
                ) == text(forgetConPass) && notEmpty(text(forgetPass))
            ) {
                load()
                CoroutineScope(Dispatchers.Main).launch {
                    load()
                    try {
                        val reg = ApiService.create(HttpRetrofit::class.java)
                            .forget(
                                text(forgetName),
                                mailToken,
                                text(forgetPass),
                                text(forgetCode).toInt()
                            )
                        t(reg.message)
                        unload()
                    } catch (e: Exception) {
                        t(e.message.toString())
                        unload()
                    }
                }
                unload()
            } else {
                t("账号或密码或验证码为空或密码输入不一致")
            }

        }


    }


}