package com.imax.cefr.fragments.teacher.watch

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.databinding.FragmentWatchStreamBinding

class WatchStreamFragment(private val channelName: String) :
    BaseFragment<FragmentWatchStreamBinding>(FragmentWatchStreamBinding::inflate) {


    override fun FragmentWatchStreamBinding.observeViewModel() {}

    @SuppressLint("SetJavaScriptEnabled")
    override fun FragmentWatchStreamBinding.setUpViews() {

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        val url = "https://player.twitch.tv/?channel=$channelName&parent=yourAppName"
        webView.loadUrl(url)
    }

    override fun FragmentWatchStreamBinding.navigation() {}

}
