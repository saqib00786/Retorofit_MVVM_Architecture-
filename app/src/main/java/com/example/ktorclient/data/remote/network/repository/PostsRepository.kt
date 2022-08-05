package com.example.ktorclient.data.remote.network.repository

import com.example.ktorclient.data.remote.model.PostItem
import com.example.ktorclient.data.remote.network.ApiService
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val apiService: ApiService
) {

//    val postData = ArrayList<PostItem>()
    suspend fun getPosts() = apiService.getPosts()
}