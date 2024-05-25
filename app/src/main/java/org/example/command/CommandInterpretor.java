package org.example.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CommandInterpretor {

    public static final Logger log = LogManager.getLogger(CommandInterpretor.class.getName());
    protected CommandExecutionPlan commandExectionPlan = new CommandExecutionPlan();
    private ObjectMapper mapper = new ObjectMapper();


    public CommandExecutionPlan getCommandExecutionPlan(File file){
        String objectAsJson = getFileContents(file);
        return getCommandExecutionPlan(objectAsJson);
    }

    public CommandExecutionPlan getCommandExecutionPlan(String objectAsJson){
        try {

            return mapper.readValue(objectAsJson.toString(), CommandExecutionPlan.class);

        } catch (JsonMappingException jme){
            log.error(jme.getMessage());
        }catch (IOException ioe){
            log.error(ioe.getMessage());
        }
        return null;
    }

    public String getCommandExecutionJson(){
        try {
            return mapper.writeValueAsString(commandExectionPlan);
        }catch (JsonProcessingException jpe){
            log.error(jpe.getMessage());
        }
        return "";
    }

    private String getFileContents(File file){
        String line = "";
        StringBuilder jsonStringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        }catch (IOException ioe){
            log.error(ioe.getMessage());
        }
        return jsonStringBuilder.toString();
    }

    public void executePlan(CommandExecutionPlan commandExecutionPlan){
        for(Command command : commandExecutionPlan.getOrderedListOfCommands()){
            if(command.needsConfirmation)
                log.info(CommandExecutor.runCommandWithUserConfirmation(command.commandString, command.question));
            else
                log.info(CommandExecutor.runCommandAndReturnOutput(command.commandString));
        }
    }
}
