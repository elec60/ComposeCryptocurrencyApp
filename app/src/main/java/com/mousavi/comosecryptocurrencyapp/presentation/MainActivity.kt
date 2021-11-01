package com.mousavi.comosecryptocurrencyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mousavi.comosecryptocurrencyapp.R
import com.mousavi.comosecryptocurrencyapp.presentation.coin_detail.CoinDetailScreen
import com.mousavi.comosecryptocurrencyapp.presentation.coin_list.CoinsViewModel
import com.mousavi.comosecryptocurrencyapp.presentation.coin_list.components.CoinsScreen
import com.mousavi.comosecryptocurrencyapp.presentation.ui.theme.ComoseCryptocurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComoseCryptocurrencyAppTheme {
                val scaffoldState = rememberScaffoldState()
                val lazyListState = rememberLazyListState()
                val scope = rememberCoroutineScope()
                val viewModel: CoinsViewModel = hiltViewModel()
                val userEvent = viewModel.userEventState.value

                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        AnimatedVisibility(
                            visible = lazyListState.firstVisibleItemIndex > 0,
                            enter = fadeIn() + slideInVertically(),
                            exit = fadeOut() + slideOutVertically()
                        ) {
                            FloatingActionButton(
                                onClick = {
                                    scope.launch {
                                        lazyListState.scrollToItem(0)
                                    }
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 20.dp, horizontal = 16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(text = "Coins", color = Color.Black, fontSize = 20.sp)
                            Icon(
                                painter = painterResource(id = R.drawable.ic_sort),
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(userEvent.copy(isOrderSectionVisible = !userEvent.isOrderSectionVisible))
                                }
                            )
                        }
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.CoinListScreen.route
                        ) {
                            composable(
                                route = Screen.CoinListScreen.route
                            ) {
                                CoinsScreen(navController = navController, scrollState = lazyListState, viewModel = viewModel)
                            }
                            composable(
                                route = Screen.CoinDetailScreen.route + "/{coinId}"
                            ) {
                                CoinDetailScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComoseCryptocurrencyAppTheme {
        Greeting("Android")
    }
}