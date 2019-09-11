package com.example.quizapp.ui.quizui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.model.QuizInfo
import com.example.quizapp.utils.ConstantKeys.JSON_FILE_NAME
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity(), QuizePageView
{
    private val mPresenter: QuizPresenter = QuizPresenterImpl()
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewAdapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.quizapp.R.layout.activity_quiz)

        mPresenter.setView(this)
        mPresenter.loadAssetJSONFile(this, JSON_FILE_NAME)
    }

    override fun displayLoading() {
        waitProgressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        waitProgressBar.visibility = View.GONE
    }

    override fun displayQuizPage(result: QuizInfo) {
        dismissLoading()
        Log.d("Quiz Info", result.toString())
        updateUI(result)
    }

    override fun displayError(error: String?) {
        Log.e("Error", error)
    }

    /**
     * Update UI
     */
    private fun updateUI(quizInfo: QuizInfo)
    {
        linearLayoutManager = LinearLayoutManager(this)
        viewAdapter = QuestionsAdapter(quizInfo.questions, this)

        recyclerView = findViewById<RecyclerView>(com.example.quizapp.R.id.recyclerView).apply {
            layoutManager = linearLayoutManager
            adapter = viewAdapter
        }

        QuestionsAdapter.setOnDataChangeListener(object : QuestionsAdapter.OnDataChangeListener
        {
            override fun onDataChanged(attemptedQuestion: Int, score: Int) {
                textViewNoOfQuestions.text = "Questions : $attemptedQuestion/5"
                textViewHighScore.text = "Score : $score/10"

                if(attemptedQuestion == 5)
                {
                    AlertDialog.Builder(this@QuizActivity, android.R.style.Theme_Material_Light_Dialog_Alert)
                        .setTitle("Note")
                        .setMessage("You scored $score out of 10")
                        .setPositiveButton(android.R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                            this@QuizActivity.finish()
                        }.show()
                }

            }
        })
    }
}
