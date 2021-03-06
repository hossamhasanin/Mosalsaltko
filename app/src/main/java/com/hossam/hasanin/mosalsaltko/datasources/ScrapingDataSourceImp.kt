package com.hossam.hasanin.mosalsaltko.datasources

import com.hossam.hasanin.mosalsaltko.externals.MAIN_PAGE
import com.hossam.hasanin.mosalsaltko.models.Category
import com.hossam.hasanin.mosalsaltko.models.Post
import com.hossam.hasanin.mosalsaltko.scraping.Scraper
import io.reactivex.Maybe


class ScrapingDataSourceImp(private val scraper: Scraper) : ScraptingDataSource {

    override fun getPosts(url: String): Maybe<List<Post>> {
        return scraper.scrapPosts(url)
    }

    override fun getCategories(): Maybe<List<Category>> {
        return scraper.scrapCategories()
    }

}