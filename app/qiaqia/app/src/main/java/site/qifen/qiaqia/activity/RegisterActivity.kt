package site.qifen.qiaqia.activity

import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.qifen.qiaqia.*
import site.qifen.qiaqia.http.ApiService
import site.qifen.qiaqia.http.UserRetrofit
import java.lang.Exception

class RegisterActivity : BaseActivity() {

    var mailToken: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBack.setOnClickListener {
            finish()
        }

    }


    override fun initLayout(): Int = R.layout.activity_register

    override fun initData() {


        registerCodeBtn.setOnClickListener {

            if (notEmpty(text(registerName))) {
                CoroutineScope(Dispatchers.Main).launch {
                    load()
                    try {
                        val mail = ApiService.create(UserRetrofit::class.java)
                            .mail(text(registerName))
                        if (mail.code == 200) {
                            object : CountDownTimer(60000, 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    registerCodeBtn.text =
                                        edit((millisUntilFinished / 1000).toString() + " 后重发")
                                }

                                override fun onFinish() {
                                    registerCodeBtn.text = edit("重新发送")
                                    registerCodeBtn.isEnabled = true
                                }
                            }.start()
                            registerCodeBtn.isEnabled = false
                            mailToken = mail.data
                            unload()
                        }
                        t(mail.message)
                        unload()
                    } catch (e: Exception) {
                        t(e.message.toString())
                        unload()
                    }
                    unload()
                }
            } else {
                t("邮箱不能为空")
            }

        }

        register.setOnClickListener {


            if (notEmpty(text(registerName)) && notEmpty(text(registerCode)) && text(
                    registerConPass
                ) == text(registerPass) && notEmpty(text(registerPass))
            ) {
                CoroutineScope(Dispatchers.Main).launch {
                    load()
                    try {
                        val reg = ApiService.create(UserRetrofit::class.java)
                            .reg(
                                text(registerName),
                                mailToken,
                                text(registerPass),
                                text(registerCode).toInt()
                            )
                        t(reg.message)
                        unload()
                    } catch (e: Exception) {
                        t(e.message.toString())
                        unload()
                    }
                    unload()
                }
            } else {
                t("账号或密码或验证码为空或密码输入不一致")
            }

        }


    }

}








