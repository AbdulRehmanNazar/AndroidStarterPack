package com.android.starterpack.domain.respository

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.domain.model.Contributor
import kotlinx.coroutines.flow.Flow
import com.android.starterpack.core.domain.Result
/**
 * Abstraction for the Contributor Repository
 */
interface ContributorRepository {
    suspend fun getRemoteContributors(): Result<List<Contributor>, DataError.Remote>
    fun getContributors(): Flow<Result<List<Contributor>, DataError.Local>>
}