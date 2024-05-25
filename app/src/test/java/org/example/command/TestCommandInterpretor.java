package org.example.command;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

public class TestCommandInterpretor extends TestCase {

    public static final Logger log = LogManager.getLogger(TestCommandInterpretor.class.getName());

    public void testObjectToJson(){
        Command command = new Command("ls -laht", true, 1);
        command.setPending(true);

        Command command2 = new Command("ls -la", true, 2);
        command2.setPending(true);

        CommandExecutionPlan commandExecutionPlan = new CommandExecutionPlan();
        commandExecutionPlan.addCommand(command2);
        commandExecutionPlan.addCommand(command);

        TestCommandInterpreterMock testCommandInterpreterMock = new TestCommandInterpreterMock();
        testCommandInterpreterMock.setCommandExecutionPlan(commandExecutionPlan);
        String jsonedObject = testCommandInterpreterMock.getCommandExecutionJson();

        log.info(jsonedObject);

        CommandInterpretor commandInterpretor = new CommandInterpretor();
        CommandExecutionPlan commandExecutionPlan1 = commandInterpretor.getCommandExecutionPlan(jsonedObject);
        assertEquals(commandExecutionPlan1.getOrderedListOfCommands().get(0).commandString, "ls -laht");

    }

    public void testExecutePlan(){
        CommandInterpretor ci = new CommandInterpretor();
        File testFile = new File(Thread.currentThread().getContextClassLoader()
                .getResource("execution_plan1.json").getFile());
        CommandExecutionPlan cip = ci.getCommandExecutionPlan(testFile);
        assertEquals(cip.getOrderedListOfCommands().get(1).commandString, "ls -la");
    }

    private class TestCommandInterpreterMock extends CommandInterpretor {
        public void setCommandExecutionPlan(CommandExecutionPlan commandExecutionPlanInput){
            commandExectionPlan = commandExecutionPlanInput;
        }
    }
}
