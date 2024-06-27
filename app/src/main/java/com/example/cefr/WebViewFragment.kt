package com.example.cefr

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cefr.databinding.FragmentWebviewBinding
import com.example.cefr.view.MainActivity

class WebViewFragment : Fragment(R.layout.fragment_webview) {

    private lateinit var binding: FragmentWebviewBinding
    private lateinit var webView: WebView
    private val navArgs by navArgs<WebViewFragmentArgs>()
    private lateinit var mainActivity: MainActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()

        binding = FragmentWebviewBinding.bind(view)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            view.setPadding(
                insets.systemWindowInsetBottom,
                insets.systemWindowInsetTop,
                insets.systemWindowInsetRight,
                insets.systemWindowInsetLeft
            )
            insets.consumeSystemWindowInsets()
        }

        webView = binding.webview
        webView.settings.javaScriptEnabled = true // Enable JavaScript for Twitch stream playback

        // Consider using a WebViewClient for handling page loading events, errors, etc.
        webView.webViewClient = WebViewClient()

        // Optionally, use a WebChromeClient for handling progress bars, JavaScript alerts, etc.
        webView.webChromeClient = WebChromeClient()

        val channelName = navArgs.channelName
        val url = "https://player.twitch.tv/?channel=$channelName&parent=yourAppName"
        webView.loadUrl(url)
    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(true)
    }
}