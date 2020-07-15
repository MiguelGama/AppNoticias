package br.com.vuxx.appnoticias.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.vuxx.appnoticias.R
import br.com.vuxx.appnoticias.adapter.NewsAdapter
import br.com.vuxx.appnoticias.model.News
import br.com.vuxx.appnoticias.model.RequestNews
import br.com.vuxx.appnoticias.util.RetrofitInitializer
import br.com.vuxx.appnoticias.util.db.NewsRepository
import br.com.vuxx.appnoticias.util.db.NewsRepositoryImpl
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeFragment : Fragment(),
    NewsAdapter.OnClicked {
    private var rv: RecyclerView? = null
    private var adapter: NewsAdapter? = null
    var listaNews: ArrayList<News>? = null
    var mLoading = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, null)
        rv = rootView.findViewById<View>(R.id.rv) as RecyclerView
        listaNews = ArrayList()
        //rv!!.setHasFixedSize(true)

        val linearLyaoutManager = LinearLayoutManager(activity)
        rv!!.layoutManager = linearLyaoutManager
        rv!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
            }
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItem = linearLyaoutManager.itemCount
                val lastVisibleItem = linearLyaoutManager.findLastVisibleItemPosition()
                if (!mLoading && lastVisibleItem == totalItem - 1) {
                    mLoading = true

                    if (listaNews != null) if (listaNews!!.size > 0);
                    //ultimo item visualizado
                }
            }
        })

        adapter = NewsAdapter(activity!!, listaNews!!, this)
        rv?.adapter = adapter
        val call = RetrofitInitializer().newsService().list()
        call.enqueue(object : Callback<RequestNews?> {
            override fun onResponse(
                call: Call<RequestNews?>,
                response: Response<RequestNews?>
            ) {
                response?.body()?.let {
                    it.articles!!.forEach { n ->
                        listaNews!!.add(n)
                    }
                }

                //obtendo dados do banco que um dia foram lidos e agora desmarcadas
                getListaBanco()?.let {
                    it.forEach { n->
                        listaNews!!.add(n)
                    }
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<RequestNews?>,
                t: Throwable
            ) {
            }
        })
        return rootView
    }

    override fun getItemBotaoClicked(posicao: Int, item: Any?) {
        insert(listaNews!![posicao])
        listaNews?.removeAt(posicao)
        adapter?.notifyItemRemoved(posicao);
        view?.let {
            Snackbar.make(it, "Marcada Como Lida", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun getItemListaClicked(posicao: Int, item: Any?) {
        openNews(listaNews!![posicao].url!!)
    }

    private fun openNews(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    fun insert(news: News) {
        var repositry: NewsRepository = NewsRepositoryImpl(activity!!)
        news.status = News.LIDA
        repositry.salvar(news)
        repositry.close()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    fun getListaBanco(): ArrayList<News> {
        var repo: NewsRepository = NewsRepositoryImpl(activity!!)
        return repo.buscarTodosPorStatus(News.NAO_LIDA)
    }
}
