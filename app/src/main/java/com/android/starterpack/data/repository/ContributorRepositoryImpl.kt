package com.android.starterpack.data.repository

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.core.domain.Result
import com.android.starterpack.data.mapper.ContributorMapper
import com.android.starterpack.domain.datastore.remote.ContributorLocalDataSource
import com.android.starterpack.domain.datastore.remote.ContributorsRemoteDataSource
import com.android.starterpack.domain.model.Contributor
import com.android.starterpack.domain.respository.ContributorRepository
import kotlinx.coroutines.flow.Flow

class ContributorRepositoryImpl(
    private val contributorsRemoteDataSource: ContributorsRemoteDataSource,
    private val contributorLocalDataSource: ContributorLocalDataSource
) : ContributorRepository {
    override fun getContributors(): Flow<Result<List<Contributor>, DataError.Local>> {
        return contributorLocalDataSource.getContributors()
    }

    override suspend fun getRemoteContributors(): Result<List<Contributor>, DataError.Remote> {
        return when (val result = contributorsRemoteDataSource.fetchContributors()) {
            is Result.Success -> {
                //Save in local DB while fetched data from server
                contributorLocalDataSource.insertContributors(result.data.map {
                    ContributorMapper.dtoToDomain(it)
                })
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