package com.qingyi.novels

import android.view.KeyEvent
import android.webkit.WebView
import androidx.activity.ComponentActivity

class BackEvent : ComponentActivity() {
    private lateinit var webView: WebView
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
}