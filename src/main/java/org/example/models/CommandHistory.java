package org.example.models;

import java.util.ArrayList;

public class CommandHistory {

    private ArrayList<CommandState> history;
    private int currentState = -1;

    public CommandHistory() {
        this.history = new ArrayList<CommandState>();
    }

    public void addCommandState(CommandState commandState) {
        this.history.add(commandState);
        currentState = this.history.size() - 1;
    }

    public CommandState getCommandState(Integer index) {
        if (index >= 0  && this.history.size() > index) {
            return this.history.get(index);
        }

        return null;
    }

    public CommandState getCurrentCommandState() {
        return this.getCommandState(this.currentState);
    }

    public CommandState undo(){

        System.out.println("UNDO");
        if (currentState < 1) {
            CommandState commandState = getCommandState(0);
            return commandState;
        }

        currentState--;
        return getCommandState(currentState);
    }


    public CommandState redo(){
        System.out.println("UNDO");
        if (currentState >= this.history.size() -1) {
            currentState = this.history.size() -1;
            return getCommandState(currentState);
        }

        currentState++;
        return getCommandState(currentState);
    }
}
