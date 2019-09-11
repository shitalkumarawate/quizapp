package com.example.quizapp.ui.quizui

import android.content.Context

interface QuizPresenter
{
    fun loadAssetJSONFile(context: Context, filename:  String)
    fun setView(quizPageView: QuizePageView)
}