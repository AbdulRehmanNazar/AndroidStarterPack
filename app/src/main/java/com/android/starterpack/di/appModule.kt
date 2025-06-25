package com.android.starterpack.di

import com.android.starterpack.data.datastore.ContributorsRemoteDataSourceImpl
import com.android.starterpack.data.repository.ContributorRepositoryImpl
import com.android.starterpack.domain.datastore.ContributorsRemoteDataSource
import com.android.starterpack.domain.respository.ContributorRepository
import com.android.starterpack.presentation.screens.contributor.ContributorsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.android.starterpack.domain.usecase.*

val appModule = module {
    singleOf(::GetRemoteContributorsUseCase)
    singleOf(::ContributorsViewModel)
    single<ContributorsRemoteDataSource> {
        ContributorsRemoteDataSourceImpl(get())
    }
    single<ContributorRepository> { ContributorRepositoryImpl(get()) }
}