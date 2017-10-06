package com.lucas.pdfviewerdemo

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.source.UriSource
import com.shockwave.pdfium.PdfiumCore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pdfView.run {
            settings.javaScriptEnabled = true
            loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=adobe.com/content/dam/Adobe/en/devnet/acrobat/pdfs/pdf_open_parameters.pdf")
        }
    }
}