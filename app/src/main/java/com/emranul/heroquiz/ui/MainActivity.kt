package com.emranul.heroquiz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emranul.heroquiz.ui.screens.HomeScreen
import com.emranul.heroquiz.ui.screens.questionScreens.QuestionScreen
import com.emranul.heroquiz.ui.screens.questionScreens.QuestionViewModel
import com.emranul.heroquiz.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Const.routeHomeScreen) {
        composable(Const.routeHomeScreen) {
            HomeScreen(navController)
        }
        composable(Const.routeQuestionScreen) {
            val viewModel = hiltViewModel<QuestionViewModel>()
            QuestionScreen(navController, viewModel)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}
