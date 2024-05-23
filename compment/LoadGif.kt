package com.pet.jetpong


import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder



@Composable
fun LoadGif(resId: Int,modifier: Modifier=Modifier.fillMaxSize()) {
    val current = LocalContext.current
    val imageLoader = ImageLoader.Builder(current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    val mPainter = rememberAsyncImagePainter(resId, imageLoader)
    Image(
        painter  = mPainter,
        contentDescription = "Local GIF Image",
        modifier = modifier
    )
}




