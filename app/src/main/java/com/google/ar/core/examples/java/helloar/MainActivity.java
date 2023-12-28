package com.google.ar.core.examples.java.helloar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements FragmentNavigation {
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseApp.initializeApp(getApplicationContext());
        getSupportFragmentManager().beginTransaction().add(R.id.container, new loginFragment()).commit();

        fAuth=FirebaseAuth.getInstance();
        /*FirebaseUser fuser=fAuth.getCurrentUser();
        if(fuser!=null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment()).addToBackStack(null).commit();
        }else {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new loginFragment()).commit();
        }*/
    }
    @Override
    public void navigate(Fragment f, Boolean addtostack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.container, f);

        if (addtostack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}