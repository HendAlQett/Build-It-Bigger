package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hend.gcemodule.backend.myApi.MyApi;
import com.example.hend.jokesdisplayerlib.JokeDisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by hend on 4/2/16.
 */
public class EndpointsAsyncTask extends AsyncTask <Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private EndpointAsyncListener mListener = null;
    private Exception mError = null;
    private ProgressBar progressBar;

    public EndpointsAsyncTask(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
    }

    public EndpointsAsyncTask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar!=null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];


        try {
            return myApiService.sayJoke().execute().getJoke();
        } catch (IOException e) {
            mError = e;
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {


        if (this.mListener != null) {
            this.mListener.onComplete(result, mError);
        }
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(JokeDisplayActivity.JOKE_KEY, result);
        if (progressBar!=null) {
            progressBar.setVisibility(View.GONE);
        }
        context.startActivity(intent);
//        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCancelled() {
        if (this.mListener != null) {
            mError = new InterruptedException("AsyncTask cancelled");
            this.mListener.onComplete(null, mError);
        }

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }


    public EndpointsAsyncTask setListener(EndpointAsyncListener listener) {
        this.mListener = listener;
        return this;
    }

    // This listener is used in AsyncTaskAndroidTest to test the AsyncTask
    public interface EndpointAsyncListener {
        void onComplete(String result, Exception e);
    }
}
