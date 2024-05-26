/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.command.CommandExecutionPlan;
import org.example.command.CommandInterpreter;
import org.example.scanner.CommandlineInterface;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class App {

    public static final Logger log = LogManager.getLogger(App.class.getName());

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        log.info(new App().getGreeting());
        //log.info(CommandExecutor.runCommandAndReturnOutput("ls -la"));
        //log.info(CommandExecutor.runCommandWithUserConfirmation("ls -la", "Do you want to get the files listed "));
        CommandInterpreter ci = new CommandInterpreter();
        File testFile = new File(Thread.currentThread().getContextClassLoader()
                .getResource("execution_plan1.json").getFile());
        CommandExecutionPlan cip = ci.getCommandExecutionPlan(testFile);
        ci.executePlan(cip);
    }


}
