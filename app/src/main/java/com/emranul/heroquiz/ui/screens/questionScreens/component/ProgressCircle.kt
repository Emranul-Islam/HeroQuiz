package com.emranul.heroquiz.ui.screens.questionScreens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emranul.heroquiz.ui.screens.questionScreens.QuestionViewModel
import kotlinx.coroutines.delay


@Composable
fun ProgressCircle(
    viewModel: QuestionViewModel,
    onTimerFinished: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = CircleShape
            ),
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .border(
                    width = 5.dp,
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape
                ),
        )


        LaunchedEffect(viewModel.initialTimer.value) {
            while (viewModel.initialTimer.value > 0) {
                delay(1000)
                if (!viewModel.isPauseClick) {
                    viewModel.initialTimer.value--
                }

            }
            onTimerFinished()
        }

        CircularProgressIndicator(
            progress = (viewModel.initialTimer.value.toFloat() / 10f),
            modifier = Modifier.size(90.dp),
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = "${viewModel.initialTimer.value}",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

