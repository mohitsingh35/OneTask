package com.ncs.onetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ncs.onetask.ViewModels.MainViewModel
import com.ncs.onetask.ui.theme.OneTaskTheme
import com.ncs.onetask.ui.theme.resbanner
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var mainViewModel:MainViewModel
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            setContent {
                Column() {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        ProgressBar()
                        Box(Modifier.padding(top = 80.dp)) {
                            Text(text = "Hold On! fetching the data")
                        }
                    }
                }
            }
            delay(2000L)
            if (mainViewModel.items.isNotEmpty()) {
                setContent {
                    val openDialog = remember { mutableStateOf(false)  }
                    Scaffold(
                      floatingActionButton = {
                          FloatingActionButton(onClick = { openDialog.value = !openDialog.value  }) {

                          }
                      }
                    ) {contentPadding ->
                        Box (modifier = Modifier.padding(contentPadding)){
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(1f)
                                    .background(
                                        Color.Green
                                    )
                          ) {
                              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                  Box(
                                      modifier = Modifier
                                          .fillMaxWidth(1f)
                                          .padding(top = 30.dp), contentAlignment = Alignment.Center
                                  ) {
                                      Text(text = "OneTask")
                                  }
                                  Spacer(modifier = Modifier.height(30.dp))

                                  LazyColumn() {
                                      items(1) {
                                          resbanner(items = mainViewModel.items)
                                      }
                                  }
                              }
                          }

                        }
                    }
                    if (openDialog.value) {
                        AlertDialog(
                            onDismissRequest = { openDialog.value = false },
                            title = { Text(text = "Internet Error") },
                            text = { Text("Please turn your internet On") },
                            confirmButton = {
                                Button(
                                    onClick = { openDialog.value = false }) {
                                    Text("OK")
                                }
                            },
                        )
                    }

                }
            }
        }
    }
}
@Composable
fun ProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier.padding(16.dp),
        color = Color.Blue,
        strokeWidth = Dp(value = 4F)
    )
}
