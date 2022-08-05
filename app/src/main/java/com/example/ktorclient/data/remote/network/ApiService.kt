package com.example.ktorclient.data.remote.network

import com.example.ktorclient.data.remote.model.Post
import com.example.ktorclient.util.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getPosts() : Post

}