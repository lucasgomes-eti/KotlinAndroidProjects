package com.lucasgomes.consultacep.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.lucasgomes.consultacep.R
import com.lucasgomes.consultacep.services.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import android.app.ProgressDialog
import android.view.View
import android.widget.ProgressBar


class MainActivity : AppCompatActivity() {

    val cep : TextView by lazy { findViewById(R.id.txt_cep) as TextView }
    val logradouro : TextView by lazy { findViewById(R.id.txt_logradouro) as TextView }
    val complemento : TextView by lazy { findViewById(R.id.txt_complemento) as TextView }
    val bairro : TextView by lazy { findViewById(R.id.txt_bairro) as TextView }
    val localidade : TextView by lazy { findViewById(R.id.txt_localidade) as TextView }
    val uf : TextView by lazy { findViewById(R.id.txt_uf) as TextView }
    val ibge : TextView by lazy { findViewById(R.id.txt_ibge) as TextView }
    val buscarButton : Button by lazy { findViewById(R.id.btn_buscar) as Button }
    val mProgressBar : ProgressBar by lazy { findViewById(R.id.progressBar) as ProgressBar }

    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setMessage("Carregando...")
        mProgressDialog.setCancelable(false)

        buscarButton.setOnClickListener {
            mProgressBar.visibility = View.VISIBLE
            //mProgressDialog.show()
            getEndereco(cep.text.toString())
        }
    }

    fun getEndereco(cep: String) {
        val restService = RestService()
        restService.loadEndereco(cep)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { endereco ->
                            logradouro.text = endereco.Logradouro
                            complemento.text = endereco.Complemento
                            bairro.text = endereco.Bairro
                            localidade.text = endereco.Localidade
                            uf.text = endereco.Uf
                            ibge.text = endereco.Ibge.toString()
                        },
                        onError = { e ->
                            e.printStackTrace()
                        },
                        onComplete = {
                            //mProgressDialog.dismiss()
                            mProgressBar.visibility = View.GONE
                        }
                )
    }
}