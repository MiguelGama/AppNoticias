package br.com.vuxx.appnoticias.util.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBCore (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val QL_DELETE_ENTRIES: String? =""// Não Vou implementar o update
    private val SQL_CREATE_ENTRIES: String?= "CREATE TABLE tbl_news(_id INTEGER PRIMARY KEY, author TEXT, " +
            "title TEXT, description TEXT, url TEXT, urlToImage TEXT, publishedAt TEXT,  content TEXT, status INTEGER )"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL(QL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FeedReader.db"
    }
}