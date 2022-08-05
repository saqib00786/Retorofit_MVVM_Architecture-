package com.example.ktorclient.domain.viewmodels.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorclient.data.remote.model.Post
import com.example.ktorclient.data.remote.model.PostItem
import com.example.ktorclient.data.remote.network.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private var _postsState = MutableStateFlow<PostsState>(PostsState.Empty)
    val postsState = _postsState.asStateFlow()


    init {
        getAllPosts()
    }

    private fun getAllPosts() {

        _postsState.value = PostsState.Loading

        viewModelScope.launch(Dispatchers.IO){
            try {
                val postsResponse = postsRepository.getPosts()
                _postsState.value = PostsState.Success(postsResponse)
            } catch (e: HttpException) {
                _postsState.value = PostsState.Error("SOME THING WENT WRONG")
            } catch (e: IOException) {
                _postsState.value = PostsState.Error("NO INTERNET CONNECTION")
            }
        }
    }
}