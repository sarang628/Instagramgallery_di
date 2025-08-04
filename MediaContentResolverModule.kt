package com.example.instagramgallery.di.Instagramgallery_di

import com.sarang.instagralleryModule.usecase.GetFolderListUseCase
import com.sarang.instagralleryModule.usecase.GetPictureListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class MediaContentResolverModule {
    @Provides
    fun ProvideGetFolderListUseCase(): GetFolderListUseCase {
        return object : GetFolderListUseCase {
            override fun invoke(): List<String> {
                return listOf()
            }
        }
    }

    @Provides
    fun ProvideGetPictureListUseCase(): GetPictureListUseCase {
        return object : GetPictureListUseCase {
            override fun invoke(folderName: String): List<String> {
                return listOf()
            }
        }
    }
}