package com.example.android.livedataapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class UserModel extends ViewModel {

    public MutableLiveData<List<UserData>> userModelMutableLiveData= new MutableLiveData<>();

    public LiveData<List<UserData>> getUserModelLiveData(){
        return  userModelMutableLiveData;
    }






}
