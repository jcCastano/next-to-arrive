package com.example.nexttoarrive.di

import com.example.nexttoarrive.api.ApiClient
import com.example.nexttoarrive.data.NextToArriveRepository
import com.example.nexttoarrive.db.NtaDatabase
import com.example.nexttoarrive.presentation.NtaViewModel
import com.example.nexttoarrive.sql.DatabaseDriverFactory
import org.koin.dsl.module

val appModule = module {
    single { ApiClient() }
    single { NtaDatabase(get()) }
    single { NextToArriveRepository(get(), get()) }
    single { NtaViewModel(get()) }
}

val allModules = listOf(appModule, platformModule)
