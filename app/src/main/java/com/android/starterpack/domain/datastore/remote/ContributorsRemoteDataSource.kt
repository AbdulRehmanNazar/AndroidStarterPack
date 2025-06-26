package com.android.starterpack.domain.datastore.remote

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.data.remote.dto.ContributorDto
import com.android.starterpack.core.domain.Result

/**
 * Abstraction for the Remote Data Source of Contributer
 */
interface ContributorsRemoteDataSource {
    suspend fun fetchContributors(): Result<List<ContributorDto>, DataError>
}