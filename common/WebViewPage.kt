package com.mtfe.rememberwidget.common


import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mtfe.rememberwidget.ext.fromJson
import com.mtfe.rememberwidget.ext.spGetStr


@Preview
@Composable
fun WebViewPagePreview() {
    WebViewPage("")
}

@Composable
fun WebViewPage(url:String) {
   CmPage(
        head = {
            CmPageHead(  "Privacy Policy" ,isShowLtImg = true)
        },
        body = {
//            CommonPageBody {
                Column(modifier = Modifier.fillMaxSize()) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { context ->
                            WebView(context).apply {
                                layoutParams = ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                webViewClient = WebViewClient()
                                loadUrl(url)
                            }
                        }
                    )
                }
//            }
        }
    )
}

