package com.qingyi.novels.browser

import android.content.Context
import android.content.Intent
import android.net.Uri

class OpenInBrowser (private val context: Context){
    fun openWebPage(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}