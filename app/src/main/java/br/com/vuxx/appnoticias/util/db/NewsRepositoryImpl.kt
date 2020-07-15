package br.com.vuxx.appnoticias.util.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import br.com.vuxx.appnoticias.model.News

class NewsRepositoryImpl : NewsRepository{
    private var db: SQLiteDatabase?

    constructor(context: Context){
        var dbHelper = DBCore(context)
        db = dbHelper.writableDatabase
    }

    override fun salvar(news : News) {
        val valores = ContentValues().apply {
            //put(ID, title)
            put(AUTHOR, news.author)
            put(TITLE, news.title)
            put(CONTENT, news.content)
            put(DESCRIPTION, news.description)
            put(URL, news.url)
            put(URLTOIMAGE, news.urlToImage)
            put(PUBLISHEDAT, news.publishedAt)
            put(STATUS, news.status)
        }

        db?.insert(TABLE, null, valores)

    }

    override fun buscarTodos(): ArrayList<News> {
        val colunas = arrayOf(ID, AUTHOR, TITLE, CONTENT, DESCRIPTION, URL,
            URLTOIMAGE, PUBLISHEDAT, STATUS)

        val cursor = db!!.query(
            TABLE,
            colunas,
            null,
            null,
            null,
            null,
            null
        )
        val list = arrayListOf<News>()
        with(cursor) {
            while (moveToNext()) {
                var n = News()
                n.id = getInt(getColumnIndexOrThrow(ID))
                n.author = getString(getColumnIndexOrThrow(AUTHOR))
                n.title = getString(getColumnIndexOrThrow(TITLE))
                n.content = getString(getColumnIndexOrThrow(CONTENT))
                n.description = getString(getColumnIndexOrThrow(DESCRIPTION))
                n.urlToImage = getString(getColumnIndexOrThrow(URLTOIMAGE))
                n.url = getString(getColumnIndexOrThrow(URL))
                n.publishedAt = getString(getColumnIndexOrThrow(PUBLISHEDAT))
                n.status = getInt(getColumnIndexOrThrow(STATUS))
                list.add(n)
            }
        }
        return list
    }

    override fun buscarTodosPorStatus(status : Int): ArrayList<News> {

        val colunas = arrayOf(ID, AUTHOR, TITLE, CONTENT, DESCRIPTION, URL,
            URLTOIMAGE, PUBLISHEDAT, STATUS)

        val condicao = "${STATUS} = ?"
        val args = arrayOf(status.toString())

        val cursor = db!!.query(
            TABLE,
            colunas,
            condicao,
            args,
            null,
            null,
            null
        )
        val list = arrayListOf<News>()
        with(cursor) {
            while (moveToNext()) {
                var n = News()
                n.id = getInt(getColumnIndexOrThrow(ID))
                n.author = getString(getColumnIndexOrThrow(AUTHOR))
                n.title = getString(getColumnIndexOrThrow(TITLE))
                n.content = getString(getColumnIndexOrThrow(CONTENT))
                n.description = getString(getColumnIndexOrThrow(DESCRIPTION))
                n.urlToImage = getString(getColumnIndexOrThrow(URLTOIMAGE))
                n.url = getString(getColumnIndexOrThrow(URL))
                n.publishedAt = getString(getColumnIndexOrThrow(PUBLISHEDAT))
                n.status = getInt(getColumnIndexOrThrow(STATUS))
                list.add(n)
            }
        }
        return list
    }

    override fun atualizaStatus(news: News) {

        val valores = ContentValues().apply {
            put(STATUS, news.status)
        }
        val condicao = "${ID} = ?"
        val args = arrayOf(news.id.toString())
        val count = db?.update(
            TABLE,
            valores,
            condicao,
            args)
    }

    override fun close() {
        db?.close()
    }
}