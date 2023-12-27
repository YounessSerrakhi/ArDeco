
package com.google.ar.core.examples.java.helloar;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

    private EditText username;
    private EditText password;
    private EditText cnfPassword;
    private FirebaseAuth fAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        username = v.findViewById(R.id.reg_username);
        password = v.findViewById(R.id.reg_password);
        cnfPassword = v.findViewById(R.id.reg_cnf_pasword);
        fAuth = FirebaseAuth.getInstance();

        v.findViewById(R.id.login_reg_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNavigation navRegister = (FragmentNavigation) getActivity();
                navRegister.navigate(new loginFragment(), false);
            }
        });
        v.findViewById(R.id.register_reg_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmptyForm();
            }
        });
        return v;
    }

    private void firebaseSignUp() {
        fAuth.createUserWithEmailAndPassword(
                        username.getText().toString(),
                        password.getText().toString()
                )
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FragmentNavigation navRegister = (FragmentNavigation) getActivity();
                                navRegister.navigate(new loginFragment(), true);
                                Toast.makeText(getContext(), "Register successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    }
                });
    }
    private void validateEmptyForm() {
        Drawable icon;
        icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_warning);
        if (icon != null) {
            icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        }
        if (TextUtils.isEmpty(username.getText().toString().trim())) {
            username.setError("please enter username", icon);
        }
        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            password.setError("please enter password", icon);
        }
        if (TextUtils.isEmpty(cnfPassword.getText().toString().trim())) {
            cnfPassword.setError("please enter password", icon);
        }
        if (!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString()) && !TextUtils.isEmpty(cnfPassword.getText().toString())) {
            if (username.getText().toString().matches("[a-zA-Z0-9._+]+@[a-z]+\\.+[a-z]+")) {
                if (password.getText().toString().length() >= 5) {
                    if (password.getText().toString().equals(cnfPassword.getText().toString())) {
                        firebaseSignUp();
                    }
                } else {
                    password.setError("please enter at least characters", icon);
                }
            } else {
                username.setError("please enter valid Email", icon);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}