package com.naswet.PaintingsArtist

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var webView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webView = WebView(this)
        webView!!.settings.javaScriptEnabled = true
        val activity: Activity = this
        webView!!.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }
        }
        if (18 < Build.VERSION.SDK_INT ){
            //18 = JellyBean MR2, KITKAT=19
            webView!!.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        webView!!.loadUrl("https://www.etsy.com/shop/PaintingsOfTheArtist?ref=seller-platform-mcnav")
        setContentView(webView)
    }
}