package com.sabanciuniv.sureviewapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> token = new MutableLiveData<>();

    public void setToken(String token) {
        this.token.setValue(token);
    }

    public LiveData<String> getToken() {
        return token;
    }
}
