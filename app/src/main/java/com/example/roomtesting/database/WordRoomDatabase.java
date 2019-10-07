package com.example.roomtesting.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null)
        {
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordRoomDatabase.class,
                            "word_database")
                            .addCallback(callback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbSync(INSTANCE).execute();
        }
    };


    private static class PopulateDbSync extends AsyncTask<Void,Void,Void>
    {
        private final WordDao wordDao;

        public PopulateDbSync(WordRoomDatabase db) {
            this.wordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll();
            Word word = new Word("Hello");
            wordDao.insert(word);
            word = new Word("World");
            wordDao.insert(word);
            return null;
        }
    }
}
