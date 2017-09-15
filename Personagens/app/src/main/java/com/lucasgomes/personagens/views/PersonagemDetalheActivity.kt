package com.lucasgomes.personagens.views

import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toolbar

import com.lucasgomes.personagens.R
import com.lucasgomes.personagens.models.Personagem
import com.lucasgomes.personagens.services.RestService
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PersonagemDetalheActivity : AppCompatActivity() {

    companion object {
        const val ID_KEY = "ID_KEY"
    }

    private val profileImage : ImageView by lazy { findViewById(R.id.imgProfile) as ImageView }
    private val textNome : TextView by lazy { findViewById(R.id.textName) as TextView }
    private val loader : ProgressBar by lazy { findViewById(R.id.progressBar2) as ProgressBar }
    private lateinit var personagem : Personagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personagem_detalhe)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val id = intent.extras.getInt("ID_KEY")

        val api = RestService()
        api.loadPersonagem(id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeBy (
                        onNext = {
                            personagem ->  this.personagem = personagem
                            loader.visibility = View.VISIBLE
                        },
                        onError = {
                            e -> e.printStackTrace()
                        },
                        onComplete = {
                            loader.visibility = View.GONE
                            this.title = personagem.Nome
                            Picasso.with(this)
                                    .load(personagem.FotoUrl)
                                    .placeholder(R.mipmap.ic_launcher)
                                    .error(R.mipmap.ic_launcher)
                                    .into(profileImage)
                            textNome.text = personagem.Descricao
                        }
                )

    }
}
