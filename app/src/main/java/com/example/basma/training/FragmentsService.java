package com.example.basma.training;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentsService extends AppCompatActivity implements BlankFragmentA.OnFragmentInteractionListener,BlankFragmentB.OnFragmentInteractionListener {


    static FragmentManager fragmentManager;
    boolean isLandscape = false;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_service);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BlankFragmentA cf = new BlankFragmentA();
        BlankFragmentB df = new BlankFragmentB();
        fragmentTransaction.add(R.id.frame, cf);


        SharedPreferences settingsfile= getSharedPreferences("Prefs",0);
        SharedPreferences.Editor myeditor = settingsfile.edit();



        if(null!=findViewById(R.id.frameA))
        {
            isLandscape=true;
            myeditor.putString("isLandscape", "true");
            myeditor.apply();

        }


        Bundle bundle = new Bundle();

        if(isLandscape)
        {
            fragmentTransaction.add(R.id.frameA, df);
            bundle.putString("landscape", "true");
            cf.setArguments(bundle);
            fragmentTransaction.commit();
        }
        else
        {

            myeditor.putString("isLandscape", "false");
            myeditor.apply();


            bundle.putString("landscape", "false");
            cf.setArguments(bundle);
            fragmentTransaction.commit();
        }


    }
}
