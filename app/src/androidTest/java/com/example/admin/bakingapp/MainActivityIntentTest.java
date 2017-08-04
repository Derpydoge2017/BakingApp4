package com.example.admin.bakingapp;


import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Admin on 02-Aug-17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //Check if the intents activate

    @Test
    public void MainActivityTest() {

        SystemClock.sleep(1000);

        onView(withId(R.id.rv_numbers))
                .perform(actionOnItemAtPosition(1, click()));

        onView(withId(R.id.recipeTest))
                .check(matches(withText("Recipe")));

    }

}
