package com.qingyi.novels.browser

import android.content.Context

fun UpdateApp(context: Context) {
    val openInBrowser = OpenInBrowser(context)
    openInBrowser.openWebPage(context, url = "https://hub.yzuu.cf/QingYi-Studio/QingYiNovelsMobile/releases/latest/download/app.apk")
}