package com.example.roomtesting.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> listLiveData;

    public WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        listLiveData = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getListLiveData(){
        return listLiveData;
    }

    public void insert(Word word)
    {
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void>
    {
        private WordDao wordDao;

        insertAsyncTask(WordDao dao){
            this.wordDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }
}
