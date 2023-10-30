package com.emranul.heroquiz.ui.screens.questionScreens.component

import android.text.Html
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.emranul.heroquiz.R
import com.emranul.heroquiz.ui.screens.questionScreens.QuestionViewModel
import com.emranul.heroquiz.ui.theme.textColor


@Composable
fun QuestionSection(modifier: Modifier, viewModel: QuestionViewModel) {

    val currentQuestion = viewModel.currentQuestion
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = modifier.padding(top = 50.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "${currentQuestion.value?.score ?: 0} Points", color = textColor)
                Spacer(modifier = Modifier.height(8.dp))
                currentQuestion.value?.questionImageUrl?.let {
                    /**
                     * COIL library for loading network image
                     * */
                    AsyncImage(
                        model = it,
                        contentDescription = "Question image",
                        placeholder = painterResource(id = R.drawable.logo),
                        error = painterResource(id = R.drawable.logo),
                        modifier = Modifier
                            .width(200.dp)
                            .height(180.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = (currentQuestion.value?.question ?: "").fromHtml(),
                    color = textColor,
                    textAlign = TextAlign.Center
                )

            }
        }

        ProgressCircle(viewModel) {
            viewModel.nextQuestion()
        }

    }
}

/**
 * Extension function for html view type text, in big project i make an separate Extension
 * file for all extension
 * */
fun String.fromHtml() = Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
