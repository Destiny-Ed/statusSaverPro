package com.destinyed.statussaverpro.News

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import com.destinyed.statussaverpro.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_news_read.*

class NewsRead : AppCompatActivity() {

    private lateinit var webView : WebView
    private lateinit var progress : ProgressBar
    private var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_read)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        webView = findViewById(R.id.newsView)
        progress = findViewById(R.id.progressRead)


        //get the url from newsAdapter
        var intent = intent.extras
        url = intent!!.getString("url")

        webView.loadUrl(url!!)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.displayZoomControls = true

        var webViewClient = webViewClient()
        webView.webViewClient = webViewClient
    }

    inner class webViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progress.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progress.visibility = View.GONE
        }

        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            Snackbar.make(snackBar, "Internet connection required. Please try again", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") {
                    webView.loadUrl(url!!)
                }.show()
        }

    }


    private fun toast(message : String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }
}