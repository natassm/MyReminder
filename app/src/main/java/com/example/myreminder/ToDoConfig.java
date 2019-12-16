package com.example.myreminder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreminder.screen.UpdateDeleteToDoActivity;

import java.util.ArrayList;

public class ToDoConfig {

    private Context mContext;
    private ToDoAdapter mToDoAdapter;

    public void setConfig(RecyclerView recyclerView, Context context,
                          ArrayList<ToDo> toDoArrayList, ArrayList<String> keys)
    {
        mContext = context;
        mToDoAdapter = new ToDoAdapter(toDoArrayList,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mToDoAdapter);
    }

    class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder>
    {
        private ArrayList<ToDo> toDoArrayList;
        private ArrayList<String> mKeys;

        public ToDoAdapter(ArrayList<ToDo> toDoArrayList, ArrayList<String> mKeys) {
            this.toDoArrayList = toDoArrayList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)   {
            return new ToDoViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
            holder.bind(toDoArrayList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return toDoArrayList.size();
        }
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder {

        private TextView titledoes, descdoes, dueDatedoes;
        private String key;

        public ToDoViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.view_holder_dashboard_list,
                    parent, false));

            titledoes = itemView.findViewById(R.id.itemListTitleTextView);
            descdoes = itemView.findViewById(R.id.itemListDescTextView);
            dueDatedoes = itemView.findViewById(R.id.itemListDateTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, UpdateDeleteToDoActivity.class);
                    i.putExtra("key", key);
                    i.putExtra("titleDoes", titledoes.getText().toString());
                    i.putExtra("descDoes", descdoes.getText().toString());
                    i.putExtra("dateDoes", dueDatedoes.getText().toString());

                    mContext.startActivity(i);
                }
            });
        }

        public void bind(ToDo toDo, String key)
        {
            titledoes.setText(toDo.getTitleMR());
            descdoes.setText(toDo.getDescMR());
            dueDatedoes.setText(toDo.getDueDateMR());
            this.key = key;
        }

    }
}
