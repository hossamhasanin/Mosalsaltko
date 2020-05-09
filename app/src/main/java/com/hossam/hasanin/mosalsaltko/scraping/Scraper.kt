package com.hossam.hasanin.mosalsaltko.scraping

import com.hossam.hasanin.mosalsaltko.externals.MAIN_PAGE
import com.hossam.hasanin.mosalsaltko.models.Category
import com.hossam.hasanin.mosalsaltko.models.Post
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception

class Scraper {

    fun scrapPosts(url: String) : Maybe<List<Post>> {
        return Maybe.create<List<Post>> {
            try {
                CoroutineScope(IO).launch {
                    val site: Document = Jsoup.connect(MAIN_PAGE + url)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                        .get()
                    val notReadyPosts: Elements = site.select(".date-outer .date-posts .post-outer .hentry")
                    val posts = notReadyPosts.map {
                        val base = it.select("a").first()
                        val name = base.select(".img-thumbnail img").attr("alt")
                        val img = base.select(".img-thumbnail img").attr("src")
                        val link = base.attr("href")
                        return@map Post(name= name , img = img , url = link , description = "")
                    }
                    it.onSuccess(posts)
                }
            }catch (e: Exception){
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
    }

    fun scrapCategories() : Maybe<List<Category>> {
        return Maybe.create<List<Category>> {
            try {
                CoroutineScope(IO).launch {
                    val site: Document = Jsoup.connect(MAIN_PAGE)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                        .get()
                    val liCats: Elements = site.select(".main-nav li")
                    val cats = liCats.map {
                        val base = it.select("a")
                        val name = base.text()
                        val link = base.attr("href")
                        return@map Category(
                            name = name,
                            url = link
                        )
                    }
                    it.onSuccess(cats)
                }
            }catch (e: Exception){
                it.onError(e)
            }
        }.subscribeOn(Schedulers.io())
    }



}