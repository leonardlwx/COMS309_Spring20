package com.example.StudyBuddy.UI_elements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudyBuddy.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private ArrayList<String> chat_entries = new ArrayList<>();

    public RecyclerViewAdapter(ArrayList<String> chat_entries)
    {
        this.chat_entries = chat_entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.chatItem.setText(chat_entries.get(position));

    }

    @Override
    public int getItemCount()
    {
        return chat_entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView chatItem;
        RelativeLayout chatItem_layout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            chatItem = itemView.findViewById(R.id.chat_entry);
            chatItem_layout = itemView.findViewById(R.id.chatItemLayout);
        }
    }

    public void addItem(String item)
    {
        chat_entries.add(0, item);
        notifyItemInserted(0);
    }
}
