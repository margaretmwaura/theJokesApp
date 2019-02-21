package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thejokeactivity.JokeDisplayingActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.startingTheDisplayActivity{

    private Button tellAJokeButton;
    public MainActivityFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

//        Code to be executed in case the button is clicked
        tellAJokeButton = root.findViewById(R.id.tellAJoke_Button);
        tellAJokeButton.setOnClickListener(new View.OnClickListener()
        {
            Context context = getActivity();
            @Override
            public void onClick(View v) {
                Log.d("AsyncTask","The asynctask has been called for the endpoints ");
                startEndPoint();
            }
        });
        return root;
    }

    private void startEndPoint()
    {
        new EndpointsAsyncTask(this).execute(getActivity());
    }

    @Override
    public void startingDisplay(String s)
    {

        Log.d("StartingDisplay","Starting the display");
        Intent intent =new Intent(getActivity().getApplicationContext(), JokeDisplayingActivity.class);
        intent.putExtra("TheJoke",s);
        startActivity(intent);

    }
}
