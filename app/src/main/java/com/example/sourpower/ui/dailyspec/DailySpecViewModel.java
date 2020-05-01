package com.example.sourpower.ui.dailyspec;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DailySpecViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DailySpecViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}