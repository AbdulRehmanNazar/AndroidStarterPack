package com.android.starterpack.domain.usecase

import com.android.starterpack.core.domain.DataError
import com.android.starterpack.core.domain.Result
import com.android.starterpack.domain.model.Contributor
import com.android.starterpack.domain.respository.ContributorRepository


/**
 * Use case defined for the Remote functionality
 */
class GetRemoteContributorsUseCase(
    private val repository: ContributorRepository
) {
    suspend operator fun invoke(): Result<List<Contributor>, DataError> {
        return repository.getRemoteContributors()
    }
}