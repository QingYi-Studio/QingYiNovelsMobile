package com.qingyi.novels.index

import android.webkit.WebView

class MainActivity {
    fun index(webView: WebView) {
        webView.clearHistory()
        webView.loadUrl("https://novels.qingyi-studio.top/index.html")
    }
}