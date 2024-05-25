package org.example.command;

public class Command {

    public String commandString;
    public boolean needsConfirmation;
    public int order;
    public String question;

    public boolean isPending = false;

    public Command(){
        this.commandString = "";
        this.needsConfirmation = false;
    }

    public Command(String commandString, boolean needsConfirmation, int order){
        this.commandString = commandString;
        this.needsConfirmation = needsConfirmation;
        this.order = order;
    }

    public void setPending(boolean pending){
        this.isPending = pending;
    }

    public void setOrder(int order){
        this.order = order;
    }
}
