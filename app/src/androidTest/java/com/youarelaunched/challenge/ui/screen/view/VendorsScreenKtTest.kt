package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.youarelaunched.challenge.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VendorsScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun noResultTest() {
        composeTestRule
            .onNodeWithTag(VENDORS_SCREEN_SEARCH_FIELD_TEST_TAG)
            .performTextInput("cafe")

        composeTestRule
            .onNodeWithTag(VENDORS_SCREEN_SEARCH_BUTTON_TEST_TAG)
            .performClick()

        composeTestRule
            .onNodeWithText("Sorry! No results found…")
            .assertIsDisplayed()
    }

    @Test
    fun oneItemTest() {
        composeTestRule
            .onNodeWithTag(VENDORS_SCREEN_LIST_TEST_TAG)
    }
}