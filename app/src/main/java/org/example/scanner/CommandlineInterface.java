package org.example.scanner;

import java.util.ArrayList;
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
        question = sanitizeQuestion(question);

        List<String> acceptedAnswers = new ArrayList<>();
        acceptedAnswers.addAll(yes);
        acceptedAnswers.addAll(no);

        String response = getResponseInXAttempts(3, question, acceptedAnswers);

        if (yes.contains(response)) {
            return ANSWER_YES;
        } else if (no.contains(response)) {
            return ANSWER_NO;
        }

        return response;
    }

    public static String askUserStringInput(String question){
        String response = getResponseInXAttempts(3, question, new ArrayList<>());
        return response;
    }

    public static String askUserChoice(String question, List<String> acceptedAnswers){
        String response = getResponseInXAttempts(3, question, acceptedAnswers);
        return response;
    }

    private static String sanitizeQuestion(String question){
        question = ">> " + question;
        if( !question.trim().endsWith("?") ){
            question = question.trim() + " ?";
        }
        return question;
    }

    private static String getResponseInXAttempts(int attempts, String question, List<String> acceptedAnswers){
        while(attempts > 0) {
            System.out.println(question);
            String response = CommandlineInterface.scanner.nextLine().trim();
            if(acceptedAnswers.contains(response.trim()) || acceptedAnswers.size() == 0)
                return response.trim();
            --attempts;
        }
        return ANSWER_NONE;
    }

}
