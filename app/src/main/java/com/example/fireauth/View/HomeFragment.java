package com.example.fireauth.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.fireauth.R;
import com.example.fireauth.ViewModel.HomeViewModel;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    private EditText edt_email;
    private Button btn_logout;
    private HomeViewModel homeViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser!= null){
                    edt_email.setText(firebaseUser.getEmail());
                    btn_logout.setEnabled(true);
                }else {
                    btn_logout.setEnabled(false);
                }
            }
        });

        homeViewModel.getLoggedOutMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoggedOut) {
                if (isLoggedOut){
                    Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_loginFragment);
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        edt_email = view.findViewById(R.id.edt_email_home);
        btn_logout = view.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.loggedOut();
            }
        });

        return view;
    }
}
