package com.emranul.heroquiz.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emranul.heroquiz.R
import com.emranul.heroquiz.ui.theme.HeroQuizTheme
import com.emranul.heroquiz.ui.theme.textColor
import com.emranul.heroquiz.utils.MySharePreference

@Composable
fun HomeScreen(navController: NavHostController) {
    HeroQuizTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier
                            .height(160.dp)
                            .width(160.dp)
                    )
                    Text(
                        text = "Hero Quiz",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = textColor
                    )
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "High Score")
                        Text(text = score(), modifier = Modifier.padding(top = 10.dp))
                    }

                }

                Text(
                    text = "Let's make new high score",
                    color = textColor
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate("question")
                    }) {
                    Text(text = "Start")
                }
            }
        }
    }
}
/**
 * Get local saved score
 * */
fun score(): String {
    val score = MySharePreference.getScore()
    return "$score"
}
