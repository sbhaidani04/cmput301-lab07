package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity>scenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testAddCity(){
        // click on add city button
        onView(withId(R.id.button_add)).perform(click());

        // type 'Edmonton' in the EditText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // click on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // check if text 'Edmonton' is matched with any of the text displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }

    @Test
    public void testClearCity(){
        // add first city to the list
        onView(withId(R.id.button_add)).perform(click());

        // type 'Edmonton' in the EditText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // click on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // add second city to the list
        onView(withId(R.id.button_add)).perform(click());

        // type 'Vancouver' in the EditText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"));

        // click on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // clear list
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }

    @Test
    public void testListView(){
        // add first city to the list
        onView(withId(R.id.button_add)).perform(click());

        // type 'Edmonton' in the EditText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // click on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // check if in Adapter view (given id of that adapter view), there is a data
        // (which is an instance of String) located at position zero
        // If this data matches the text we provided then Voila! Our test should pass
        // You can also use anything() in place of is(instanceOf(String.class))

        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).check(matches((withText("Edmonton"))));
    }

    @Test
    public void testActivitySwitch(){
        // add first city to the list
        onView(withId(R.id.button_add)).perform(click());

        // type 'Edmonton' in the EditText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // click on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // click on city to ShowActivity
        onView(withText("Edmonton")).perform(click());

        // check that 'Edmonton' is displayed
        onView(withId(R.id.city_name)).check(matches(isDisplayed()));
    }

    @Test
    public void testConsistency(){
        // add first city to the list
        onView(withId(R.id.button_add)).perform(click());

        // type 'Edmonton' in the EditText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"));

        // click on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // click on city to ShowActivity
        onView(withText("Vancouver")).perform(click());

        // check that 'Vancouver' is displayed
        onView(withId(R.id.city_name)).check(matches(withText("Vancouver")));
    }

    @Test
    public void testBackButton(){
        // add first city to the list
        onView(withId(R.id.button_add)).perform(click());

        // type 'Edmonton' in the EditText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // click on confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // click on city to ShowActivity
        onView(withText("Edmonton")).perform(click());

        // click back button
        onView(withId(R.id.button_back)).perform(click());

        // verify back to MainActivity
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }
}
