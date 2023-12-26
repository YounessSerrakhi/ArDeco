package com.google.ar.core.examples.java.helloar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AcceuilFragment extends Fragment {

   TypeWriter typeWriter;

    public AcceuilFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_acceuil, container, false);

        typeWriter = view.findViewById(R.id.typeWriter); // Assurez-vous d'ajuster l'ID selon votre configuration
        typeWriter.setCharacterDelay(50);
        typeWriter.animateText("Bienvenue sur ARDecor, votre application de décoration virtuelle.");

        view.findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HelloArActivity.class);
                startActivity(i);
            }
        });
        view.findViewById(R.id.btnCatalogue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNavigation navRegister = (FragmentNavigation) getActivity();
                navRegister.navigate(new HomeFragment(), true);
            }
        });
        return view;
    }
}