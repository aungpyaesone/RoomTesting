package com.example.roomtesting.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomtesting.database.Word;
import com.example.roomtesting.database.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel
{
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getListLiveData();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    public void insert(Word word)
    {
        mRepository.insert(word);
    }
}
