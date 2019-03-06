package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class EndpointsAsyncTask extends AsyncTask<Context, Void, String>
{
        private static MyApi myApiService = null;
        private startingTheDisplayActivity mainFragment;
        private Context context;

        public EndpointsAsyncTask() { mainFragment = null;  }

        public EndpointsAsyncTask(MainActivityFragment mainFrag)
        {
            mainFragment = mainFrag;
        }
        @Override
        protected String doInBackground(Context... pairs)
        {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
//                    Changed it to the comps ip address
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

            context = pairs[0];

            try {
                return myApiService.sayHi("TheJoke").execute().getData();
            } catch (IOException e)
            {
                Log.d("TheResultingError " , "This is the error" + e.getMessage());
                return e.getMessage();
            }

        }

        //    This codes need to be edited
        @Override
        protected void onPostExecute(String s) {


            if(mainFragment!= null)
            {
                mainFragment.startingDisplay(s);
            }
        }

        public interface startingTheDisplayActivity
        {
            void startingDisplay(String s);
        }
    }


