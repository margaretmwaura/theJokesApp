package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest
{
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAsyncTask() throws Throwable {

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        String randomJoke =  endpointsAsyncTask.execute(activityTestRule.getActivity()).get();
        assertNotNull(randomJoke);
    }
}
