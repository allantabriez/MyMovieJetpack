package com.example.jetpacksubmission.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.utils.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun checkMovies() {
        Espresso.onView(allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), ViewMatchers.isCompletelyDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), ViewMatchers.isCompletelyDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun checkDetails() {
        Espresso.onView(allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), ViewMatchers.isCompletelyDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(ViewMatchers.withId(R.id.detailTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailTitle)).check(ViewAssertions.matches(ViewMatchers.withText("Mulan")))
        Espresso.onView(ViewMatchers.withId(R.id.filmImage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailReleaseImage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.filmImage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailRelease)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailPopularity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailGenre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailVoteAverage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.detailRuntime)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkFavorites() {
        Espresso.onView(allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), ViewMatchers.isCompletelyDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFab)).perform(click())
        Espresso.onView(isRoot()).perform(pressBack())
        Espresso.onView(ViewMatchers.withId(R.id.favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favorite)).perform(click())
        Espresso.onView(allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), ViewMatchers.isCompletelyDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(isRoot()).perform(pressBack())
        Espresso.onView(allOf(ViewMatchers.withId(R.id.recyclerView), ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE), ViewMatchers.isCompletelyDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(ViewMatchers.withId(R.id.favoriteFab)).perform(click())
        Espresso.onView(isRoot()).perform(pressBack())
    }
}