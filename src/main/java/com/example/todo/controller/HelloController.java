package com.example.todo.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.Details;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class HelloController {

    static ArrayList<Details> list = new ArrayList<Details>();


    // This method will fetch the list
    @GetMapping("/")
    public ArrayList<Details> home() {
        return list;
    }

    // This method will add the todo to list
    @PostMapping("/post")
    public String insert(@RequestBody Details entity) {
        entity.id = UUID.randomUUID();
        list.add(entity);
        return "Data Inserted";
    }
    
    // This method will delete the data from the list
    @DeleteMapping("/delete/{id}")
    public String deleteTodoByName(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id); 
        } catch (IllegalArgumentException e) {
            return "Invalid UUID format";
        }
        Iterator<Details> iterator = list.iterator();
        while (iterator.hasNext()) {
            Details obd = iterator.next();
            if (obd.id.equals(uuid)) {
                iterator.remove(); 
                return "Data Deleted";
            }
        }
        return "Data Not Found";
    }

    // This method will delete the data from the list
    @PutMapping("/put/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody Details entity) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return "Invalid UUID format";
        }
        for (Details obj : list) {
            if (obj.id.equals(uuid)) {
                obj.title = entity.title;
                obj.description = entity.description;
                return "Entry Updated";
            }
        }
        return "Entry not found";
    }

}