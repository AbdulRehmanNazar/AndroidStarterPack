package com.android.starterpack.data.remote

import com.android.starterpack.data.remote.dto.ContributorDto
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("contributors")
    suspend fun getContributors(): Response<List<ContributorDto>>
}