package com.example.quizapp.model

data class Module(
    var number: Int,
    var name: String,
    var questions: Int)
{
    override fun toString(): String {
        return "Module(number=$number, name='$name', questions=$questions)"
    }
}

