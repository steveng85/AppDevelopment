package com.example.appdevelopment.ui.screens.leaderboardsView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdevelopment.data.dataClasses.Board
import com.example.appdevelopment.navigation.Screen
import com.example.appdevelopment.ui.layout.Scaffoldlayout
import com.example.appdevelopment.ui.screens.cameraView.CameraX
import com.example.appdevelopment.ui.theme.Bronze
import com.example.appdevelopment.ui.theme.Gold
import com.example.appdevelopment.ui.theme.Silver

//import com.example.appdevelopment.ui.components.BottomHomeBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    navController: NavController,
    leaderboardViewModel: LeaderboardViewModel?
){

    Scaffoldlayout(navController = navController, "Leaderboard", Color.White) { Leaderboard(leaderboardViewModel) }

}

@Composable
fun Leaderboard(leaderboardViewModel: LeaderboardViewModel?) {
    Surface(modifier = Modifier.fillMaxSize()) {
        leaderboardViewModel?.onGet()
        leaderboardViewModel?.leaderboard?.collectAsState()?.value?.let { LeaderboardsList(it) }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) { }
    }
}

@Preview
@Composable
fun LeaderboardScreenPreview(){
    LeaderboardScreen(navController = rememberNavController(), null)
}

@Composable
fun LeaderboardsList(boardList: List<Board>){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = rememberLazyListState()

    //(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
    ){
        items(boardList){ board ->
            BoardCard(rank = board.rank, name = board.name, points = board.points)
        }
    }

}

@Composable
fun BoardCard(rank: Int, name: String, points: Int){
    var backgroundColor = Color.White
    var borderColor = MaterialTheme.colorScheme.primary
    if (rank == 1){
        backgroundColor = Gold
        borderColor = Gold
    } else if(rank == 2){
        backgroundColor = Silver
        borderColor = Silver
    } else if(rank == 3){
        backgroundColor = Bronze
        borderColor = Bronze
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = backgroundColor)
            .height(80.dp)
            .fillMaxWidth()
            .border(border = BorderStroke(2.5.dp, borderColor), RoundedCornerShape(15.dp))
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            RankingText(text = "#$rank")
            NameText(text = name)
            Text(text = "$points")
        }
    }
}

@Composable
fun NameText(text: String, modifier: Modifier = Modifier){
    Surface(
        color = MaterialTheme.colorScheme.onSurface.copy(0.01f),
        modifier = modifier.semantics { heading() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .width(215.dp)
                .padding(horizontal = 1.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun RankingText(text: String, modifier: Modifier = Modifier){
    Surface(
        color = MaterialTheme.colorScheme.onSurface.copy(0.01f),
        modifier = modifier.semantics { heading() }
        ) {
        Text(text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .width(40.dp)
                .padding(horizontal = 5.dp, vertical = 5.dp))
    }
}

@Preview
@Composable
fun BoardListItemPreview(){
    BoardCard(1, "hello", 3)
}