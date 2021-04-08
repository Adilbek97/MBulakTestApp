package com.example.mbulaktestapp.di

import com.example.mbulaktestapp.network.MainApi
import com.example.mbulaktestapp.network.RetrofitService
import com.example.mbulaktestapp.repository.AuthRepository
import com.example.mbulaktestapp.ui.phone.PhoneNumberViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module{
    single { RetrofitService.builService(MainApi::class.java) }
    single { AuthRepository(get()) }

    viewModel { PhoneNumberViewModel(get()) }
}