package com.lucasgomes.personagens.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.lucasgomes.personagens.R
import com.lucasgomes.personagens.models.Personagem
import com.lucasgomes.personagens.services.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ListaPersonagensActivity : AppCompatActivity() {

    val listPersonagens : ListView by lazy { findViewById(R.id.personagens_list_view) as ListView }
    private var adapter : ListaPersonagemAdapter? = null
    var personagensList = ArrayList<Personagem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_personagens)

        adapter = ListaPersonagemAdapter(personagensList, applicationContext)
        listPersonagens.adapter = adapter

        val api = RestService()
        api.loadPersonagens()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            personagem -> personagensList.add(personagem)
                        },
                        onError = { e ->
                            e.printStackTrace()
                        },
                        onComplete = {
                            adapter!!.notifyDataSetChanged()
                        }

                )
    }
}