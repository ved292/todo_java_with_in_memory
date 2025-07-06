package com.example.todo;
import java.util.UUID;
public class Todo {
    public UUID id;
    public String title;
    public String description;
    public Todo(UUID id,String title,String description)
    {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}