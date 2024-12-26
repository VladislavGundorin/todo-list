package org.example.models;

public class Task {

    private int id;
    private String title;

    public Task() {}

    public Task(String title) {
        this.title = title;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}