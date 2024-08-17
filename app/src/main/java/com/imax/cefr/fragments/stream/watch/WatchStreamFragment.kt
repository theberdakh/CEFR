package com.imax.cefr.fragments.teacher.stream.watch

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.FragmentWatchStreamBinding
import com.imax.cefr.fragments.channel.ChannelFragment
import com.imax.cefr.fragments.profile.TeacherProfileFragment

class WatchStreamFragment(private val stream: StreamResponseData) :
    BaseFragment<FragmentWatchStreamBinding>(FragmentWatchStreamBinding::inflate) {


    override fun FragmentWatchStreamBinding.observeViewModel() {}

    override fun FragmentWatchStreamBinding.setUpViews() {
        initWebView(webView)
        watchStreamTitle.text = stream.streamTitle
        watchStreamAuthorAvatar.setImageResource(R.drawable.avatar)
        watchStreamAuthorTitle.text = stream.teacherName
        watchStreamAuthorSubtitle.text = stream.toString()

        watchStreamAuthorLayout.setOnClickListener {

            requireActivity().supportFragmentManager.
            addFragmentToBackStack(R.id.activity_container_view, ChannelFragment(stream.description))
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(webView: WebView) {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        val url = "https://player.twitch.tv/?channel=${stream.description}&parent=yourAppName"
        webView.loadUrl(url)
    }

    override fun FragmentWatchStreamBinding.navigation() {}

}
