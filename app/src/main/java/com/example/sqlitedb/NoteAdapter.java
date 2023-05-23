package com.example.sqlitedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends  RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    public Context context;
    public ArrayList<Notes> mList;
    public View.OnClickListener mOnItemClickListener;

    public NoteAdapter(Context context, ArrayList<Notes> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note_layout, parent, false);
        return new NoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.note_text.setText(mList.get(position).getNote_text());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView note_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            note_text = itemView.findViewById(R.id.tv_Note);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
