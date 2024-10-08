package com.imax.cefr.fragments.teacher.stream.watch

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.setFragmentResultListener
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.FragmentWatchStreamBinding
import com.imax.cefr.fragments.channel.ChannelFragment
import com.imax.cefr.utils.gone
import com.imax.cefr.utils.toastMessage
import com.imax.cefr.utils.visible

class WatchStreamFragment(private val stream: StreamResponseData) :
    BaseFragment<FragmentWatchStreamBinding>(FragmentWatchStreamBinding::inflate) {

    private var fullScreenView: View? = null

    inner class WebAppInterface {
        @JavascriptInterface
        fun onVideoEnd() {
            // Handle video end event
            requireActivity().runOnUiThread {
             toastMessage("finished")  // Perform additional actions here, such as navigating or updating UI
            }
        }
    }

    override fun FragmentWatchStreamBinding.observeViewModel() {}

    override fun FragmentWatchStreamBinding.setUpViews() {


        val updatedUrl = if (stream.description.startsWith("https")) {
            val videoId =  stream.description.substringAfterLast("/")
            "https://player.twitch.tv/?video=$videoId&parent=cefr"
        } else {
            "https://player.twitch.tv/?channel=${stream.description}&parent=cefr"
        }

        initWebView(webView, updatedUrl)
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

        webView.addJavascriptInterface(WebAppInterface(), "Android")
        webView.evaluateJavascript(
            """
            document.addEventListener('DOMContentLoaded', function() {
                var player = document.querySelector('video');
                if (player) {
                    player.addEventListener('ended', function() {
                        Android.onVideoEnd();
                    });
                }
            });
            """.trimIndent(), null
        )


        webView.webViewClient = object: WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
               //to avoid going to other site
                return true
            }
        }
        webView.webChromeClient = object : WebChromeClient(){

            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                toastMessage("Show")
                fullScreenView = view
                val decorView = requireActivity().window.decorView as FrameLayout
                decorView.addView(fullScreenView, FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                ))

                webView.gone()


            }

            override fun onHideCustomView() {
                val decorView = requireActivity().window.decorView as FrameLayout
                decorView.removeView(fullScreenView)
                fullScreenView = null

                // Restore UI elements
                webView.visible()
            }
        }

        webView.loadUrl(url)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun FragmentWatchStreamBinding.navigation() {}

    companion object {
        const val REQUEST_KEY = "requestKey"
        const val BUNDLE_KEY = "url"

        @JvmStatic
        fun newInstance(newStream: StreamResponseData) : WatchStreamFragment {
            return WatchStreamFragment(newStream)
        }
    }

}
