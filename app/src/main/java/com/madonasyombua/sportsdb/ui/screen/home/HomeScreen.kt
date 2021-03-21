package com.madonasyombua.sportsdb.ui.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import com.madonasyombua.sportsdb.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madonasyombua.sportsdb.ui.screen.LeagueTab
import com.madonasyombua.sportsdb.ui.screen.teams.TeamsDisplayGrid
import com.madonasyombua.sportsdb.ui.theme.mediumSeaGreen

/**
 * Created by Loveth Nwokike
 * on 3/15/2021
 * */
@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
     val viewModel: HomeViewModel = viewModel()
                    val leaguesState = viewModel.state.observeAsState()
                      val selected = leaguesState.value?.selectedLeague

                    Scaffold(topBar = {
                        AppBar()
                    },modifier = Modifier.background(color = Color.White)) {
                        leaguesState.value?.leagues?.let {leagues->
                            selected?.let {
                                Column {
                                    LeagueTab(
                                        leagues = leagues.take(20),
                                        selectedLeague = it,
                                        onLeagueSelected = viewModel::onLeagueSelected
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Crossfade(targetState = selected, modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)) {
                                        TeamsDisplayGrid(it)
                                    }
                                }

                            }
                        }
    }
}



@Composable
fun AppBar() {
    TopAppBar(title = {
        Text(text = "Sports App", color = Color.White)
    },
                backgroundColor = mediumSeaGreen,
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = { /* TODO: Open search */ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.cd_search),
                        tint = Color.White
                    )
                }
            }
        })
}

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MaterialTheme {
        HomeScreen()
    }
}