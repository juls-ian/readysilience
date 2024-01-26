package com.example.readysilience.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readysilience.AddTodo;
import com.example.readysilience.Checklist;
import com.example.readysilience.Model.ToDoModel;
import com.example.readysilience.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {

    private List<ToDoModel> todoList;
    private Checklist activity;
    private FirebaseFirestore firestore;

    public TodoAdapter(Checklist checklistActivity, List<ToDoModel> todoList){
        this.todoList= todoList;
        activity = checklistActivity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_todo_tasks, parent, false);
        firestore = FirebaseFirestore.getInstance();
        return new MyViewHolder(view);
    }

    public Context getContext(){
        return activity;
    }

    //DELETING THE ITEMS
    public void deleteTask(int position){
        ToDoModel toDoModel = todoList.get(position);
        firestore.collection("task").document(toDoModel.TaskId).delete();
        todoList.remove(position);
        notifyItemRemoved(position);

    }

    //EDIT THE ITEMS
    public void editTask(int position){
        ToDoModel toDoModel = todoList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("task", toDoModel.getTask());
        bundle.putString("due", toDoModel.getDue());
        bundle.putString("id", toDoModel.TaskId);

        AddTodo addTodo = new AddTodo();
        addTodo.setArguments(bundle);
        addTodo.show(activity.getSupportFragmentManager(), addTodo.getTag());
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ToDoModel toDoModel = todoList.get(position);
        holder.mCheckBox.setText(toDoModel.getTask());
        holder.mDueDateTV.setText("Acquiring on: " + toDoModel.getDue());
        holder.mCheckBox.setChecked(toBoleean(toDoModel.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    firestore.collection("task").document(toDoModel.TaskId).update("status", 1);
                }else {
                    firestore.collection("task").document(toDoModel.TaskId).update("status", 0);
                }
            }
        });

    }

    private boolean toBoleean(int status){
        return status != 0;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mDueDateTV;
        CheckBox mCheckBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mDueDateTV = itemView.findViewById(R.id.due_date);
            mCheckBox = itemView.findViewById(R.id.checkbox);

        }
    }
}
