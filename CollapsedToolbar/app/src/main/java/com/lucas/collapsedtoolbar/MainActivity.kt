package com.lucas.collapsedtoolbar

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import android.transition.TransitionManager
import android.widget.FrameLayout
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        setSupportActionBar(toolbar)
        collapsingToolbar.title = getString(R.string.agrosig)
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white))
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary))
        var isShow = false
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0) {
                isShow = true
                showOption(R.id.action_sincronizar)
                val layoutParams = FrameLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.topMargin = 0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(tableMenu)
                }
                tableMenu.layoutParams = layoutParams
            } else {
                isShow = false
                val layoutParams = FrameLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.topMargin = 32
                tableMenu.layoutParams = layoutParams
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(tableMenu)
                }
                hideOption(R.id.action_sincronizar)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        hideOption(R.id.action_sincronizar)
        return true
    }

    private fun hideOption(id: Int) {
        val item = menu?.findItem(id)
        item?.isVisible = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    private fun showOption(id: Int) {
        val item = menu?.findItem(id)
        item?.isVisible = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}
