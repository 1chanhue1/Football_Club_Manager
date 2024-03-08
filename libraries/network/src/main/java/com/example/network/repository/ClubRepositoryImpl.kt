package com.example.network.repository

import com.example.network_api.ErrorType
import com.example.network_api.RespMapper
import com.example.network_api.api.ClubApi
import com.example.network_api.repository.ClubRepository
import com.example.network_api.response.MakeClubResponse
import com.example.network_api.response.RespResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

internal class ClubRepositoryImpl @Inject constructor(
    private val clubApi: ClubApi
) : ClubRepository {
    override suspend fun sendClubInfo(
        name: RequestBody,
        emblem: MultipartBody.Part
    ): RespResult<MakeClubResponse> {
        val response = clubApi.sendClubInfo(name = name, emblem= emblem)
        return if (response.isSuccessful) {
            RespResult.Success(response.body()!!)
        } else {
            val errorBodyJson = response.errorBody()?.string() ?: ""
            val errorBody = RespMapper.errorMapper(errorBodyJson)
            RespResult.Error(ErrorType(errorBody.message!!, errorBody.code))
        }
    }
}