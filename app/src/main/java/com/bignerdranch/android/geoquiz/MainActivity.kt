package com.bignerdranch.android.geoquiz

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
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var resultt: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var points =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        resultt=findViewById(R.id.resultt)
        prevButton=findViewById(R.id.pre_Button)



        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1)
            updateQuestion()
        }
        updateQuestion()

        prevButton.setOnClickListener{
                currentIndex = (currentIndex - 1)
                updateQuestion()
            }



        }



    private fun updateQuestion() {
        when (currentIndex) { // disable next button in the last question and disable pre in the first question
            in 1..questionBank.size - 2 -> { prevButton.isEnabled = true ; nextButton.isEnabled = true}
            questionBank.size - 1 -> {nextButton.isEnabled = false
            }
            0 -> prevButton.isEnabled = false } // end of when

        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            points +=1
            resultt.setText(" gained points : $points out of ${questionBank.size}")
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
        val toast=Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP,0,0)
        toast.show()
    }
}
