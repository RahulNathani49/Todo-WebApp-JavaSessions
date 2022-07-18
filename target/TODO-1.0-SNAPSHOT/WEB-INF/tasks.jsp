<%-- 
    Document   : tasks
    Created on : Jul 18, 2022, 3:51:37 PM
    Author     : isi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.todo.models.Task"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Taks Page</title>
        <style>
            body, p, legend, label, input {
    font: normal 8pt verdana, helvetica, sans-serif;
    text-align: left;
}

li {
    padding: 8px 0px;
    
}

.task {
    font-size: 12pt;
}

.task.complete {
    text-decoration: line-through;
    color: #aaaaaa;
}

form, .tasksList {
    max-width: 400px;
    margin: auto;
}

fieldset {
    padding: 20px;
    margin-top: 20px;
    border: 1px solid #aaaaaa;
    border-radius: 8px;
}

legend {
    font-size: 12pt;
    color: #444444;
}

form input {
    width: 100%;
    font-size: 12pt;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #cccccc;
    border-radius: 4px;
    box-sizing: border-box;
}

form button[type=submit] {
    width: 100%;
    background-color: #33bb66;
    color: white;
    text-align: center;
    font-size: 12pt;
    padding: 12px 20px;
    margin: 8px 0px 0px 0px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
.lists a{
    padding: 10px;
    background:black;
    color: white;
    text-decoration: none;
}
        </style>
    </head>
    <%
        ArrayList<Task> tasklist = (ArrayList<Task>)request.getAttribute("tasklist");
        String message = (String)request.getAttribute("message");
        if (message==null) {
              message="";  
         }
    %>
    <body>
        <form action="todo?action=add" method="post">
            <fieldset>
                <legend>Add Task</legend>
            <input type="text" name="taskname">
            <small style="color:red;"> <%= message %></small>
            <button type="submit">Add</button>
            </fieldset>
        </form>
        <fieldset class="tasksList" style="margin:20px auto">
                <legend>Tasks</legend>
                <ul class="lists">
                    <%
                     if (tasklist!=null) {
                          for (Task task : tasklist) {
                            %>
                             <% 
                                String buttonvalue;
                                String completedesign;
                                String urlchanged;
                                if (task.isIsCompleted()) {
                                    buttonvalue="Reset";
                                    completedesign="line-through";
                                    urlchanged="reset";
                                }
                                else{
                                buttonvalue="Complete";
                                completedesign="none";
                                urlchanged="complete";
                                }
                            %>
                            <li style="text-decoration: <%=completedesign %>"><h3><%= task.getName() %></h3></li>
                            <a href="todo?action=remove&uuid=<%= task.getUuid() %>">Remove</a>
                           
                            <a href="todo?action=<%=urlchanged %>&&uuid=<%= task.getUuid() %>"><%= buttonvalue %></a>
                            <%
                         }    
                         }
                    
                    %>
                </ul>
        </fieldset>
    </body>
</html>
