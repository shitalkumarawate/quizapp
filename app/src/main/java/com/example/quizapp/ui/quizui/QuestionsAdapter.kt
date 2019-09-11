package com.example.quizapp.ui.quizui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.model.Question
import kotlinx.android.synthetic.main.list_item.view.*


class QuestionsAdapter(private val questions: List<Question>, context: Context) : RecyclerView.Adapter<QuestionsAdapter.MyViewHolder>()
{
    var context = context
    var holder: MyViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(com.example.quizapp.R.layout.list_item, parent, false), context)
        return holder!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindViewData(questions[position])
    }

    override fun getItemCount() = questions.size

    /**
     *
     */
    class MyViewHolder(view: View, context: Context) : RecyclerView.ViewHolder(view)
    {
        private var view = view
        private var context = context
        private var selectedAnswer: Int? = null

        fun bindViewData(question: Question)
        {
            view.textViewQuestion.text = question.question
            view.radioButtonAnswer1.text = question.answers[0]
            view.radioButtonAnswer2.text = question.answers[1]
            view.radioButtonAnswer3.text = question.answers[2]
            view.radioButtonAnswer4.text = question.answers[3]

            view.radioGroup.setOnCheckedChangeListener { radioGroup, id ->
                when(id)
                {
                    com.example.quizapp.R.id.radioButtonAnswer1 -> selectedAnswer = 1
                    com.example.quizapp.R.id.radioButtonAnswer2 -> selectedAnswer = 2
                    com.example.quizapp.R.id.radioButtonAnswer3 -> selectedAnswer = 3
                    com.example.quizapp.R.id.radioButtonAnswer4 -> selectedAnswer = 4
                }
            }

            view.buttonConfirm.setOnClickListener{

                attemptedQuestion++

                if(selectedAnswer == question.correct_answer)
                    score++

                /**
                 * change color of radio button
                 */
                view.textViewQuestion.text = "Answer ${question.correct_answer} is correct"
                if(question.correct_answer == 1)
                    view.radioButtonAnswer1.setTextColor(context.resources.getColor(com.example.quizapp.R.color.green))
                else
                    view.radioButtonAnswer1.setTextColor(context.resources.getColor(com.example.quizapp.R.color.red))

                if(question.correct_answer == 2)
                    view.radioButtonAnswer2.setTextColor(context.resources.getColor(com.example.quizapp.R.color.green))
                else
                    view.radioButtonAnswer2.setTextColor(context.resources.getColor(com.example.quizapp.R.color.red))

                if(question.correct_answer == 3)
                    view.radioButtonAnswer3.setTextColor(context.resources.getColor(com.example.quizapp.R.color.green))
                else
                    view.radioButtonAnswer3.setTextColor(context.resources.getColor(com.example.quizapp.R.color.red))

                if(question.correct_answer == 4)
                    view.radioButtonAnswer4.setTextColor(context.resources.getColor(com.example.quizapp.R.color.green))
                else
                    view.radioButtonAnswer4.setTextColor(context.resources.getColor(com.example.quizapp.R.color.red))

                view.buttonConfirm.isClickable = false
                view.buttonConfirm.isEnabled = false

                if(mOnDataChangeListener != null)
                    mOnDataChangeListener?.onDataChanged(attemptedQuestion, score*2)
            }
        }
    }

    companion object{

        var attemptedQuestion: Int = 0
        var score: Int = 0
        var mOnDataChangeListener: OnDataChangeListener? = null

        fun setOnDataChangeListener(onDataChangeListener: OnDataChangeListener) {
            mOnDataChangeListener = onDataChangeListener
        }
    }

    interface OnDataChangeListener {
        fun onDataChanged(attemptedQuestion: Int, score: Int)
    }
}