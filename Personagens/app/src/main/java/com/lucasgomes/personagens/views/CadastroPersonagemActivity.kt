package com.lucasgomes.personagens.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import com.lucasgomes.personagens.R
import com.squareup.picasso.Picasso

class CadastroPersonagemActivity : AppCompatActivity() {

    val imgProfile : ImageView by lazy { findViewById(R.id.imgCadastroProfile) as ImageView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_personagem)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = "Cadastrar Personagem"
        Picasso.with(this)
                .load("http://s3.amazonaws.com/37assets/svn/765-default-avatar.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imgProfile)
    }
}
