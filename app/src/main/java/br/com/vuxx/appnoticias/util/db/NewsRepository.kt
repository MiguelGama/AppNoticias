package br.com.vuxx.appnoticias.util.db

import br.com.vuxx.appnoticias.model.News

interface NewsRepository {
    val TABLE: String?
        get() = "tbl_news"
    val ID: String?
        get() = "_id"
    val AUTHOR: String? get() = "author"
    val TITLE: String? get() = "title"
    val DESCRIPTION: String? get() = "description"
    val URL: String? get() = "url"
    val URLTOIMAGE: String? get() = "urlToImage"
    val PUBLISHEDAT: String? get() = "publishedAt"
    val CONTENT: String? get() = "content"
    val STATUS: String? get() = "status"

    fun salvar(news: News)
    fun buscarTodos(): ArrayList<News>
    fun buscarTodosPorStatus(status : Int): ArrayList<News>
    fun atualizaStatus(news: News)
    fun close()
}