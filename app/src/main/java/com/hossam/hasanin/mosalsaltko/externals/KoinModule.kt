package com.hossam.hasanin.mosalsaltko.externals

import com.hossam.hasanin.mosalsaltko.datasources.ScrapingDataSourceImp
import com.hossam.hasanin.mosalsaltko.mainPage.MainPageUseCase
import com.hossam.hasanin.mosalsaltko.mainPage.MainPageViewModel
import com.hossam.hasanin.mosalsaltko.repositories.MainRepository
import com.hossam.hasanin.mosalsaltko.scraping.Scraper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { Scraper() }
    factory { ScrapingDataSourceImp(get()) }
    factory { MainRepository(get<ScrapingDataSourceImp>()) }
    factory { MainPageUseCase(get()) }
    viewModel { MainPageViewModel(get()) }
}