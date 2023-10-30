package com.emranul.heroquiz.ui.screens.questionScreens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emranul.heroquiz.R
import com.emranul.heroquiz.ui.screens.questionScreens.QuestionViewModel
import com.emranul.heroquiz.ui.theme.textColor


@Composable
fun QuizCompleteSection(viewModel: QuestionViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_success),
            contentDescription = "error image",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Congratulation",
            color = textColor,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Your Score is ${viewModel.score.value}",
            color = textColor,
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Back to Home")
        }
    }
}