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
import androidx.compose.runtime.Composable
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
import com.example.appdevelopment.mockData.leaderboards.Board
import com.example.appdevelopment.mockData.leaderboards.boards
//import com.example.appdevelopment.ui.components.BottomHomeBar

@Composable
fun LeaderboardScreen(
    navController: NavController
){
    Surface(modifier = Modifier.fillMaxSize()) {

        LeaderboardsList(boards)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            //BottomHomeBar(navController)
        }
    }
}


@Preview
@Composable
fun LeaderboardScreenPreview(){
    LeaderboardScreen(navController = rememberNavController())
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
        
        item { 
            Text(text = "Leaderboards")
        }

        items(boardList){ board ->
            BoardCard(rank = board.rank, name = board.name, points = board.point)
        }
    }

}


@Composable
fun BoardCard(rank: Int, name: String, points: Int){
    val gold = Color(0xffFFD700)
    val silver = Color(0xffC0C0C0)
    val bronze = Color(0xffCD7F32)
    var backgroundColor = Color.White
    var borderColor = MaterialTheme.colorScheme.primary
    if (rank == 1){
        backgroundColor = gold
        borderColor = gold
    } else if(rank == 2){
        backgroundColor = silver
        borderColor = silver
    } else if(rank == 3){
        backgroundColor = bronze
        borderColor = bronze
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