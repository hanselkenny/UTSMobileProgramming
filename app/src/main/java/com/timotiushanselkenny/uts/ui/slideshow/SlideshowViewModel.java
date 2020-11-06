package com.timotiushanselkenny.uts.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public SlideshowViewModel(String string) {
        mText = new MutableLiveData<>();
        mText.setValue(string);
    }

    public LiveData<String> getText() {
        return mText;
    }
}