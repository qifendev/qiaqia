package site.qifen.qiaqia.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import site.qifen.qiaqia.data.Result


interface UserRetrofit {

    @POST("/forget")
    suspend fun forget(
        @Query("mail") mail: String,
        @Query("token") token: String,
        @Query("password") password: String,
        @Query("code") code: Int
    ): Result<String>

    @POST("/reg")
    suspend fun reg(
        @Query("mail") mail: String,
        @Query("token") token: String,
        @Query("password") password: String,
        @Query("code") code: Int
    ): Result<String>


    @GET("/mail")
    suspend fun mail(
        @Query("mail") mail: String
    ): Result<String>


    @POST("/login")
    suspend fun login(
        @Query("mail") mail: String,
        @Query("password") password: String
    ): Result<String>


    @GET("/token")
    suspend fun token(
        @Query("token") token: String
    ): Result<String>


}














