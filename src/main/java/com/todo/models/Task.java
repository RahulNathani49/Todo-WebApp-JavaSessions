/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.models;

import com.todo.exceptions.todoexceptions;
import java.util.UUID;

/**
 *
 * @author isi
 */
public class Task {
    private final String id;
    private String name;
    private boolean isCompleted;

    public Task(String id,String name, boolean isCompleted) throws todoexceptions {
        if (id==null) {
            throw new todoexceptions("Id was not generated");
        }
        if (name.isBlank() || name.isEmpty()) {
            throw new todoexceptions("Task name is invalid");
        }
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getUuid() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public void completeTask(Task task){
        task.isCompleted = true;
    }
    public void resetTask(Task task){
        task.isCompleted = false;
    }
    @Override
    public String toString() {
        return getUuid() + getName();
    }
    
}
