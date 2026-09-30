package com.example

import com.example.chemlabx.data.SubscriptionManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {
    @Before
    fun setUp() {
        SubscriptionManager.resetForTesting()
    }

    @Test
    fun testFreeQuestionsLimitAndSubscription() {
        assertEquals(3, SubscriptionManager.FREE_QUESTION_LIMIT)
        assertEquals(3, SubscriptionManager.remainingFreeQuestions())
        assertTrue(SubscriptionManager.canAskQuestion())

        // 1st question
        SubscriptionManager.recordQuestionAsked()
        assertEquals(2, SubscriptionManager.remainingFreeQuestions())
        assertTrue(SubscriptionManager.canAskQuestion())

        // 2nd question
        SubscriptionManager.recordQuestionAsked()
        assertEquals(1, SubscriptionManager.remainingFreeQuestions())
        assertTrue(SubscriptionManager.canAskQuestion())

        // 3rd question (final free question)
        SubscriptionManager.recordQuestionAsked()
        assertEquals(0, SubscriptionManager.remainingFreeQuestions())
        assertFalse(SubscriptionManager.canAskQuestion())

        // 4th attempt cannot proceed without subscription
        assertFalse(SubscriptionManager.isProSubscribed)

        // Subscribe to ChemTutor Pro ($3/mo)
        val success = SubscriptionManager.subscribePro()
        assertTrue(success)
        assertTrue(SubscriptionManager.isProSubscribed)
        assertTrue(SubscriptionManager.canAskQuestion())
    }
}
