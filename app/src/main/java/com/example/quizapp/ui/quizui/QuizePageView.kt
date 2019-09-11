package com.example.quizapp.ui.quizui

import com.example.quizapp.model.QuizInfo

interface QuizePageView {
    fun displayLoading()

    fun dismissLoading()

    fun displayQuizPage(result: QuizInfo)

    fun displayError(error: String?)
}