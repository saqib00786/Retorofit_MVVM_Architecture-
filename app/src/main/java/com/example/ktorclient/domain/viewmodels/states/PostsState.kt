package com.example.ktorclient.domain.viewmodels.states

import com.example.ktorclient.data.remote.model.Post
import com.example.ktorclient.data.remote.model.PostItem

sealed class PostsState{
        object Empty: PostsState()
        object Loading: PostsState()
        class Success(val data: ArrayList<PostItem>): PostsState()
        class Error(val message: String): PostsState()
}
