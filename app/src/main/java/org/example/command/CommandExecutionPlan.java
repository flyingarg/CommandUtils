package org.example.command;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutionPlan {

    private List<Command> orderedListOfCommands = new ArrayList<>();

    public CommandExecutionPlan(){
    }

    public List<Command> getOrderedListOfCommands(){
        return orderedListOfCommands;
    }

    public void addCommand(Command command){
        orderedListOfCommands.add(command);
        orderedListOfCommands.sort((c1, c2)
                -> c1.order > c2.order ?
                (c1.order == c2.order ? 0 : 1 ) :
                -1);
    }
}
