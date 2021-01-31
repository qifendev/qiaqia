package site.qifen.qiaqia.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import site.qifen.qiaqia.data.*


interface HttpRetrofit {

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


    @GET("/allUser")
    suspend fun allUser(): List<User>


    @GET("/findUser")
    suspend fun findUser(
        @Query("text") name: String
    ): Result<List<User>>


    @GET("/myFriends")
    suspend fun myFriends(
        @Query("mail") mail: String
    ): Result<List<Friend>>

    @GET("/addFriend")
    suspend fun addFriend(
        @Query("from") from: String,
        @Query("to") to: String
    ): Result<String>

    @GET("/findGroup")
    suspend fun findGroup(
        @Query("name") name: String
    ): Result<List<Group>>

    @GET("/addGroup")
    suspend fun addGroup(
        @Query("mail") mail: String,
        @Query("groupName") groupName: String
    ): Result<Group>

    @GET("/myGroup")
    suspend fun myGroup(
        @Query("mail") mail: String
    ): Result<List<Group>>


    @GET("/createGroup")
    suspend fun createGroup(
        @Query("name") name: String,
        @Query("mail") mail: String
    ): Result<Group>

    @GET("/findMessage")
    suspend fun findMessage(
        @Query("data") data: String,
        @Query("mail") mail: String
    ): Result<List<Message>>



}














