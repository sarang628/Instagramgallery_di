package com.example.instagramgallery.di.Instagramgallery_di

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sarang.instagralleryModule.compose.GalleryListWithPreviewScreen
import com.sarang.torang.ShortItem
import com.sarang.torang.ShortVideo
import com.sarang.torang.di.image.TorangAsyncImageData
import com.sarang.torang.di.image.provideTorangAsyncImage

@Composable
fun GalleryWithPhotoPicker(onNext: (List<String>) -> Unit = {}, onClose: () -> Unit = {}){
    var uris : List<Uri> by remember { mutableStateOf(listOf()) }
    val pickMultipleMedia =
        rememberLauncherForActivityResult(PickMultipleVisualMedia()) { it ->
            if (it.isNotEmpty()) {
                Log.d("__PhotoPicker", "Number of items selected: ${it.size}")
                uris = it
            }
            else { Log.d("__PhotoPicker", "No media selected") }
        }
    GalleryListWithPreviewScreen(
        onNext              = onNext,
        onClose             = onClose,
        isPhotoPickerMode   = true,
        list                = uris.map { it.toString() },
        onPhotoPicker       = { pickMultipleMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageAndVideo)) },
        imageLoader         = { provideTorangAsyncImage().invoke(
            TorangAsyncImageData(modifier = it.modifier,
                                      model = it.url,
                                      contentScale = it.contentScale))
        },
        videoLoader = { ShortItem(short = ShortVideo(id       = "0",
                                                     videoUrl = it.url),
                                  isActive = it.isActive) }

        )
}