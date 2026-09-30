package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.chemlabx.data.CompoundData
import com.example.chemlabx.data.ElementData
import com.example.chemlabx.data.ReactionData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("ChemLab X", appName)
  }

  @Test
  fun `verify full periodic table has 118 elements`() {
    val elements = ElementData.allElements
    assertEquals(118, elements.size)
    assertEquals("Hydrogen", elements.first().name)
    assertEquals("Oganesson", elements.last().name)
  }

  @Test
  fun `verify compounds and reaction data`() {
    val allCompounds = CompoundData.compounds
    assertEquals(62, allCompounds.size)
    assertNotNull(allCompounds.find { it.id == "glucose" })
    assertNotNull(allCompounds.find { it.id == "caffeine" })
    assertNotNull(allCompounds.find { it.id == "nitric_acid" })
    assertNotNull(allCompounds.find { it.id == "aspirin" })
    assertNotNull(allCompounds.find { it.id == "urea" })

    val reaction = ReactionData.findReaction("HCl", "NaOH")
    assertNotNull(reaction)
    assertEquals("Acid-Base Neutralization", reaction?.name)
  }

  @Test
  fun `verify chem tutor free question quota and subscription`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    com.example.chemlabx.data.SubscriptionManager.init(context)
    com.example.chemlabx.data.SubscriptionManager.resetForTesting()

    assertEquals(3, com.example.chemlabx.data.SubscriptionManager.remainingFreeQuestions())
    assertTrue(com.example.chemlabx.data.SubscriptionManager.canAskQuestion())

    // Ask question 1
    com.example.chemlabx.data.SubscriptionManager.recordQuestionAsked()
    assertEquals(2, com.example.chemlabx.data.SubscriptionManager.remainingFreeQuestions())
    assertTrue(com.example.chemlabx.data.SubscriptionManager.canAskQuestion())

    // Ask question 2
    com.example.chemlabx.data.SubscriptionManager.recordQuestionAsked()
    assertEquals(1, com.example.chemlabx.data.SubscriptionManager.remainingFreeQuestions())
    assertTrue(com.example.chemlabx.data.SubscriptionManager.canAskQuestion())

    // Ask question 3
    com.example.chemlabx.data.SubscriptionManager.recordQuestionAsked()
    assertEquals(0, com.example.chemlabx.data.SubscriptionManager.remainingFreeQuestions())
    org.junit.Assert.assertFalse(com.example.chemlabx.data.SubscriptionManager.canAskQuestion())

    // Subscribe for $3
    com.example.chemlabx.data.SubscriptionManager.subscribePro()
    assertTrue(com.example.chemlabx.data.SubscriptionManager.isProSubscribed)
    assertTrue(com.example.chemlabx.data.SubscriptionManager.canAskQuestion())
  }

  @Test
  fun `verify organic chemistry functional groups and reactions`() {
    val groups = com.example.chemlabx.data.OrganicChemistryData.allFunctionalGroups
    assertEquals(20, groups.size)

    // Verify Carboxylic Acids is priority #1
    val carboxylic = groups.find { it.id == "carboxylic_acids" }
    assertNotNull(carboxylic)
    assertEquals(1, carboxylic?.priorityOrder)
    assertEquals("-oic acid (or -carboxylic acid if cyclic/poly)", carboxylic?.iupacSuffix)
    assertTrue(carboxylic!!.reactions.isNotEmpty())

    // Verify IUPAC priority list
    val priorityList = com.example.chemlabx.data.OrganicChemistryData.iupacPriorityList
    assertEquals(19, priorityList.size)

    // Verify reactions search
    val foundReaction = com.example.chemlabx.data.OrganicChemistryData.findReactionById("fischer_esterification")
    assertNotNull(foundReaction)
    assertEquals("Fischer Esterification", foundReaction?.second?.name)
  }

  @Test
  fun `verify scientific citations and balanced equation validation`() {
    val elements = ElementData.allElements
    assertTrue(elements.all { it.citationSource.isNotBlank() })

    val reactions = ReactionData.reactions
    assertTrue(reactions.all { it.citationSource.isNotBlank() })
    assertTrue(reactions.all { ReactionData.validateBalancedEquation(it.equation) })

    // Dangerous reactions must have simulation notices
    val dangerous = reactions.filter { it.isDangerousSimulationOnly }
    assertTrue(dangerous.isNotEmpty())
    assertTrue(dangerous.all { it.simulationWarningNotice != null })
  }

  @Test
  fun `verify localization across all three languages`() {
    com.example.chemlabx.data.LocalizationManager.currentLanguage = com.example.chemlabx.data.AppLanguage.ENGLISH
    assertEquals("ChemLab X", com.example.chemlabx.data.LocalizationManager.getString("app_title"))

    com.example.chemlabx.data.LocalizationManager.currentLanguage = com.example.chemlabx.data.AppLanguage.HINDI
    assertEquals("ChemLab X", com.example.chemlabx.data.LocalizationManager.getString("app_title"))
    assertTrue(com.example.chemlabx.data.LocalizationManager.getString("nav_periodic").isNotBlank())

    com.example.chemlabx.data.LocalizationManager.currentLanguage = com.example.chemlabx.data.AppLanguage.FRENCH
    assertTrue(com.example.chemlabx.data.LocalizationManager.getString("nav_periodic").isNotBlank())
  }
}

