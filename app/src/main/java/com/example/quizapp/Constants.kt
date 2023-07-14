package com.example.quizapp

object Constants {

    const val USERNAME = "username"
    const val CORRECT_ANSWERS = "correct_answers"
    const val TOTAL_QUESTIONS = "total_questions"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val q1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_pakistan,
            "Saudi Arabia",
            "Turkey",
            "Pakistan",
            "Azerbaijan",
            3
        )

        val q2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_azerbaijan,
            "Turkmenistan",
            "Kyrgyzstan",
            "Bolivia",
            "Azerbaijan",
            4
        )

        val q3 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_saudia,
            "Saudi Arabia",
            "Iran",
            "Iraq",
            "UAE",
            1
        )

        val q4 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_bolivia,
            "Uzbekistan",
            "Bolivia",
            "Somalia",
            "Nepal",
            2
        )

        val q5 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_portugal,
            "Spain",
            "Italy",
            "Portugal",
            "Egypt",
            3
        )

        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        questionsList.add(q4)
        questionsList.add(q5)

        return questionsList
    }

}