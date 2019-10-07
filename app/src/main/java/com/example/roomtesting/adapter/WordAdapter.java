package com.example.roomtesting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomtesting.R;
import com.example.roomtesting.database.Word;
import com.example.roomtesting.viewholder.WordViewHolder;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private List<Word> wordList;

    public WordAdapter(Context context)
    {

    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (wordList != null)
        {
            Word currentWord = wordList.get(position);
            holder.wordItemView.setText(currentWord.getWord());
        }
        else {
            holder.wordItemView.setText("No Word");
        }
    }

    public void setWordList(List<Word> wordList)
    {
        this.wordList = wordList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(wordList !=null)
        {
            return wordList.size();
        }
        return 0;
    }
}
