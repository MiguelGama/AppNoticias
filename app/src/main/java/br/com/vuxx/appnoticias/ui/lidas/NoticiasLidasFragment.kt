package br.com.vuxx.appnoticias.ui.lidas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.vuxx.appnoticias.R
import br.com.vuxx.appnoticias.adapter.NewsAdapter
import br.com.vuxx.appnoticias.model.News
import br.com.vuxx.appnoticias.util.db.NewsRepository
import br.com.vuxx.appnoticias.util.db.NewsRepositoryImpl
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class NoticiasLidasFragment : Fragment(), NewsAdapter.OnClicked {
    private var rv: RecyclerView? = null
    private var adapter: NewsAdapter? = null
    var listaNoticias: ArrayList<News>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, null)
        rv = rootView.findViewById<View>(R.id.rv) as RecyclerView
        listaNoticias = ArrayList()
        val linearLyaoutManager = LinearLayoutManager(activity)
        rv!!.layoutManager = linearLyaoutManager

        adapter = NewsAdapter(activity!!, listaNoticias!!, this)
        rv!!.adapter = adapter
        getListaBanco().forEach { t: News? -> listaNoticias?.add(t!!) }
        adapter!!.notifyDataSetChanged()
        return rootView
    }

    override fun getItemBotaoClicked(posicao: Int, item: Any?) {
        ataulizar(listaNoticias!![posicao])
        listaNoticias?.removeAt(posicao)
        adapter?.notifyItemRemoved(posicao);
        view?.let {
            Snackbar.make(it, "Marcada Como Não Lida", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun ataulizar(news: News) {
        var repository : NewsRepository = NewsRepositoryImpl(activity!!)
        news.status = News.NAO_LIDA
        repository.atualizaStatus(news)
    }

    override fun getItemListaClicked(posicao: Int, item: Any?) {
        Log.e("TAG", "getItemListClicked")
    }

    fun getListaBanco(): ArrayList<News> {
        var repo: NewsRepository = NewsRepositoryImpl(activity!!)
        return repo.buscarTodosPorStatus(News.LIDA)
    }
}
