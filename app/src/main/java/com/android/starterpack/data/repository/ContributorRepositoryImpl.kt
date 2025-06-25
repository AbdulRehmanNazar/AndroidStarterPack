package com.android.starterpack.data.repository

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.core.domain.Result
import com.android.starterpack.core.domain.map
import com.android.starterpack.data.mapper.ContributorMapper
import com.android.starterpack.domain.datastore.ContributorsRemoteDataSource
import com.android.starterpack.domain.model.Contributor
import com.android.starterpack.domain.respository.ContributorRepository

class ContributorRepositoryImpl(
    private val contributorsRemoteDataSource: ContributorsRemoteDataSource
) : ContributorRepository {

    override suspend fun getRemoteContributors(): Result<List<Contributor>, DataError.Remote> {
        return when (val result = contributorsRemoteDataSource.fetchContributors()) {
            is Result.Success -> {
                val domainList = result.data.map { ContributorMapper.dtoToDomain(it) }
                Result.Success(domainList)
            }

            is Result.Error -> {
                // Only return if error is of type Remote
                val error = result.error
                when (error) {
                    is DataError.Remote -> Result.Error(error)
                    else -> Result.Error(DataError.Remote.Unknown("Unexpected error type"))
                }
            }
        }
    }
}