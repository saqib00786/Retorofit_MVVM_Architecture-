import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ktorclient.components.CircularProgressBar
import com.example.ktorclient.components.EmptyScreen
import com.example.ktorclient.data.remote.model.Post
import com.example.ktorclient.data.remote.model.PostItem
import com.example.ktorclient.domain.viewmodels.states.MainViewModel
import com.example.ktorclient.domain.viewmodels.states.PostsState
import com.example.ktorclient.ui.theme.KtorClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorClientTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    FetchData()
                }
            }
        }
    }
}

@Composable
fun FetchData(postViewModel: MainViewModel = viewModel()) {


    when (val state = postViewModel.postsState.collectAsState().value) {
        is PostsState.Success -> PostsData(posts = state.data)
        is PostsState.Loading -> {
            CircularProgressBar()
        }
        is PostsState.Error -> Text(text = state.message)
        is PostsState.Empty -> {
            EmptyScreen()
        }
    }
}

@Composable
fun PostsData(posts: ArrayList<PostItem>) {

    val postData = posts.toMutableList()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(postData) { postItem ->
            PostItem(postItem)
        }
    }
}

@Composable
fun PostItem(body: PostItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .clip(RoundedCornerShape(20.dp))

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 8.dp),
            elevation = CardDefaults.cardElevation(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .border(width = 0.1.dp, color = Color.White, RoundedCornerShape(20.dp))
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 2.dp, bottom = 2.dp),
                    text = body.id.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 2.dp),
                    text = body.title,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 0.8.dp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 6.dp),
                    text = body.body,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }
        }


    }


}
