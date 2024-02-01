package com.example.quiz

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var quizTextView: TextView
    private lateinit var java: Button
    private lateinit var python: Button
    private lateinit var cPlus: Button
    private lateinit var javaScript: Button
    private lateinit var imageView: ImageView

    private val questions = mutableListOf(
        Question("Какой язык программирования использует ключевое слово \"def\" для определения функций?", listOf("Java", "Python", "C++", "JavaScript"), "Python", R.drawable.photo1),
        Question("Какова основная цель использования инструкции \"try-catch\" в языках программирования?", listOf("Определение переменных", "Объявление функций", "Обработка исключений", "Управление циклами"), "Обработка исключений", R.drawable.photo2),
        Question("Какие из перечисленных типов данных являются примитивными в языке программирования Java?", listOf("int", "float", "char", "double"), "int", R.drawable.photo3),
        Question("Какие из перечисленных типов данных являются примитивными в языке программирования Java?", listOf("int, float, char, double", " String, boolean, array, object", " byte, short, long, enum", " function, pointer, struct, void"), " int, float, char, double", R.drawable.photo4),
        Question("Что такое Git?", listOf("Язык программирования", "Система управления базами данных", "Система контроля версий", "Графический интерфейс разработки"), "Система контроля версий", R.drawable.photo5)
    )

    private var currentQuestionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)
        imageView = findViewById(R.id.imageView)

        // Инициализация кнопок и других элементов
        quizTextView = findViewById(R.id.quizTextView)
        java = findViewById(R.id.java)
        python = findViewById(R.id.python)
        cPlus = findViewById(R.id.cPlus)
        javaScript = findViewById(R.id.javaScript)

        // Перемешиваем вопросы перед первым вопросом
        questions.shuffle()

        // Показываем первый вопрос
        showRandomQuestion()

        // Добавьте обработчики нажатия на кнопки
        java.setOnClickListener { checkAnswer(java.text.toString()) }
        python.setOnClickListener { checkAnswer(python.text.toString()) }
        cPlus.setOnClickListener { checkAnswer(cPlus.text.toString()) }
        javaScript.setOnClickListener { checkAnswer(javaScript.text.toString()) }
    }

    private fun showRandomQuestion() {
        if (currentQuestionIndex < questions.size) {
            // Получаем текущий вопрос
            val currentQuestion = questions[currentQuestionIndex]

            // Устанавливаем текст вопроса
            quizTextView.text = currentQuestion.text

            // Перемешиваем варианты ответов для разнообразия
            val shuffledOptions = currentQuestion.options.shuffled()

            // Устанавливаем тексты для кнопок
            java.text = shuffledOptions[0]
            python.text = shuffledOptions[1]
            cPlus.text = shuffledOptions[2]
            javaScript.text = shuffledOptions[3]

            // Устанавливаем изображение
            imageView.setImageResource(currentQuestion.imageResourceId)

            // Увеличиваем индекс текущего вопроса
            currentQuestionIndex++
        } else {
            // Все вопросы заданы, выполните необходимые действия
            showResult()
        }
    }

    private fun checkAnswer(selectedAnswer: String) {
        val currentQuestion = questions[currentQuestionIndex - 1] // Текущий вопрос до инкремента

        if (selectedAnswer == currentQuestion.correctAnswer) {
            // Ответ правильный, увеличиваем счетчик правильных ответов
            correctAnswers++
        }

        // Переход к следующему вопросу
        showRandomQuestion()
    }

    private fun showResult() {
        // Показываем результат, например, с помощью Toast
        Toast.makeText(this, "Вы ответили правильно на $correctAnswers из ${questions.size} вопросов", Toast.LENGTH_LONG).show()
    }
}
