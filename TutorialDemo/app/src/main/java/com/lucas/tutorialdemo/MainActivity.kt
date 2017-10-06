package com.lucas.tutorialdemo

import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextPaint
import android.view.View
import com.github.amlcurran.showcaseview.OnShowcaseEventListener
import kotlinx.android.synthetic.main.activity_main.*
import com.github.amlcurran.showcaseview.targets.ActionViewTarget
import com.github.amlcurran.showcaseview.ShowcaseView
import com.github.amlcurran.showcaseview.SimpleShowcaseEventListener
import com.github.amlcurran.showcaseview.targets.ViewTarget
import android.content.SharedPreferences




class MainActivity : AppCompatActivity() {

    private lateinit var sv1 : ShowcaseView
    private lateinit var sv2 : ShowcaseView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("tutorialShow", Context.MODE_PRIVATE)
        if(sharedPref.getBoolean("tutorialShow", true)){
            sv1 = ShowcaseView.Builder(this)
                    .withNewStyleShowcase()
                    .setTarget(ViewTarget(textHello))
                    .setContentTitle("Olá")
                    .setContentText("Esse é um texto")
                    .setStyle(R.style.AppTheme)
                    .build()

            sv1.overrideButtonClick {
                sv1.hide()
                sv2 = ShowcaseView.Builder(this)
                        .withNewStyleShowcase()
                        .setTarget(ViewTarget(buttonHello))
                        .setContentTitle("Sucesso")
                        .setContentText("Esse é um botão")
                        .setStyle(R.style.AppTheme)
                        .build()
            }
            sharedPref.edit()
                    .putBoolean("tutorialShow", false)
                    .apply()
        }
    }
}
