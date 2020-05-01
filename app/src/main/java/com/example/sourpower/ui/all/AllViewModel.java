package com.example.sourpower.ui.all;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

public class AllViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}