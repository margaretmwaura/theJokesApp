package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.thejokeactivity.JokeDisplayingActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.startingTheDisplayActivity
{
    private InterstitialAd mInterstitialAd;
    private Button jokeButton;

    public MainActivityFragment()
    {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitialAd_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                jokeButton.setEnabled(false);
                startEndPoint();
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.activity_main_fragment, container, false);
        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else
                    {

                    jokeButton.setEnabled(false);
                   startEndPoint();

                }
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
