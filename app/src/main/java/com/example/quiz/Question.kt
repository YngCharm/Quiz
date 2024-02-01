package com.example.quiz

data class Question(val text: String, val options: List<String>, val correctAnswer: String, val imageResourceId: Int)
