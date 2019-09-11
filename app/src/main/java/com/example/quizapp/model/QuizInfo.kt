package com.example.quizapp.model

class QuizInfo(
    var module: Module,
    var questions: List<Question>)
{
    override fun toString(): String {
        return "QuizInfo(module=$module, questions=$questions)"
    }
}
