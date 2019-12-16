package com.example.myreminder;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseDatabaseHelper {

    private DatabaseReference reference;
    private FirebaseDatabase database;
    ArrayList<ToDo> toDoArrayList = new ArrayList<>();

    public interface DataStatus{
        void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys);
        void dataIsUpdate();
        void dataIsDelete();
    }

    public FirebaseDatabaseHelper(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("MyReminder");
    }

    public void readToDo(final DataStatus dataStatus){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toDoArrayList.clear();
                ArrayList<String> keys = new ArrayList<>();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    for (DataSnapshot dataSnapshot2: dataSnapshot1.getChildren()){
                        keys.add(dataSnapshot2.getKey());
                        ToDo p = dataSnapshot2.getValue(ToDo.class);
                        toDoArrayList.add(p);
                    }
                    dataStatus.dataIsLoad(toDoArrayList, keys);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateToDo(String key, ToDo toDo, final DataStatus dataStatus){
        reference.child("AddNewToDo").child(key).setValue(toDo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsUpdate();
            }
        });
    }

    public void deleteToDo(String key, final DataStatus dataStatus){
        reference.child("AddNewToDo").child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsDelete();
            }
        });
    }
}
