package site.qifen.qiaqia.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import site.qifen.qiaqia.serverHost

object ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(serverHost)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serverClass: Class<T>): T = retrofit.create(serverClass) //创建retrofit服务

    private inline fun <reified T> create() = create(T::class.java) //调用上面的create并且实化T

}




