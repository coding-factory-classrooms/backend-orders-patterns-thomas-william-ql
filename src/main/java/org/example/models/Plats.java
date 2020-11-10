package org.example.models;

public class Plats {

    private String Name;
    private String Type;
    private String State;

    public String getName(){
       return Name;
    }
    public String getType(){
        return Type;
    }
    public String getState(){ return State;}

    public void setName(String Name){
        this.Name = Name;
    }
    public void setType(String Type){
        this.Type = Type;
    }
    public void setState(String State){
        this.State = State;
    }

}
