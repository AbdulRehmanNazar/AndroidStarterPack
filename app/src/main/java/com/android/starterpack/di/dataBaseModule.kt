package com.android.starterpack.di

import com.android.starterpack.data.local.AppDataBase
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        AppDataBase.getInstance(get())
    }
}
