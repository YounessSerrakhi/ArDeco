package com.google.ar.core.examples.java.helloar;


import android.annotation.SuppressLint;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class loginFragment extends Fragment{


    private String mParam1;
    private String mParam2;
    private EditText username;
    private EditText password;
    private FirebaseAuth fAuth;


    @SuppressLint("MissingInflatedId")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);
        username=v.findViewById(R.id.log_username);
        password=v.findViewById(R.id.log_password);
        fAuth= FirebaseAuth.getInstance();
        v.findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
        v.findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNavigation navRegister = (FragmentNavigation) getActivity();
                navRegister.navigate(new RegisterFragment(), false);
            }
        });

       v.findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                FragmentNavigation navRegister = (FragmentNavigation) getActivity();
                navRegister.navigate(new AcceuilFragment(), true);
            }
        });

        return v;
    }

    private void validateForm() {
        Drawable icon;
        icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_warning);
        if (icon != null) {
            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        }
        if(TextUtils.isEmpty(username.getText().toString().trim())){
            username.setError("please enter username",icon);
        }
        if(TextUtils.isEmpty(password.getText().toString().trim())){
            username.setError("please enter password",icon);
        }
        if(username.getText().toString() != null && password.getText().toString() != null ){
            if (username.getText().toString().matches("[a-zA-Z0-9._+]+@[a-z]+\\.+[a-z]+")) {
                firebaseSignIn();

            }
            else {
                username.setError("please enter valid Email",icon);
            }
        }
    }

    private void firebaseSignIn() {
        fAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FragmentNavigation navRegister = (FragmentNavigation) getActivity();
                        navRegister.navigate(new AcceuilFragment(), true);
                        Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "password or email is invalid", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}