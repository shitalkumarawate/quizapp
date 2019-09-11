package com.example.quizapp.model

class Question (
    var number: Int,
    var question: String,
    var answers: List<String>,
    var correct_answer: Int)
{
    override fun toString(): String {
        return "Question(number=$number, question='$question', answers=$answers, correctAnswer=$correct_answer)"
    }
}
