package org.example.scanner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandlineInterface {

    public static Scanner scanner = new Scanner(System.in);
    public static String ANSWER_YES = "YES";
    public static String ANSWER_NO = "NO";
    public static String ANSWER_NONE = "NONE";
    private static List<String> yes = Arrays.asList("Yes", "yes", "y");
    private static List<String> no = Arrays.asList("No", "no", "n");

    public static String askUserYesNo(String question){
        int getAnswerAttempt = 3;
        question = ">> " + question;
        if( !question.trim().endsWith("?") ){
            question = question.trim() + " ?";
        }

        while(getAnswerAttempt > 0) {
            System.out.println(question);
            String response = CommandlineInterface.scanner.nextLine().trim();
            if (yes.contains(response)) {
                return ANSWER_YES;
            } else if (no.contains(response)) {
                return ANSWER_NO;
            }
            --getAnswerAttempt;
        }
        return ANSWER_NONE;
    }

}
