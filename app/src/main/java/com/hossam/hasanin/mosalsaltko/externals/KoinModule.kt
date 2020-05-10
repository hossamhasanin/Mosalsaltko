package com.hossam.hasanin.mosalsaltko.externals

import androidx.room.Room
import com.hossam.hasanin.mosalsaltko.datasources.ScrapingDataSourceImp
import com.hossam.hasanin.mosalsaltko.mainPage.MainPageUseCase
import com.hossam.hasanin.mosalsaltko.mainPage.MainPageViewModel
import com.hossam.hasanin.mosalsaltko.repositories.MainRepository
import com.hossam.hasanin.mosalsaltko.scraping.Scraper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { Scraper() }

    single { Room.databaseBuilder(get(), LocalDatabase::class.java, "mosalsaltko_database").build() }

    single { get<LocalDatabase>().postsDao() }

    factory { ScrapingDataSourceImp(get()) }
    factory { MainRepository(get<ScrapingDataSourceImp>() , get()) }
    factory { MainPageUseCase(get()) }
    viewModel { MainPageViewModel(get()) }
}