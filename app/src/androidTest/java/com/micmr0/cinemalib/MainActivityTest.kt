package com.micmr0.cinemalib

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    val composeTestRuleMainActivity = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainActivity_openSettingsScreenTest() {
        composeTestRuleMainActivity.onNodeWithContentDescription("settings")
            .performClick()

        composeTestRuleMainActivity.onNodeWithText("Select language").assertIsDisplayed()
        composeTestRuleMainActivity.onNodeWithText("Theme").assertIsDisplayed()
        composeTestRuleMainActivity.onNodeWithText("Back").assertIsDisplayed()
    }

    @Test
    fun mainActivity_CategoriesTest() {
        composeTestRuleMainActivity.onNodeWithText("Most popular").assertIsDisplayed()
        composeTestRuleMainActivity.onNodeWithText("Top rated").assertIsDisplayed()
        composeTestRuleMainActivity.onNodeWithText("Upcoming").assertIsDisplayed()
    }
}