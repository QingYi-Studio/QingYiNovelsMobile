package com.qingyi.novels

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.io.*

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
        webView.settings.javaScriptEnabled = true
        settings.javaScriptEnabled = true

        // 支持多窗口
        webView.settings.setSupportMultipleWindows(true)

        // 设置WebViewClient
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false // 允许在WebView中加载链接
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                // 注入JavaScript代码，监听下载完成事件
                view?.loadUrl("javascript:(function() { " +
                        "var downloadLinks = document.getElementsByTagName('a');" +
                        "for (var i = 0; i < downloadLinks.length; i++) {" +
                        "downloadLinks[i].addEventListener('click', function(event) {" +
                        "event.preventDefault();" + // 阻止默认点击事件
                        "window.location.href = this.href;" + // 触发下载链接
                        "});" +
                        "}" +
                        "})()")
            }
        }

        /*
        webView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        */

        // 设置WebChromeClient
        webView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest?) {
                // 自动授权
                request?.grant(request.resources)
            }

            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                consoleMessage?.message()?.let {
                    Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                }
                return super.onConsoleMessage(consoleMessage)
            }
        }

        webView.loadUrl("https://qingyi-novels.zeabur.app/index.html")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    fun back(view: View) {
        webView.clearHistory()
        webView.loadUrl("https://qingyi-novels.zeabur.app/index.html")
    }

    fun good_author(view: View) {
        webView.loadUrl("https://qingyi-novels.zeabur.app/excellent_author/index.html")
    }

    fun update(view: View) {
        // TODO: 制作自动下载更新并自动安装
        latest_release()
    }

    fun latest_release(){
        val link = "https://hub.yzuu.cf/Grey-Wind/QingYiNovelsMobile/releases/latest/download/app.apk"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}
