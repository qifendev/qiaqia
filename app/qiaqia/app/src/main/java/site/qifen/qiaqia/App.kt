package site.qifen.qiaqia

import android.app.Application


//"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg"



class App : Application() {

    companion object {
        lateinit var context: App
        lateinit var username: String
        lateinit var password: String
        lateinit var token: String
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        username = readPreference("username")
        password = readPreference("password")
        token = readPreference("token")
    }


}