package com.qingyi.novels

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        webView.settings.userAgentString =
            "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36" // 设置浏览器 UA 为手机
        webView.settings.javaScriptEnabled = true // 允许执行 JavaScript

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url)
                } else {
                    openInAppBrowser(url)
                }
                return true
            }
        }

        webView.loadUrl("https://gw-novels.zeabur.app/")
        setContentView(webView)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun openInAppBrowser(url: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
