package com.imax.cefr.fragments.teacher.stream.watch

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.setFragmentResultListener
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.FragmentWatchStreamBinding
import com.imax.cefr.fragments.channel.ChannelFragment
import com.imax.cefr.utils.toastMessage

class WatchStreamFragment(private val stream: StreamResponseData) :
    BaseFragment<FragmentWatchStreamBinding>(FragmentWatchStreamBinding::inflate) {


    override fun FragmentWatchStreamBinding.observeViewModel() {}

    override fun FragmentWatchStreamBinding.setUpViews() {


        initWebView(webView, stream.description)
        watchStreamTitle.text = stream.streamTitle
        watchStreamAuthorAvatar.setImageResource(R.drawable.avatar)
        watchStreamAuthorTitle.text = stream.teacherName
        watchStreamAuthorSubtitle.text = stream.toString()

        watchStreamAuthorLayout.setOnClickListener {

            val fragmentInStack = requireActivity().supportFragmentManager.
            findFragmentByTag(ChannelFragment::class.java.simpleName)

            if (fragmentInStack?.isHidden == true) {
                requireActivity().supportFragmentManager.popBackStack()
            } else {
                requireActivity().supportFragmentManager.addFragmentToBackStack(
                    R.id.activity_container_view,
                    ChannelFragment(stream)
                )
            }



        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(webView: WebView, url: String) {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        val updatedUrl = if (url.startsWith("https")) {
            val videoId =  stream.description.substringAfterLast("/")
            "https://player.twitch.tv/?video=$videoId&parent=cefr"
        } else {
            "https://player.twitch.tv/?channel=${url}&parent=cefr"
        }

        webView.loadUrl(updatedUrl)
    }

    override fun FragmentWatchStreamBinding.navigation() {}

    companion object {
        const val REQUEST_KEY = "requestKey"
        const val BUNDLE_KEY = "url"
        fun newInstance(newStream: StreamResponseData) : WatchStreamFragment {
            return WatchStreamFragment(newStream)
        }
    }

}
