package com.example.readysilience;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.readysilience.Adapter.TodoAdapter;
import com.example.readysilience.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Checklist extends AppCompatActivity implements OnDialogCloseListener {

    private RecyclerView todoRecyclerView;
    private FloatingActionButton addFab;
    private FirebaseFirestore firestore;
    private TodoAdapter adapter;
    private List<ToDoModel> modelList;
    private Query query;
    private ListenerRegistration listenerRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        todoRecyclerView = findViewById(R.id.todo_recyclerview);
        addFab = findViewById(R.id.add_fab);
        firestore = FirebaseFirestore.getInstance();

        ImageButton backButtonChecklist = findViewById(R.id.back_button_checklist);
        backButtonChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Checklist.this, MainActivity.class));
                finish();
            }
        });

        todoRecyclerView.setHasFixedSize(true);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(Checklist.this));

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodo.newInstance().show(getSupportFragmentManager(), AddTodo.TAG);
            }
        });



        modelList = new ArrayList<>();
        adapter = new TodoAdapter(Checklist.this, modelList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(todoRecyclerView);
        todoRecyclerView.setAdapter(adapter);
        showData();

    }

    private void showData(){
        query = firestore.collection("task").orderBy("time", Query.Direction.DESCENDING);
                listenerRegistration = query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange documentChange: value.getDocumentChanges()){
                    if (documentChange.getType() == DocumentChange.Type.ADDED){
                        String id = documentChange.getDocument().getId();
                        ToDoModel toDoModel = documentChange.getDocument().toObject(ToDoModel.class).withId(id);
                        modelList.add(toDoModel);
                        adapter.notifyDataSetChanged();
                        findViewById(R.id.empty_indicator).setVisibility(View.GONE);
                    }else {
                        findViewById(R.id.empty_indicator).setVisibility(View.VISIBLE);
                    }
                }
                listenerRegistration.remove();
            }
        });
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        modelList.clear();
        showData();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateToMainActivity();
    }

    private void navigateToMainActivity() {
        startActivity(new Intent(Checklist.this, MainActivity.class));
        finish();
    }
}