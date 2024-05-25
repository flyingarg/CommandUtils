package org.example.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.scanner.CommandlineInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class CommandExecutor {

    public static final Logger log = LogManager.getLogger(CommandExecutor.class.getName());

    private static String TEMPPROCESS_FILE_LOCATION = "/tmp/";

    public static String runCommandAndReturnOutput(String inputCommand) {
        String md5CheckSum = "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(inputCommand.getBytes());
            md5CheckSum = HexFormat.of().formatHex(md.digest());
        }catch (NoSuchAlgorithmException nsae){
            log.error(nsae.getMessage());
        }
        try{
            ProcessBuilder processBuilder = new ProcessBuilder();
            File redirectFileError = new File(TEMPPROCESS_FILE_LOCATION + md5CheckSum + ".error");
            File redirectFileOutput = new File(TEMPPROCESS_FILE_LOCATION + md5CheckSum + ".output");
            Process process = processBuilder
                    .command(inputCommand.split(" "))
                    .redirectError(ProcessBuilder.Redirect.to(redirectFileError))
                    .redirectOutput(ProcessBuilder.Redirect.to(redirectFileOutput))
                    .start();
            process.waitFor();
            int exitValue = process.exitValue();
            if(exitValue == 0){
                return getFileContents(redirectFileOutput);
            }else{
                return getFileContents(redirectFileError);
            }
        }catch(IOException | InterruptedException ioe){
            log.error(ioe.getMessage());
        }
        return "";
    }

    public static String runCommandWithUserConfirmation(String inputCommand, String userQuestion) {
        String answer = CommandlineInterface.askUserYesNo(userQuestion);
        if(CommandlineInterface.ANSWER_YES.equals(answer)){
            return runCommandAndReturnOutput(inputCommand);
        }else if(CommandlineInterface.ANSWER_NONE.equals(answer)){
            log.info("The user did not answer the question");
        }else {
            log.info("The user answered the question no");
        }
        return "";
    }

    //TODO : Pause and run command !!

    private static String getFileContents(File file){
        String output = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while (( line = br.readLine()) != null) {
                output += line + "\n";
            }
        }catch (IOException ioe){
            log.error(ioe.getMessage());
        }
        return output;
    }

}
