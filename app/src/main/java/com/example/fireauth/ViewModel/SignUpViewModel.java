package com.example.fireauth.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fireauth.Model.FireUserRepo;
import com.google.firebase.auth.FirebaseUser;

public class SignUpViewModel extends AndroidViewModel {
    private FireUserRepo fireUserRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    public SignUpViewModel(@NonNull Application application) {
        super(application);
        fireUserRepo = new FireUserRepo(application);
        userMutableLiveData = fireUserRepo.getUserMutableLiveData();
    }

    public void signUp(String email, String password){
        fireUserRepo.signUp(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
