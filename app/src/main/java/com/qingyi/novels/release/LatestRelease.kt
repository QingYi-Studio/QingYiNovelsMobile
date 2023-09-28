package com.qingyi.novels.release

import android.content.Intent
import android.net.Uri

class LatestRelease {
    fun update() {
        // TODO: ①修复当前的崩溃bug
        //       ②制作自动下载最新版安装包
        //       ③实现自动安装下载好的最新版安装包
        // TODO: 对目前的崩溃原因推测
        //  由于startActivity方法的失败调用导致无法正常打开默认浏览器
        //  尝试性解决方案:①重写startActivity方法(失败)(注：Activity.java中有更多的奇怪错误，估计是由于某个诡异的特性导致的，我是没办法了，属于是主页面的运行依靠bug了)
        //              ②修改调用方式(待测试)
        val link = "https://hub.yzuu.cf/Grey-Wind/QingYiNovelsMobile/releases/latest/download/app.apk" // 更新链接，GitHub镜像站
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }

    // 试图重写startActivity方法，一塌糊涂
    private fun startActivity(intent: Intent?) {
        startActivity(intent)
    }
}