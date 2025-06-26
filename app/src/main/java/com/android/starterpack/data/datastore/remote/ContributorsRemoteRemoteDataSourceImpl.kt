package com.android.starterpack.data.datastore.remote

import com.android.starterpack.core.data.datasource.BaseRemoteDataSource
import com.android.starterpack.core.domain.DataError
import com.android.starterpack.core.domain.Result
import com.android.starterpack.data.remote.APIService
import com.android.starterpack.data.remote.dto.ContributorDto
import com.android.starterpack.domain.datastore.remote.ContributorsRemoteDataSource

class ContributorsRemoteRemoteDataSourceImpl(
    private val apiService: APIService
) : BaseRemoteDataSource(), ContributorsRemoteDataSource {

    override suspend fun fetchContributors(): Result<List<ContributorDto>, DataError> {
        return makeApiCall { apiService.getContributors() }
    }
}