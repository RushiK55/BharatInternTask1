package com.rushi.todo_List;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rushi.todo_List.database.MySqliteDB;
import com.rushi.todo_List.database.taskModuling.TaskModuling;

public class MainActivity extends AppCompatActivity {
    Button addTask;
    FloatingActionButton showTask;
    EditText getTask;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make the Navigation Color to Transperant..
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.CUR_DEVELOPMENT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        img = findViewById(R.id.bgImage);
        //make a background image blur
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            img.setRenderEffect(RenderEffect.createBlurEffect(20f,15f, Shader.TileMode.MIRROR));
        }
        getTask = findViewById(R.id.getTask);
        addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = getTask.getText().toString().trim();
                if (task.isEmpty() || task==null){
                    Toast.makeText(MainActivity.this, "Enter a Valid Task", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Adding a Task "+task, Toast.LENGTH_SHORT).show();
                    addATask(task);
                    getTask.setText("");
                }
            }
        });


        showTask = findViewById(R.id.showTask1);
        showTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, ShowTask.class);
                startActivity(in);
                finish();
            }
        });
    }

    private void addATask(String task){
        MySqliteDB db = new MySqliteDB(MainActivity.this);
        TaskModuling taskModuling = new TaskModuling();
        taskModuling.setTask(task);
        db.addTask(taskModuling);
        Log.d("dbin","The Task is added in Db");
        startActivity(new Intent(MainActivity.this, ShowTask.class));
        finish();
    }

}