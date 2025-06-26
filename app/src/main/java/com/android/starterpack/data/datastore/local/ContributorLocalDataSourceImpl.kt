package com.android.starterpack.data.datastore.local

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.data.local.dao.ContributorDao
import com.android.starterpack.domain.datastore.remote.ContributorLocalDataSource
import com.android.starterpack.domain.model.Contributor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.android.starterpack.core.domain.Result
import com.android.starterpack.data.mapper.ContributorMapper
import kotlinx.coroutines.flow.catch

/**
 * Implementation of DB Entity operations with Data source
 */
class ContributorLocalDataSourceImpl(
    private val dao: ContributorDao
) : ContributorLocalDataSource {

    override fun getContributors(): Flow<Result<List<Contributor>, DataError.Local>> {
        return dao.observeAllContributors()
            .map { list ->
                Result.Success(list.map { ContributorMapper.entityToDomain(it) })
            }
            .catch { e ->
                Result.Error(DataError.Local.Unknown(e.message.toString()))
            }
    }

    override suspend fun insertContributors(contributors: List<Contributor>): Result<Unit, DataError.Local> {
        return try {
            val entities = contributors.map { ContributorMapper.domainToEntity(it) }
            dao.insertContributors(entities)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.Unknown(e.message.toString()))
        }
    }

    override suspend fun clearContributors() {
        return dao.clearAll()
    }
}