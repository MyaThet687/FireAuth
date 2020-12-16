package com.example.fireauth.ViewModel;

import android.app.Application;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fireauth.Model.FireUserRepo;
import com.google.firebase.auth.FirebaseUser;

public class HomeViewModel extends AndroidViewModel {
    private FireUserRepo fireUserRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        fireUserRepo = new FireUserRepo(application);
        userMutableLiveData = fireUserRepo.getUserMutableLiveData();
        loggedOutMutableLiveData = fireUserRepo.getLoggedOutMutableLiveData();
    }

    public void loggedOut(){
        fireUserRepo.signOut();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }
}
