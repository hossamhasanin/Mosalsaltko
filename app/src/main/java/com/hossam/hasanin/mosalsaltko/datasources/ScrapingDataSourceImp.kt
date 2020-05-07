package com.hossam.hasanin.mosalsaltko.datasources

import com.hossam.hasanin.mosalsaltko.externals.MAIN_PAGE
import com.hossam.hasanin.mosalsaltko.models.Post
import com.hossam.hasanin.mosalsaltko.scraping.Scraper
import io.reactivex.rxjava3.core.Maybe


class ScrapingDataSourceImp(private val scraper: Scraper) : ScraptingDataSource {

    override fun getPosts(url: String): Maybe<List<Post>> {
        return scraper.scrapPosts(url)
    }

//    override fun isThereMorePosts(page: String): Maybe<String> {
//        return scraper.getNextPage(page)
//    }

}