package com.inspire12.practice.api.lab.toy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class ToyMain {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (!(input = br.readLine()).equals(" ")) {
            System.out.println(checkStarMacro(input));
        }
    }

    private static String checkStarMacro(String input) throws IOException {

        CharacterIterator it = new StringCharacterIterator(input);
        StringBuilder result = new StringBuilder();
        while (it.current() != CharacterIterator.DONE)
        {
            result.append(it.current());
            if(Character.isDigit(it.current())) {
                if (it.next() != 'e') {
                    result.append(ANSI_RED).append(it.current()).append(ANSI_RESET);
                } else {
                    result.append(it.current());
                }
                if (it.next() != 'w') {
                    result.append(ANSI_RED).append(it.current()).append(ANSI_RESET);
                } else {
                    result.append(it.current());
                }
            }

            it.next();
        }
        return result.toString();
    }
}
