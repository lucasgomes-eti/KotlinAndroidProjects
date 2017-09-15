package com.lucasgomes.personagens.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.lucasgomes.personagens.R
import com.lucasgomes.personagens.models.Personagem
import com.lucasgomes.personagens.services.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

class ListaPersonagensActivity : AppCompatActivity() {

    val listPersonagens : ListView by lazy { findViewById(R.id.personagens_list_view) as ListView }
    val fabCadastrar : FloatingActionButton by lazy { findViewById(R.id.floatingActionButton) as FloatingActionButton }

    private var adapter : ListaPersonagemAdapter? = null
    var personagensList = ArrayList<Personagem>()

    val mProgressBar : ProgressBar by lazy { findViewById(R.id.progressBar) as ProgressBar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_personagens)

        adapter = ListaPersonagemAdapter(personagensList, applicationContext, {onDetalheClick(it)})
        listPersonagens.adapter = adapter

        fabCadastrar.setOnClickListener({
            val intent = Intent(this, CadastroPersonagemActivity::class.java)
            startActivity(intent)
        })

        val api = RestService()
        api.loadPersonagens()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeBy(
                        onNext = {
                            personagem -> personagensList.add(personagem)
                            mProgressBar.visibility = View.VISIBLE
                        },
                        onError = { e ->
                            e.printStackTrace()
                            mProgressBar.visibility = View.GONE
                        },
                        onComplete = {
                            adapter!!.notifyDataSetChanged()
                            mProgressBar.visibility = View.GONE
                        }

                )
    }
    private fun onDetalheClick(personagem: Personagem){
        val intent = Intent(this, PersonagemDetalheActivity::class.java)
                intent.putExtra(PersonagemDetalheActivity.ID_KEY, personagem.ID)
                startActivity(intent)
    }
}