package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizQuestionsBinding

    private lateinit var mQuestionsList: ArrayList<Question>
    private var mPosition : Int = 1
    var mSelectedOption : Int = 0
    var mAnswered : Boolean = false
    private var mUsername: String? = null
    var mCorrectAnswers: Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_quiz_questions)

        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mQuestionsList = Constants.getQuestions()

        binding.tvOption1.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        mUsername = intent.getStringExtra(Constants.USERNAME)

        setQuestion()

        binding.btnSubmit.setOnClickListener {
            if(mSelectedOption==0){
                if(mAnswered){
                    mPosition++
                    when {
                        mPosition <=mQuestionsList.size -> {
                            setQuestion()
                            mAnswered=false
                        }
                        else -> {
                            intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USERNAME, mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }


                }
                else{
                    Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                val question = mQuestionsList.get(mPosition-1)
                if(mSelectedOption != question.correctOption) {
                    answerView(mSelectedOption, R.drawable.wrong_bg_option)
                }
                else{
                    mCorrectAnswers++
                }
                answerView(question.correctOption, R.drawable.correct_bg_option)

                if(mPosition == mQuestionsList.size){
                    binding.btnSubmit.text = "Finish"
                }
                else{
                    binding.btnSubmit.text = "Next"
                }
                mAnswered=true
                mSelectedOption = 0


            }
        }

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        binding.tvOption1.let {
            options.add(0, it)
        }

        binding.tvOption2.let {
            options.add(1, it)
        }

        binding.tvOption3.let {
            options.add(2, it)
        }

        binding.tvOption4.let {
            options.add(3, it)
        }

        for (i in options) {
            val customTypeface = ResourcesCompat.getFont(this, R.font.montserrat_semibold)
            i.typeface = customTypeface
            i.background = ContextCompat.getDrawable(this, R.drawable.default_bg_option)
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOpt : Int){
        defaultOptionsView()

        mSelectedOption = selectedOpt

        val customTypeface = ResourcesCompat.getFont(this, R.font.montserrat_extrabold)
        tv.typeface = customTypeface
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_bg_option)


    }

    private fun setQuestion() {

        defaultOptionsView()

        val question: Question = mQuestionsList[mPosition - 1]
        binding.tvQuestion.text = question.question
        binding.ivFlag.setImageResource(question.image)
        binding.progressBar.progress = mPosition
        binding.tvProgress.text = "$mPosition/${binding.progressBar.max}"
        binding.tvOption1.text = question.optionOne
        binding.tvOption2.text = question.optionTwo
        binding.tvOption3.text = question.optionThree
        binding.tvOption4.text = question.optionFour

        if (mPosition == mQuestionsList.size){
            binding.btnSubmit.text = "Finish"
        }
        else{
            binding.btnSubmit.text = "Submit"
        }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.tvOption1 -> {
                selectedOptionView(binding.tvOption1, 1)
            }
            R.id.tvOption2 -> {
                selectedOptionView(binding.tvOption2, 2)
            }
            R.id.tvOption3 -> {
                selectedOptionView(binding.tvOption3, 3)
            }
            R.id.tvOption4 -> {
                selectedOptionView(binding.tvOption4, 4)
            }
            R.id.btnSubmit -> {}
        }
    }

    private fun answerView(answer:Int, drawable: Int){
        when(answer){
            1 -> {
                binding.tvOption1.background =
                    ContextCompat.getDrawable(this, drawable)
            }

            2 -> {
                binding.tvOption2.background =
                    ContextCompat.getDrawable(this, drawable)
            }

            3 -> {
                binding.tvOption3.background =
                    ContextCompat.getDrawable(this, drawable)
            }

            4 -> {
                binding.tvOption4.background =
                    ContextCompat.getDrawable(this, drawable)
            }
        }
    }
}