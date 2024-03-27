package com.example.network_api.repository

import com.example.core.model.ClubSchedule
import com.example.network_api.response.MakeClubResponse
import com.example.network_api.response.MakeClubScheduleResponse
import com.example.network_api.response.RespResult
import com.example.network_api.response.SearchClubResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ClubRepository {
    suspend fun sendClubInfo(name: RequestBody, details: RequestBody, emblem: MultipartBody.Part): RespResult<MakeClubResponse>
    suspend fun searchClub(code: String): RespResult<SearchClubResponse>
    suspend fun createClubSchedule(teamId: Long, clubSchedule: com.example.core.model.ClubSchedule) : RespResult<MakeClubScheduleResponse>
}