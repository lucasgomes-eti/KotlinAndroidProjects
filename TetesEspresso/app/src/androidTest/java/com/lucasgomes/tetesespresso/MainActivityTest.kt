package com.lucasgomes.tetesespresso

import android.app.Activity
import android.app.Instrumentation
import android.support.test.espresso.ViewAssertion
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers


/**
 * Created by Lucas Gomes on 14/09/2017.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun whenActivityIsLaunched_shouldDisplayInitialState() {
        Intents.init()

        onView(withId(R.id.editTextUserInput)).perform(typeText("Lorem ipsum."), closeSoftKeyboard())

        val matcher = IntentMatchers.hasComponent(SettingsActivity::class.java.name)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, null)
        Intents.intending(matcher).respondWith(result)

        onView(withId(R.id.changeTextButton)).perform(click())

        Intents.intended(matcher)

        Intents.release()
    }
}
