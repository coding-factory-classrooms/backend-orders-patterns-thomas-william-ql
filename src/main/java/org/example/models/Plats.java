package org.example.models;

public class Plats {

    protected enum State {
        MANGEABLE,
        IMMENGEABLE,
        POURRI,
    }

    private String name;
    private String type;
    private State state;

    public String getName(){
       return name;
    }
    public String getType(){
        return type;
    }
    public State getState(){
        return state;
    }

    public void setName(String Name){
        this.name = Name;
    }
    public void setType(String Type){
        this.type = Type;
    }
    public void setState(State State){
        this.state = State;
    }

}
