package com.example.myreminder.helper;

import androidx.annotation.NonNull;

import com.example.myreminder.ToDo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseDatabaseHelper {

    private DatabaseReference reference, referenceUrgent, referenceImportant, referenceMedium, referenceLow;
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
        referenceUrgent = database.getReference("MyReminder").child("AddUrgentToDo");
        referenceImportant = database.getReference("MyReminder").child("AddImportantToDo");
        referenceMedium = database.getReference("MyReminder").child("AddMediumToDo");
        referenceLow = database.getReference("MyReminder").child("AddLowToDo");
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
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void readUrgent(final DataStatus dataStatus){
        referenceUrgent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toDoArrayList.clear();
                ArrayList<String> keys = new ArrayList<>();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        keys.add(dataSnapshot1.getKey());
                        ToDo p = dataSnapshot1.getValue(ToDo.class);
                        toDoArrayList.add(p);
                }
                dataStatus.dataIsLoad(toDoArrayList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void readImportant(final DataStatus dataStatus){
        referenceImportant.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toDoArrayList.clear();
                ArrayList<String> keys = new ArrayList<>();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        keys.add(dataSnapshot1.getKey());
                        ToDo p = dataSnapshot1.getValue(ToDo.class);
                        toDoArrayList.add(p);
                    }
                    dataStatus.dataIsLoad(toDoArrayList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void readMedium(final DataStatus dataStatus){
        referenceMedium.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toDoArrayList.clear();
                ArrayList<String> keys = new ArrayList<>();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        keys.add(dataSnapshot1.getKey());
                        ToDo p = dataSnapshot1.getValue(ToDo.class);
                        toDoArrayList.add(p);
                    }
                    dataStatus.dataIsLoad(toDoArrayList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void readLow(final DataStatus dataStatus){
        referenceLow.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toDoArrayList.clear();
                ArrayList<String> keys = new ArrayList<>();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        keys.add(dataSnapshot1.getKey());
                        ToDo p = dataSnapshot1.getValue(ToDo.class);
                        toDoArrayList.add(p);
                    }
                    dataStatus.dataIsLoad(toDoArrayList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void updateUrgent(String key, ToDo toDo, final DataStatus dataStatus){
        referenceUrgent.child(key).setValue(toDo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsUpdate();
            }
        });
    }

    public void updateImportant(String key, ToDo toDo, final DataStatus dataStatus){
        referenceImportant.child(key).setValue(toDo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.dataIsUpdate();
                    }
                });
    }

    public void updateMedium(String key, ToDo toDo, final DataStatus dataStatus){
        referenceMedium.child(key).setValue(toDo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.dataIsUpdate();
                    }
                });
    }

    public void updateLow(String key, ToDo toDo, final DataStatus dataStatus){
        referenceLow.child(key).setValue(toDo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.dataIsUpdate();
                    }
                });
    }

    public void deleteUrgent(String key, final DataStatus dataStatus){
        referenceUrgent.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.dataIsDelete();
                    }
                });
    }

    public void deleteImportant(String key, final DataStatus dataStatus){
        referenceImportant.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.dataIsDelete();
            }
        });
    }

    public void deleteMedium(String key, final DataStatus dataStatus){
        referenceMedium.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.dataIsDelete();
                    }
                });
    }

    public void deleteLow(String key, final DataStatus dataStatus){
        referenceLow.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.dataIsDelete();
                    }
                });
    }
}
