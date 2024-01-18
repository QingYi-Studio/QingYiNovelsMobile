package com.qingyi.novels

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.*
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.qingyi.novels.browser.OpenInBrowser
import com.qingyi.novels.index.MainActivity


class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // 加载主页

        startLoad() // 启动加载项
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun startLoad () {
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
        }

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

        MainActivity().index(webView) // 加载初始页面
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

    fun back() {
        MainActivity().index(webView) // 加载初始页面
    }

    fun author() {
        webView.loadUrl("https://novels.qingyi-studio.top/excellent_author/index.html")
    }

    fun update() {
        val url = "https://hub.yzuu.cf/Grey-Wind/QingYiNovelsMobile/releases/latest/download/app.apk"
        OpenInBrowser(this).openWebPage(context = this, url)
    }

    fun dev() {
        webView.loadUrl("https://qingyi-novels-dev.zeabur.app/index.html")
    }
}
