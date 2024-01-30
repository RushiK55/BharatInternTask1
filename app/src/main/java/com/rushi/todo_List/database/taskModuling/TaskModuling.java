package com.rushi.todo_List.database.taskModuling;

public class TaskModuling {
    private int id;
    private  String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public TaskModuling(String task) {
        this.task = task;
    }

    public TaskModuling(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public TaskModuling() {

    }
}
