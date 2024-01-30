package com.rushi.todo_List.adapter;


import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rushi.todo_List.MainActivity;
import com.rushi.todo_List.R;
import com.rushi.todo_List.ShowTask;
import com.rushi.todo_List.database.MySqliteDB;
import com.rushi.todo_List.database.taskModuling.TaskModuling;

import java.util.List;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.viewHolder> {
    protected Context context;
    Context con;

    protected List<TaskModuling> taskModulings;

    public MyRecyclerView(Context context, List<TaskModuling> taskModulings) {
        this.context = context;
        this.taskModulings = taskModulings;
    }


    @NonNull
    @Override
    public MyRecyclerView.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_my_formate,parent, false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerView.viewHolder holder, int position) {
        Log.d("dbin", "in onBindig Holder At Recycler");
        TaskModuling taskModuling = taskModulings.get(position);
        holder.taskName.setText(taskModuling.getTask());

    }

    @Override
    public int getItemCount() {
        return taskModulings.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        public TextView taskName;
        public Button deleteButton;
        public viewHolder(View itemView) {

            super(itemView);
            Log.d("dbin", "in afer super View Holder At Recycler");
            Intent in = new Intent(context, MainActivity.class);

            taskName = itemView.findViewById(R.id.taskName);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            con = taskName.getContext();
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getItemCount()>=0){
//                        Toast.makeText(context, "It is del", Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder builder =new AlertDialog.Builder(con);
                        builder.setTitle("Are You Sure?");
                        builder.setMessage("Remove The Task From List.");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletesome();
//                        startActivity(context,in,null);
                                Intent io= new Intent(context, ShowTask.class);
//                        context.startActivity(i);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();



                    }
                    else {
                        Toast.makeText(context, "No Task For Delete", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        protected void deletesome(){
            MySqliteDB db = new MySqliteDB(context);
            int position = this.getAdapterPosition();
            TaskModuling taskModuling = taskModulings.get(position);
            String task = taskModuling.getTask();
            Toast.makeText(context, "The Task Is Deleted..", Toast.LENGTH_SHORT).show();
            db.deleteTask(task);
            taskModulings.remove(position);
            notifyDataSetChanged();
        }


    }

}
