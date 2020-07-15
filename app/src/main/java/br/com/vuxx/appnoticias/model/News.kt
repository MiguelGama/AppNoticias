package br.com.vuxx.appnoticias.model

class News {

    var id : Int? = 0
    var author: String? = null
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedAt: String? = null
    var content: String? = null
    var status : Int? = null
    constructor()
    constructor(
        id: Int?,
        author: String?,
        title: String?,
        description: String?,
        url: String?,
        urlToImage: String?,
        publishedAt: String?,
        content: String?
    ) {
        this.id = id
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
        this.content = content
    }

    constructor(
        author: String?,
        title: String?,
        description: String?,
        url: String?,
        urlToImage: String?,
        publishedAt: String?,
        content: String?
    ) {
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
        this.content = content
    }

    companion object{
        val LIDA = 1
        val NAO_LIDA = 2
    }

}