package com.emranul.heroquiz.ui.screens.questionScreens

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emranul.heroquiz.data.model.Question
import com.emranul.heroquiz.data.network.Resource
import com.emranul.heroquiz.data.repository.QuizRepository
import com.emranul.heroquiz.utils.Const
import com.emranul.heroquiz.utils.MySharePreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuizRepository) : ViewModel() {

    var score = mutableIntStateOf(0)
    var error = mutableStateOf("")
    var isVisible = mutableStateOf(true)

    private var questionList: List<Question> = emptyList()
    var questionCount = mutableStateOf("")
    var currentQuestion = mutableStateOf<Question?>(null)

    var isLoading = mutableStateOf(true)

    var positiveAnswer = mutableStateOf("")
    var wrongAnswer = mutableStateOf("")

    var initialTimer = mutableIntStateOf(10)  //question timer value

    var quizComplete = mutableStateOf(false)

    /**
     * When user select any answer we have to disable timer
     * and all the answer button , so this variable check on
     * every button click and timer loop
     * */
    var isPauseClick = false


    init {

        /**
         * Quiz api call here when viewModel initialize
         * */
        viewModelScope.launch {
            when (val response = repository.quizList()) {
                is Resource.Error -> {
                    isLoading.value = false
                    error.value = response.message.toString()
                }

                is Resource.Success -> {
                    questionList = response.data?.questions ?: emptyList()
                    nextQuestion()
                    isLoading.value = false
                }
            }

        }


    }


    /**
     * Here's all about changing question from
     * question list and update ui with current question
     * */
    private var currentIndex = -1
    fun nextQuestion() {
        isVisible.value = false
        viewModelScope.launch {
            delay(Const.animationDelay)
            wrongAnswer.value = ""
            positiveAnswer.value = ""
            currentIndex++
            questionCount.value = "${currentIndex + 1}/${questionList.size}"
            if (questionList.size >= currentIndex + 1) {
                currentQuestion.value = questionList[currentIndex]
                initialTimer.value = 10
                delay(100)
                isVisible.value = true
            } else {
                val previousScore = MySharePreference.getScore()
                if (score.value > previousScore) {
                    MySharePreference.saveScore(score.value)
                }
                currentQuestion.value = null
                quizComplete.value = true
            }

        }
    }

    /**
     * After answer button Click here we chake the
     * answer and store positive answer and wrong answer
     * */
    fun checkAnswer(answer: String) {
        if (isPauseClick) {
            return
        }
        if (answer == currentQuestion.value?.correctAnswer) {
            wrongAnswer.value = ""
            positiveAnswer.value = answer
            score.value = score.value + (currentQuestion.value?.score?:0)
        } else {
            positiveAnswer.value = currentQuestion.value?.correctAnswer ?: ""
            wrongAnswer.value = answer
        }
        viewModelScope.launch {
            isPauseClick = true
            delay(2000)
            isPauseClick = false
            nextQuestion()
        }
    }

}