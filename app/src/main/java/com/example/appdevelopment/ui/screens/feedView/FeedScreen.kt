package com.example.appdevelopment.ui.screens.feedView

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdevelopment.R
import com.example.appdevelopment.data.dataClasses.Feed
import com.example.appdevelopment.ui.layout.Scaffoldlayout

@ExperimentalMaterial3Api
@Composable
fun FeedScreen(navController: NavController, feedScreenViewModel: FeedScreenViewModel?) {
        val opdate = feedScreenViewModel?.uiState?.collectAsState()?.value

    if(!opdate!!.needOpdate) {
        feedScreenViewModel.onGetFeedList()
        Scaffoldlayout(
            navController = navController,
            "Feed",
            Color.White
        ) {
            feedScreenViewModel.feedList.collectAsState().value?.let { PostList(it, feedScreenViewModel)}
        }
    } else {
        feedScreenViewModel.onGetFeedList()
        feedScreenViewModel.onNeedOpdate(false)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun scaffoldprev() {
    //Scaffoldlayout(navController = rememberNavController(), text = "dø", screenContent = { PostList(posts) } )
}

@Composable
fun ClearButton(feedScreenViewModel: FeedScreenViewModel?) {
    Button(
        onClick = {feedScreenViewModel?.onEvent(FeedScreenEvent.OnClearFeed)},
        shape = RoundedCornerShape(40.dp),
        modifier = Modifier
            .padding(start = 10.dp)
            .wrapContentHeight()
            .width(100.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 8.dp,
            disabledElevation = 4.dp,
            hoveredElevation = 4.dp,
            focusedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = "Clear")
    }
}

@Preview
@Composable
fun buttonPreview(){
    ClearButton(feedScreenViewModel = null)
}

@Composable
fun PostList(posts: List<Feed>, feedScreenViewModel: FeedScreenViewModel?) {
    ClearButton(feedScreenViewModel)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = rememberLazyListState()
        ) {
            items(posts) { post ->
                PostItem(post, feedScreenViewModel)
            }
        }
}

@Composable
fun PostItem(post: Feed, feedScreenViewModel: FeedScreenViewModel?) {
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
                    painter = painterResource(id = R.drawable.ic_baseline_photo_camera_24),
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
                        text = post.timestamp.toString(),
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
                        androidx.compose.material.IconButton(onClick = { feedScreenViewModel?.onPressLike(post) }) {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_baseline_thumb_up_off_alt_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(text = "${post.like}")
                        androidx.compose.material.IconButton(onClick = { feedScreenViewModel?.onPressDislike(post) }) {
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