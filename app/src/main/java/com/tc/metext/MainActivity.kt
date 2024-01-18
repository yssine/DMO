@file:OptIn(ExperimentalMaterial3Api::class)

package com.tc.metext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.metext.Presentation.DTTScreen
import com.tc.metext.Presentation.ExtraScreen
import com.tc.metext.Presentation.Salsa
import com.tc.metext.Presentation.Screen
import com.tc.metext.Presentation.SumScreen
import com.tc.metext.ui.theme.MeTextTheme





//val Salsa = FontFamily(Font(R.font.salsa_regular))
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeTextTheme {
                
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var navController = rememberNavController()
                    Column {
                        CenterAlignedTopAppBar(
                            title = { Text("MeText",fontFamily = Salsa,) },
                            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor =  MaterialTheme.colorScheme.secondaryContainer ),
                            navigationIcon = {
                                IconButton(onClick = {/* Do Something*/ }) {
                                    Icon(Icons.Filled.Menu, null)
                                }
                            },
//                            actions = {
////                                IconButton(onClick = {/* Do Something*/ }) {
////                                    Icon(Icons.Filled.Share, null)
////                                },
//                                IconButton(onClick = {/* Do Something*/ }) {
//                                    Icon(Icons.Filled.Menu, null)
//                                }
//                            }
                        )
//                        home()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.rout
                        ) {
                            composable(Screen.Home.rout) {
                                home(navController = navController)
                            }

                            composable(Screen.Summarize.rout)
                            {
//                                navController = navController
                                SumScreen(navController = navController)
                            }

                            composable(Screen.Extra.rout)
                            {
//                                navController = navController
                                ExtraScreen(navController = navController)
                            }

                            composable(Screen.DTT.rout)
                            {
//                                navController = navController
                                DTTScreen(navController = navController)
                            }


                        }
                }
            }
        }
    }
}
}

@Composable
fun home(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)

        {
            Button(onClick = {navController.navigate(Screen.Summarize.rout)},
                modifier = Modifier
                    .fillMaxWidth()) {
                Text(
                    text = "Summarize a text",
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            Button(onClick = {navController.navigate(Screen.Extra.rout)},
                modifier = Modifier
                    .fillMaxWidth()) {
                Text(
                    text = "Extract feeling form a text",
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            Button(onClick = {navController.navigate(Screen.DTT.rout)},
                modifier = Modifier
                    .fillMaxWidth()) {
                Text(
                    text = "Extract text from a document",
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

