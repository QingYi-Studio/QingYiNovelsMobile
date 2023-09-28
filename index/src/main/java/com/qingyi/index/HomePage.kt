package com.qingyi.index

import android.webkit.WebView

object HomePage {
    fun Index(webView: WebView) {
        webView.clearHistory()
        webView.loadUrl("https://qingyi-novels.zeabur.app/index.html")
    }
}
