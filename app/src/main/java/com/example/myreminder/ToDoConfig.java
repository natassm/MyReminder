package com.example.myreminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreminder.screen.DetailActivity;
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

        private TextView titledoes, descdoes, dueDatedoes, categoryPrio;
        private String key;

        public ToDoViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.view_holder_dashboard_list,
                    parent, false));

            titledoes = itemView.findViewById(R.id.itemListTitleTextView);
            descdoes = itemView.findViewById(R.id.itemListDescTextView);
            dueDatedoes = itemView.findViewById(R.id.itemListDateTextView);
            categoryPrio = itemView.findViewById(R.id.itemListPriorityTextView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    setAlertDialog();
                    return false;
                }
            });
        }

        public void bind(ToDo toDo, String key)
        {
            titledoes.setText(toDo.getTitleMR());
            descdoes.setText(toDo.getDescMR());
            dueDatedoes.setText(toDo.getDueDateMR());
            categoryPrio.setText(toDo.getCategoryPriority());

            this.key = key;
        }

        public void setAlertDialog(){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            alertDialogBuilder.setMessage("Hello, please choose your decision!");
            alertDialogBuilder.setPositiveButton("Update or Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intentUD = new Intent(mContext, UpdateDeleteToDoActivity.class);
                    intentUD.putExtra("key", key);
                    intentUD.putExtra("titleDoes", titledoes.getText().toString());
                    intentUD.putExtra("descDoes", descdoes.getText().toString());
                    intentUD.putExtra("dateDoes", dueDatedoes.getText().toString());
                    intentUD.putExtra("categoryDoes", categoryPrio.getText().toString());

                    mContext.startActivity(intentUD);
                }
            });
            alertDialogBuilder.setNegativeButton("Set Alarm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intentUD = new Intent(mContext, DetailActivity.class);
                    intentUD.putExtra("key", key);
                    intentUD.putExtra("titleDoes", titledoes.getText().toString());
                    intentUD.putExtra("descDoes", descdoes.getText().toString());
                    intentUD.putExtra("dateDoes", dueDatedoes.getText().toString());
                    intentUD.putExtra("categoryDoes", categoryPrio.getText().toString());

                    mContext.startActivity(intentUD);
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}