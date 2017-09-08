package com.lucas.sorteionumeros

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    val button : Button by lazy { findViewById(R.id.button) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(View.OnClickListener { OnJogarClicked(view = View(this)) })
    }
    fun OnJogarClicked (view: View){
        var text = findViewById(R.id.textNumero) as TextView
        var numeroSorteado = Random().nextInt(11)
        text.text = "Número Sorteado: ${numeroSorteado}"
    }
}
