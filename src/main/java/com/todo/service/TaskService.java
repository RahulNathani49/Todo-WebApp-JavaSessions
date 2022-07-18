/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.todo.service;

import com.todo.exceptions.todoexceptions;
import com.todo.models.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author isi
 */
public class TaskService {
    private final HttpSession session;
    private HashMap<String,Task> tasklist = new HashMap<>();
    public TaskService(HttpSession session) {
        if (session != null) {   
            this.session = session;            
        } else {
            throw new IllegalArgumentException("Empty Session");
        }     
    }
    public ArrayList<Task> getTasks(){
        HashMap<String,Task> taskHashMap = (HashMap<String,Task>)session.getAttribute("tasklist");

        if (taskHashMap!=null) {
             ArrayList<Task> listOfKeys = (ArrayList)taskHashMap.values().stream().collect(Collectors.toList());
            return listOfKeys;  
        }
       return null;
       
    }
    public Task getTask(String id){
       HashMap<String,Task> taskHashMap = (HashMap<String,Task>)session.getAttribute("tasklist");
        if (taskHashMap.containsKey(id)) {
            return taskHashMap.get(id);
        }
        return null;
    }
    public void addTask(String name) throws todoexceptions{
        HashMap<String,Task> taskHashMap = (HashMap<String,Task>)session.getAttribute("tasklist");
        if (taskHashMap==null) {
          taskHashMap = new HashMap<>(); 
        }
         ArrayList<Task> listOfKeys = getTasks();
         
        if (listOfKeys!=null) {
            for(Task task : listOfKeys){
                if (task.getName().equals(name)) {
                 throw new todoexceptions("Task already exists");
                }
            } 
        }
        
     
        UUID uuid = UUID.randomUUID();
        Task task = new Task(uuid.toString(),name, false);    
        taskHashMap.put(uuid.toString(), task); 
        
        session.setAttribute("tasklist", taskHashMap);
        
    }
    public void removeTask(String id){
      HashMap<String,Task> taskHashMap = (HashMap<String,Task>)session.getAttribute("tasklist");
        if (taskHashMap.containsKey(id)) {
            taskHashMap.remove(id);
        }  
        session.setAttribute("tasklist", taskHashMap);        
    }
    public void completeTask(String id){
      HashMap<String,Task> taskHashMap = (HashMap<String,Task>)session.getAttribute("tasklist");
        if (taskHashMap.containsKey(id)) {
            taskHashMap.get(id).setIsCompleted(true);
        }
       session.setAttribute("tasklist", taskHashMap);

    }
    public void resetTask(String id)
    {
      HashMap<String,Task> taskHashMap = (HashMap<String,Task>)session.getAttribute("tasklist");
        if (taskHashMap.containsKey(id)) {
            taskHashMap.get(id).setIsCompleted(false);
        }
       session.setAttribute("tasklist", taskHashMap);

    }
}
