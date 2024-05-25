package org.example.command;

public class CommandExecutor {

    public static String runCommandAndReturnOutput(String inputCommand) {
        return "";
    }

    public static String runCommandWithUserConfirmation(String inputCommand) {
        if(hasUserConfirmedRequest()){
            runCommandAndReturnOutput(inputCommand);
        }
        return "";
    }

    //TODO : Pause and run command !!

    private static boolean hasUserConfirmedRequest(){
        return false;
    }
}
