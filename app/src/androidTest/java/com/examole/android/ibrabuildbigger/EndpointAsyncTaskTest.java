package com.examole.android.ibrabuildbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.util.Log;

import com.examole.android.ibrabuildbigger.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import callback.Callbacks;
import data.EndpointAsyncTask;

import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dell on 4/16/2018.
 */



@RunWith(AndroidJUnit4.class)

public class EndpointAsyncTaskTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testDoInBackground() throws InterruptedException, ExecutionException, TimeoutException {

        EndpointAsyncTask endpointAsyncTask=new EndpointAsyncTask(new Callbacks.EndpointAsyncTaskCallback() {
            @Override
            public void onEndpointAsyncTaskDone(String result) {

            }
        });
        endpointAsyncTask.execute(mActivityRule.getActivity().getApplicationContext());
        try {
            String result=endpointAsyncTask.get(30, SECONDS);
            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}

