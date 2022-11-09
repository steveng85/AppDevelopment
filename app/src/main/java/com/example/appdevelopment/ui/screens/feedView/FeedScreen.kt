package com.example.appdevelopment.ui.screens.feedView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdevelopment.R
import com.example.appdevelopment.mockData.posts.Post
import com.example.appdevelopment.mockData.posts.posts
import java.util.*
import kotlin.random.Random

@ExperimentalMaterial3Api
@Composable
fun FeedScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {
        Column(
            Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            ScaffoldFeedScreen("Feed")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun ScaffoldFeedScreen(text: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        text = text,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(
                            id = R.drawable.ic_baseline_arrow_back_ios),
                            contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                backgroundColor = Color.Black,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(1f)
                ) {
                    androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(
                            id = R.drawable.ic_baseline_menu_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(
                            id = R.drawable.ic_baseline_photo_camera_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(
                            id = R.drawable.ic_baseline_leaderboard_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    ) {
        PostList(posts)
    }
}

@Composable
fun PostList(posts: List<Post>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = rememberLazyListState()
    ) {
        items(posts) { post ->
            PostItem(post)
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Column(modifier = Modifier
        .wrapContentHeight()
        .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(modifier = Modifier
            .width(250.dp)
            .wrapContentHeight(),
            shape = RoundedCornerShape(25.dp),
            backgroundColor = Color.White,
            border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.primary),
            elevation = 5.dp
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    painter = painterResource(id = post.image),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Divider(thickness = 1.dp, color = Color.LightGray)
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ) {
                    Row(modifier = Modifier.padding(
                        start = 10.dp,
                        top = 5.dp,
                        end = 10.dp,
                        bottom = 5.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 5.dp, end = 5.dp),
                            text = post.username,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            text = post.description,
                            color = MaterialTheme.colorScheme.primary,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                Column() {
                    Text(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 5.dp,
                            end = 10.dp,
                            bottom = 5.dp),
                        text = post.timestamp,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ) {
                    Divider(thickness = 1.dp, color = Color.LightGray)
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_baseline_thumb_up_off_alt_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(text = "${post.likes}")
                        androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_baseline_thumb_down_off_alt_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PostCard() {
//    Column(modifier = Modifier
//        .wrapContentHeight()
//        .wrapContentWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Card(modifier = Modifier
//            .width(250.dp)
//            .wrapContentHeight(),
//            shape = RoundedCornerShape(25.dp),
//            backgroundColor = Color.White,
//            border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.primary),
//            elevation = 5.dp
//        ) {
//            Column {
//                //Image(painterResource(id = post.image), null) /*TODO*/
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(150.dp),
//                    painter = painterResource(id = R.drawable.ic_baseline_photo_camera_24),
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
//                Divider(thickness = 1.dp, color = Color.LightGray)
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                ) {
//                    Row(modifier = Modifier.padding(
//                        start = 10.dp,
//                        top = 5.dp,
//                        end = 10.dp,
//                        bottom = 5.dp)
//                    ) {
//                        //Text(text = post.username) /*TODO*/
//                        Text(
//                            modifier = Modifier.padding(top = 5.dp, end = 5.dp),
//                            text = "Pelle",
//                            color = MaterialTheme.colorScheme.primary,
//                            fontWeight = FontWeight.Bold
//                        )
//                        //Text(text = post.description) /*TODO*/
//                        Text(
//                            modifier = Modifier.padding(top = 5.dp),
//                            text = "Found one!",
//                            color = MaterialTheme.colorScheme.primary,
//                            overflow = TextOverflow.Ellipsis,
//                        )
//                    }
//                }
//                Column() {
//                    // post.timestamp /*TODO*/
//                    Text(
//                        modifier = Modifier.padding(
//                            start = 10.dp,
//                            top = 5.dp,
//                            end = 10.dp,
//                            bottom = 5.dp),
//                        text = "1 hour ago",
//                        fontSize = 12.5.sp,
//                        fontWeight = FontWeight.Light,
//                        color = Color.Gray
//                    )
//                }
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                ) {
//                    Divider(thickness = 1.dp, color = Color.LightGray)
//                    Row(modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 10.dp, end = 10.dp),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
//                            Icon(painter = painterResource(
//                                id = R.drawable.ic_baseline_thumb_up_off_alt_24),
//                                contentDescription = null,
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                        //Text("${post.likes}") /*TODO*/
//                        Text(text = "10")
//                        androidx.compose.material.IconButton(onClick = { /*TODO*/ }) {
//                            Icon(painter = painterResource(
//                                id = R.drawable.ic_baseline_thumb_down_off_alt_24),
//                                contentDescription = null,
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                        }
//
//                    }
//                }
//            }
//        }
//    }
//}