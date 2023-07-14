package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mUsername = intent.getStringExtra(Constants.USERNAME)
        val mCorrect = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val mTotal = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        binding.tvCongrats.text = "Congratulations ${mUsername}!"
        binding.tvScore.text = "Your score is ${mCorrect} out of ${mTotal}."

        binding.btnFinish.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}