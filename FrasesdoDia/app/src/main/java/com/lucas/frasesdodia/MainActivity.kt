package com.lucas.frasesdodia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val gerarFraseButton : Button by lazy { findViewById(R.id.btnNovaFrase) as Button }
    private val fraseText : TextView by lazy { findViewById(R.id.lblFrase) as TextView }

    private val frases = arrayOf(
            "O importante não é vencer todos os dias, mas lutar sempre.",
            "Maior que a tristeza de não haver vencido é a vergonha de não ter lutado!",
            "É melhor conquistar a si mesmo do que vencer mil batalhas.",
            "Enquanto houver vontade de lutar haverá esperança de vencer."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gerarFraseButton.setOnClickListener({ fraseAleatoria() })
    }

    private fun fraseAleatoria(){
        fraseText.text = frases[Random().nextInt(frases.size)]
    }
}
