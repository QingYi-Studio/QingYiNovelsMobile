package com.qingyi.novels

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)

        // 获取 WebView 的 WebSettings 对象
        val settings: WebSettings = webView.settings

        // 启用 JavaScript 支持
        settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        webView.loadUrl("https://qingyi-novels.zeabur.app/")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun back(view: View) {
        webView.clearHistory()
        webView.loadUrl("https://qingyi-novels.zeabur.app/")
    }

    fun good_author(view: View) {
        webView.loadUrl("https://qingyi-novels.zeabur.app/excellent_author/index.html")
    }
}
