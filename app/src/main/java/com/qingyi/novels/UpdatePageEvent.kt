package com.qingyi.novels

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class UpdatePageEvent : ComponentActivity()  {

    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_page) // 设置页面

        adView = findViewById(R.id.adView) // 获取adView控件

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest) // 启动广告显示
    }

    // 确保在 Activity 停止时停止加载和显示广告
    override fun onPause() {
        adView.pause() // 暂停广告显示
        super.onPause()
    }

    // 在 Activity 恢复时继续加载和显示广告
    override fun onResume() {
        super.onResume()
        adView.resume() // 恢复广告显示
    }

    // 在 Activity 销毁时释放广告资源
    override fun onDestroy() {
        adView.destroy() // 关闭广告显示并释放资源
        super.onDestroy()
    }
}