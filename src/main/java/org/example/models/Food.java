package org.example.models;

public class Food {

    @Override
    public String toString() {
        return ""+this.type + " : "+ this.name ;
    }

    public enum Type {
        BURGER,
        ACCOMPAGNEMENT,
        BOISSON,
    }
    public Food(Type type, String name){
        this.type = type;
        this.name = name;
    }


    private String name;
    private Type type;

    public String getName(){
       return name;
    }
    public Type getType(){
        return type;
    }

    public void setName(String Name){
        this.name = Name;
    }
    public void setType(Type type){
        this.type = type;
    }

}
