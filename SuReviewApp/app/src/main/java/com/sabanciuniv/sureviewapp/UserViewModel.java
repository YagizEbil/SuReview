package com.sabanciuniv.sureviewapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    MutableLiveData<User> userData = new MutableLiveData<User>();

    public MutableLiveData<User> getUserData(){
        return userData;
    }

    public void setUserData(User u){
        this.userData.setValue(u);
    }
}
