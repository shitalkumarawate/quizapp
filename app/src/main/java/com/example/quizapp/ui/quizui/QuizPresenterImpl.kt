package com.example.quizapp.ui.quizui

import android.content.Context
import com.example.quizapp.model.QuizInfo
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset

class QuizPresenterImpl: QuizPresenter
{
    private var quizPageView: QuizePageView? = null

    override fun setView(quizPageView: QuizePageView) {
        this.quizPageView = quizPageView
    }

    /**
     * Load json file from asset folder
     */
    override fun loadAssetJSONFile(context: Context, filename: String) {
        try
        {

            quizPageView?.displayLoading()

            /**
             * Read the file form asset
             */
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            /**
             * Convert to response to data object and Pass response to view
             */
            quizPageView?.displayQuizPage(Gson().fromJson<QuizInfo>(String(buffer, Charset.forName("UTF-8")), QuizInfo::class.java))

        } catch (ex: IOException) {
            quizPageView?.displayError(ex.toString())
        }
    }
}