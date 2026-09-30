package com.example.chemlabx.data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object SubscriptionManager {
    const val FREE_QUESTION_LIMIT = 3
    const val SUBSCRIPTION_PRICE = "$3.00 / month"

    private const val PREFS_NAME = "chemlabx_subscription_prefs"
    private const val KEY_QUESTIONS_USED = "key_questions_used"
    private const val KEY_IS_PRO = "key_is_pro_subscribed"

    private var sharedPreferences: SharedPreferences? = null

    var questionsUsed by mutableIntStateOf(0)
        private set

    var isProSubscribed by mutableStateOf(false)
        private set

    fun init(context: Context) {
        if (sharedPreferences == null) {
            val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            sharedPreferences = prefs
            questionsUsed = prefs.getInt(KEY_QUESTIONS_USED, 0)
            isProSubscribed = prefs.getBoolean(KEY_IS_PRO, false)
        }
    }

    fun canAskQuestion(): Boolean {
        return isProSubscribed || questionsUsed < FREE_QUESTION_LIMIT
    }

    fun remainingFreeQuestions(): Int {
        return if (isProSubscribed) 999 else maxOf(0, FREE_QUESTION_LIMIT - questionsUsed)
    }

    fun recordQuestionAsked() {
        if (!isProSubscribed) {
            questionsUsed++
            sharedPreferences?.edit()?.putInt(KEY_QUESTIONS_USED, questionsUsed)?.apply()
        }
    }

    fun subscribePro(): Boolean {
        isProSubscribed = true
        sharedPreferences?.edit()?.putBoolean(KEY_IS_PRO, true)?.apply()
        return true
    }

    fun cancelSubscription() {
        isProSubscribed = false
        sharedPreferences?.edit()?.putBoolean(KEY_IS_PRO, false)?.apply()
    }

    fun resetForTesting() {
        questionsUsed = 0
        isProSubscribed = false
        sharedPreferences?.edit()
            ?.putInt(KEY_QUESTIONS_USED, 0)
            ?.putBoolean(KEY_IS_PRO, false)
            ?.apply()
    }
}
