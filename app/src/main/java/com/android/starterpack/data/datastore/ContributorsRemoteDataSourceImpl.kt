package com.android.starterpack.data.datastore

import com.android.starterpack.core.data.datasource.BaseDataSource
import com.android.starterpack.core.domain.DataError
import com.android.starterpack.core.domain.Result
import com.android.starterpack.data.remote.APIService
import com.android.starterpack.data.remote.dto.ContributorDto
import com.android.starterpack.domain.datastore.ContributorsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class ContributorsRemoteDataSourceImpl(
    private val apiService: APIService
) : BaseDataSource(), ContributorsRemoteDataSource {

    override suspend fun fetchContributors(): Result<List<ContributorDto>, DataError> {
        return makeApiCall { apiService.getContributors() }
    }
}