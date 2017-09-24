package com.lucas.gasolinaoualcool

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val editPrecoAlcool : EditText by lazy { findViewById(R.id.edit_preco_alcool) as EditText }
    private val editPrecoGasolina : EditText by lazy { findViewById(R.id.edit_preco_gasolina) as EditText }
    private val textResultado : TextView by lazy { findViewById(R.id.text_resultado) as TextView }
    private val buttonCalcular : Button by lazy { findViewById(R.id.button_calcular) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalcular.setOnClickListener({
            textResultado.text = calcularPreco(editPrecoAlcool.text.toString(), editPrecoGasolina.text.toString())
        })
    }

    private fun calcularPreco(precoAlcool : String, precoGasolina: String) : String{
        if (precoAlcool == "" && precoGasolina == ""){
            return "Informe algum valor"
        }
        val resultado =  precoAlcool.toDouble() / precoGasolina.toDouble()
        return if (resultado <= 0.7){
            "É melhor utilizar álcool"
        }else{
            "É melhor utiizar gasolina"
        }
    }
}