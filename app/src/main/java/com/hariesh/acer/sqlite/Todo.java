package com.hariesh.acer.sqlite;

public class Todo {
    String head;
    String description;
    String id;
    int status;
    public Todo(){

    }
    public Todo(String i,String h,String d,int status) {
        this.head = h;
        this.id=i;
        this.description = d;
        this.status=status;
    }
    public void setHead(String h){
        this.head=h;
    }
    public int getStatus(){
        return status;
    }
    public void setStatus(int status){
        this.status=status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
}
