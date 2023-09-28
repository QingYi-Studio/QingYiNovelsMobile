package com.qingyi.novels

import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebView
import androidx.activity.ComponentActivity

class MainPageEvent : ComponentActivity() {

    private lateinit var webView: WebView

    fun back(view: View) {
        webView.clearHistory()
        webView.loadUrl("https://qingyi-novels.zeabur.app/index.html")
    }

    fun good_author(view: View) {
        webView.loadUrl("https://qingyi-novels.zeabur.app/excellent_author/index.html")
    }

    fun update(view: View) {
        // webView.loadUrl("https://qingyi-novels.zeabur.app/download/index.html")

        val link =
            "https://hub.yzuu.cf/Grey-Wind/QingYiNovelsMobile/releases/latest/download/app.apk"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}