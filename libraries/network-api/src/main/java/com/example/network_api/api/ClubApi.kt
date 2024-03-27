package com.example.network_api.api

import com.example.network_api.entity.ClubJoin
import com.example.network_api.entity.Join
import com.example.network_api.response.ClubJoinResponse
import com.example.network_api.response.JoinResponse
import com.example.network_api.response.MakeClubResponse
import com.example.network_api.response.SearchClubResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ClubApi {
    @Multipart
    @POST("/api/team-service/teams")
    suspend fun sendClubInfo(
        @Part("name") name: RequestBody,
        @Part emblem: MultipartBody.Part
    ): Response<MakeClubResponse>
    
    @GET("/api/team-service/team/search")
    suspend fun searchClub(
        @Query("search") code: String
    ): Response<SearchClubResponse>


    @POST("/api/team-service/teams/52/apply")
    suspend fun clubJoin(
        @Body JoinReq: ClubJoin
    ):Response<ClubJoinResponse>
    /*
    @POST("/api/team-service/teams/{teamId}/apply")
    suspend fun clubJoin(
        @Path("teamId") teamId:String,
        @Body JoinReq: ClubJoin
    ):Response<ClubJoinResponse>
    */






}