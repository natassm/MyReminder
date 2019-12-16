package com.example.myreminder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    Context context;
    ArrayList<ToDo> toDoArrayList;

    public ToDoAdapter(Context c, ArrayList<ToDo> p){
        context = c;
        toDoArrayList = p;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ToDoViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_holder_dashboard_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder toDoViewHolder, int position) {
        ToDo toDo = toDoArrayList.get(position);
        toDoViewHolder.titledoes.setText(toDo.getTitleMR());
        toDoViewHolder.descdoes.setText(toDo.getDescMR());
        toDoViewHolder.dueDatedoes.setText(toDo.getDueDateMR());

        final String getTitleDoes = toDoArrayList.get(position).getTitleMR();
        final String getDescDoes = toDoArrayList.get(position).getDescMR();
        final String getDueDateDoes = toDoArrayList.get(position).getDueDateMR();
        final String getIdDoes = toDoArrayList.get(position).getIdMR();

        toDoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(context, UpdateDeleteToDoActivity.class);

                a.putExtra("titleDoes", getTitleDoes);
                a.putExtra("descDoes", getDescDoes);
                a.putExtra("dueDateDoes", getDueDateDoes);
                a.putExtra("idDoes", getIdDoes);

                context.startActivity(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoArrayList.size();
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder{

        private TextView titledoes, descdoes, dueDatedoes;

        public ToDoViewHolder(View itemView) {
            super(itemView);

            titledoes = itemView.findViewById(R.id.itemListTitleTextView);
            descdoes = itemView.findViewById(R.id.itemListDescTextView);
            dueDatedoes = itemView.findViewById(R.id.itemListDateTextView);
        }
    }

}

