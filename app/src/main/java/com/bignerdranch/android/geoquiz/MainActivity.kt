package com.bignerdranch.android.geoquiz

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var previosButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        previosButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.lastIndex
            Toast.makeText(this, "$currentIndex", Toast.LENGTH_SHORT).show()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
//        val correctAnswer = questionBank[currentIndex].answer
//        val messageResId = if (userAnswer == correctAnswer) {
//            R.string.correct_toast
//        } else {
//            R.string.incorrect_toast
//        }
//        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
//            .show()
//    }
//
        val correctAnswer=questionBank[currentIndex].answer
        val messageAnswer=if (userAnswer == correctAnswer)R.string.correct_toast else R.string.incorrect_toast

        val toast1=Toast.makeText(this,getString(messageAnswer),Toast.LENGTH_SHORT)
        val _toast1:TextView = toast1.view!!.findViewById(android.R.id.message)
        toast1.setGravity(Gravity.CENTER,5,5)
        if (userAnswer==correctAnswer){
            _toast1.setTextColor(Color.BLUE)
            _toast1.setTextSize(24F)
            toast1.show()
        }else{
            _toast1.setTextColor(Color.RED)
            _toast1.setTextSize(24F)
            toast1.show()
        } }
    fun showToase(message:Int){
    }




}
