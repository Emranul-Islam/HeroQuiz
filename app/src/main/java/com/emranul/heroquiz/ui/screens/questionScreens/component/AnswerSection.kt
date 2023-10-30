package com.emranul.heroquiz.ui.screens.questionScreens.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.emranul.heroquiz.ui.screens.questionScreens.QuestionViewModel
import com.emranul.heroquiz.ui.theme.textColor


@Composable
fun AnswerSection(modifier: Modifier, viewModel: QuestionViewModel) {
    Column(
        modifier = modifier.padding(
            start = 20.dp,
            end = 20.dp,
            bottom = 40.dp
        )
    ) {
        val question = viewModel.currentQuestion.value?.answers
        question?.a?.let {
            AnswerButton(
                question = it,
                questionOption = "A",
                positiveAnswer = viewModel.positiveAnswer,
                wrongAnswer = viewModel.wrongAnswer
            ) {
                viewModel.checkAnswer("A")
            }
        }
        question?.b?.let {
            AnswerButton(
                question = it,
                questionOption = "B",
                positiveAnswer = viewModel.positiveAnswer,
                wrongAnswer = viewModel.wrongAnswer
            ) {
                viewModel.checkAnswer("B")
            }
        }
        question?.c?.let {
            AnswerButton(
                question = it,
                questionOption = "C",
                positiveAnswer = viewModel.positiveAnswer,
                wrongAnswer = viewModel.wrongAnswer
            ) {
                viewModel.checkAnswer("C")
            }
        }
        question?.d?.let {
            AnswerButton(
                question = it,
                questionOption = "D",
                positiveAnswer = viewModel.positiveAnswer,
                wrongAnswer = viewModel.wrongAnswer
            ) {
                viewModel.checkAnswer("D")
            }
        }


    }
}

@Composable
fun AnswerButton(
    question: String,
    questionOption: String,
    positiveAnswer: MutableState<String>,
    wrongAnswer: MutableState<String>,
    onCLick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = when (questionOption) {
                positiveAnswer.value -> {
                    Color.Green
                }
                wrongAnswer.value -> {
                    Color.Red
                }
                else -> MaterialTheme.colorScheme.background
            }
        ),
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(50.dp)
            .clickable {
                onCLick()
            },

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = question,
                color = if (questionOption == positiveAnswer.value || questionOption == wrongAnswer.value) Color.Black else textColor
            )
        }
    }


}
