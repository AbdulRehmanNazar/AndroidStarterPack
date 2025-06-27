package com.android.starterpack.di

import com.android.starterpack.data.datastore.local.ContributorLocalDataSourceImpl
import com.android.starterpack.data.datastore.remote.ContributorsRemoteRemoteDataSourceImpl
import com.android.starterpack.data.repository.ContributorRepositoryImpl
import com.android.starterpack.domain.datastore.remote.ContributorLocalDataSource
import com.android.starterpack.domain.datastore.remote.ContributorsRemoteDataSource
import com.android.starterpack.domain.respository.ContributorRepository
import com.android.starterpack.presentation.screens.contributor.ContributorsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.android.starterpack.domain.usecase.*

val appModule = module {
    singleOf(::GetRemoteContributorsUseCase)
    singleOf(::ContributorsViewModel)
    single<ContributorsRemoteDataSource> {
        ContributorsRemoteRemoteDataSourceImpl(get())
    }
    single<ContributorLocalDataSource> {
        ContributorLocalDataSourceImpl(get())
    }
    single<ContributorRepository> { ContributorRepositoryImpl(get(), get()) }
}