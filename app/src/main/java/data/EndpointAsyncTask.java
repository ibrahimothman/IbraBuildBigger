package data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dell.myapplication.backend.myApi.MyApi;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import callback.Callbacks;

/**
 * Created by dell on 4/17/2018.
 */

public class EndpointAsyncTask extends AsyncTask<Context, Void, String> {
    private static final String TAG = "EndpointAsyncTask";
    private static MyApi myApiService = null;
    private Context context;

    Callbacks.EndpointAsyncTaskCallback endpointAsyncTaskCallback;

    public EndpointAsyncTask(Callbacks.EndpointAsyncTaskCallback endpointAsyncTaskCallback) {
        this.endpointAsyncTaskCallback = endpointAsyncTaskCallback;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(Constant.ENDPOINT_URL)
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

            Log.d(TAG, "fromAsynTaskDoInBackground joke is " +myApiService.getJoke().execute().getData() );
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(final String result) {
        Log.d(TAG, "fromAsynOnPost joke " + result);
        endpointAsyncTaskCallback.onEndpointAsyncTaskDone(result);
    }




}
