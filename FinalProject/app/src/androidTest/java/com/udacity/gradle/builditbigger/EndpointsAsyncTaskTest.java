package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by hend on 4/3/16.
 */
public class EndpointsAsyncTaskTest extends AndroidTestCase {

    private CountDownLatch signal;
    String mJoke;
    private Exception mError = null;

    @Override
    public void setUp() throws Exception{
        super.setUp();
        signal = new CountDownLatch(1);
    }


    public void testEndPoint() throws InterruptedException {

        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.setListener(new EndpointsAsyncTask.EndpointAsyncListener() {
            @Override
            public void onComplete(String result, Exception e) {
                mJoke = result;
                mError = e;
                signal.countDown();
            }
        }).execute(getContext());

        signal.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mJoke));
        Log.i("Test","Test");

    }

}