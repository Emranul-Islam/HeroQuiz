package com.emranul.heroquiz.ui.screens.questionScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.emranul.heroquiz.R
import com.emranul.heroquiz.ui.screens.questionScreens.component.AnswerSection
import com.emranul.heroquiz.ui.screens.questionScreens.component.QuestionSection
import com.emranul.heroquiz.ui.screens.questionScreens.component.QuizCompleteSection
import com.emranul.heroquiz.ui.theme.HeroQuizTheme
import com.emranul.heroquiz.ui.theme.textColor
import com.emranul.heroquiz.utils.Const

@Composable
fun QuestionScreen(navController: NavHostController, viewModel: QuestionViewModel) {
    HeroQuizTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (viewModel.isLoading.value) {
                LoadingSection()
            } else {
                if (viewModel.error.value.isNotEmpty()) {
                    val errorText = viewModel.error.value
                    ErrorSection(errorText = errorText)
                } else {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        AnimatedVisibility(
                            visible = !viewModel.quizComplete.value,
                            enter = scaleIn(animationSpec = tween(durationMillis = Const.animationDelay.toInt())),
                            exit = scaleOut(animationSpec = tween(durationMillis = Const.animationDelay.toInt()))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Question:${viewModel.questionCount.value}",
                                    color = textColor
                                )
                                Text(text = "Score ${viewModel.score.value}", color = textColor)
                            }
                        }
                        AnimatedVisibility(
                            visible = viewModel.isVisible.value,
                            enter = scaleIn(animationSpec = tween(durationMillis = Const.animationDelay.toInt())),
                            exit = scaleOut(animationSpec = tween(durationMillis = Const.animationDelay.toInt()))
                        ) {
                            QuestionSection(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, end = 8.dp),
                                viewModel
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        AnimatedVisibility(
                            visible = viewModel.isVisible.value,
                            enter = scaleIn(animationSpec = tween(durationMillis = Const.animationDelay.toInt())),
                            exit = scaleOut(animationSpec = tween(durationMillis = Const.animationDelay.toInt()))
                        ) {
                            AnswerSection(modifier = Modifier, viewModel)
                        }
                    }
                }

            }

            if (viewModel.quizComplete.value) {
                QuizCompleteSection(viewModel = viewModel, navController)
            }
        }
    }
}

/**
 * Api call simple loading , normally i try to use ShimmerEffect on
 * loading section but in here i decided to go with simple way :)
 * */
@Composable
fun LoadingSection() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

/**
 * If api call fetch any error then this error design will show
 * */
@Composable
fun ErrorSection(errorText: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "error image",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = errorText, color = textColor)
    }
}


