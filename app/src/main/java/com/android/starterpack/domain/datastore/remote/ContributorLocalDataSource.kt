package com.android.starterpack.domain.datastore.remote

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.domain.model.Contributor
import kotlinx.coroutines.flow.Flow
import com.android.starterpack.core.domain.Result

/**
 * Abstraction for the Local DB Data Source
 */
interface ContributorLocalDataSource {
    fun getContributors(): Flow<Result<List<Contributor>, DataError.Local>>
    suspend fun insertContributors(contributors: List<Contributor>): Result<Unit, DataError.Local>
    suspend fun clearContributors()
}