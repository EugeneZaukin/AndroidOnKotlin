package com.eugene.androidonkotlin.common.di

import androidx.lifecycle.ViewModel
import com.eugene.androidonkotlin.listMovie.screen.MainViewModel
import com.eugene.androidonkotlin.movieDescription.screen.DescriptionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface MultiViewModelModule {
    @Binds
    @IntoMap
    @ClassKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(DescriptionViewModel::class)
    fun provideDescriptionViewModel(descriptionViewModel: DescriptionViewModel): ViewModel
}