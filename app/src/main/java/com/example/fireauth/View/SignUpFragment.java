package com.example.fireauth.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.fireauth.Model.FireUserRepo;
import com.example.fireauth.R;
import com.example.fireauth.ViewModel.SignUpViewModel;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment {

    private EditText edt_email, edt_password;
    private Button btn_signUp;
    private SignUpViewModel signUpViewModel;
    private FireUserRepo fireUserRepo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        signUpViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                    Navigation.findNavController(getView()).navigate(R.id.action_signUpFragment_to_loginFragment);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup,container,false);
        edt_email = view.findViewById(R.id.edt_email_signup);
        edt_password = view.findViewById(R.id.edt_password_signup);
        btn_signUp = view.findViewById(R.id.btn_signup);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                if (email.length()>0 && password.length()>0){
                    signUpViewModel.signUp(email, password);
                }
            }
        });
        return view;
    }
}
