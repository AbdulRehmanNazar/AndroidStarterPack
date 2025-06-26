package com.android.starterpack.domain.usecase

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.core.domain.Result
import com.android.starterpack.domain.model.Contributor
import com.android.starterpack.domain.respository.ContributorRepository
import kotlinx.coroutines.flow.Flow


/**
 * Use case defined for the Remote functionality
 */
class GetRemoteContributorsUseCase(
    private val repository: ContributorRepository
) {
    operator fun invoke(): Flow<Result<List<Contributor>, DataError.Local>> {
        return repository.getContributors()
    }
}