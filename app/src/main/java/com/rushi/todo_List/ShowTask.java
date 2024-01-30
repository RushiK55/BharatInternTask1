package com.rushi.todo_List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rushi.todo_List.adapter.MyRecyclerView;
import com.rushi.todo_List.database.MySqliteDB;
import com.rushi.todo_List.database.taskModuling.TaskModuling;

import java.util.ArrayList;
import java.util.List;

public class ShowTask extends AppCompatActivity {
    private RecyclerView rcview;
    private MyRecyclerView myRecyclerView;
    private ArrayList<TaskModuling> taskModulings;
   private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton showTaska;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        rcview = findViewById(R.id.recyclerView);
        rcview.setHasFixedSize(true);
        rcview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("dbin", "In ShowTask ");

        MySqliteDB db = new MySqliteDB(ShowTask.this);

        taskModulings = new ArrayList<>();
        List<TaskModuling> taskModulingList = db.getAllTask();
//
    for (TaskModuling taskModuling: taskModulingList) {
            Log.d("dbin", "In ShowTask ");
            taskModulings.add(taskModuling);
        }
        Log.d("dbin", "At ShowTask after loop");
        myRecyclerView = new MyRecyclerView(ShowTask.this, taskModulings);
        Log.d("dbin", "At ShowTask after setting adapter");
        rcview.setAdapter(myRecyclerView);
        Log.d("dbin", "At ShowTask all End");


        showTaska = findViewById(R.id.showTaskTo);
        showTaska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent in = new Intent(ShowTask.this, MainActivity.class);
            startActivity(in);
            }
        });
    }



}