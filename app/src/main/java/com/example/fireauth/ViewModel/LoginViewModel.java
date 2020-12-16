package com.example.fireauth.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fireauth.Model.FireUserRepo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {
    private FireUserRepo fireUserRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        fireUserRepo = new FireUserRepo(application);
        userMutableLiveData = fireUserRepo.getUserMutableLiveData();

    }

    public void logIn(String email, String password){
        fireUserRepo.logIn(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
