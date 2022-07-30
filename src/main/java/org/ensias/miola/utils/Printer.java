package org.ensias.miola.utils;

import org.ensias.miola.datatypes.Colors;
import org.ensias.miola.entities.Transaction;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Printer {
    private static final PrintStream STREAM = System.out;

    public static final String CURRENNCY = "MAD";

    public static final String DECIMAL_FORMAT = "0.00";

    public static final String TIME_FORMAT = "dd/MM/yyyy HH:mm";

    private static final int LINE_LENGTH = 50;

    private static final String ANSI_RESET = "\u001B[0m";

    private static final Map<Colors, String> COLORS = new HashMap<>(){{
        put(Colors.BLACK , "\u001B[30m");
        put(Colors.RED   , "\u001B[31m");
        put(Colors.GREEN , "\u001B[32m");
        put(Colors.YELLOW, "\u001B[33m");
        put(Colors.BLUE  , "\u001B[34m");
        put(Colors.PURPLE, "\u001B[35m");
        put(Colors.CYAN  , "\u001B[36m");
        put(Colors.WHITE , "\u001B[37m");
    }};

    public static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat(DECIMAL_FORMAT);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static void printc(String s, Colors c){
        STREAM.print(color(s,c));
    }

    public static void printlnc(String s, Colors c){
        STREAM.println(color(s,c));
    }

    public static void printLine(Colors color){
        for (int i = 0; i < LINE_LENGTH; i++) {
            STREAM.print(color("─", color));
        }
    }

    public static void printlnLine(Colors color){
        for (int i = 0; i < LINE_LENGTH; i++) {
            STREAM.print(color("─", color));
        }
        STREAM.println("");
    }

    public static String color(String s, Colors color){
        return COLORS.get(color)+s+ANSI_RESET;
    }

    public static void printTitle(String title){
        printlnLine(Colors.BLUE);
        printlnc(title, Colors.BLUE);
        printlnLine(Colors.BLUE);
    }

    public static void printMenu(String title, String[] options){
        int rest = LINE_LENGTH - title.length();
        title = title + " ".repeat(rest) + "│";
        printLine(Colors.YELLOW); System.out.println(color("┐", Colors.YELLOW));
        printlnc(title, Colors.YELLOW);
        printLine(Colors.YELLOW); System.out.println(color("┤", Colors.YELLOW));
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            rest = LINE_LENGTH - option.length();
            option = option + " ".repeat(rest);
            option += "│";
            System.out.println(color(option, Colors.YELLOW));
        }
        printLine(Colors.YELLOW); System.out.println(color("┘", Colors.YELLOW));
        System.out.print(ANSI_RESET+"Choose your option : ");
    }

    /*
     * Print Transactions History methods
     * */

    public static void printTransactionsHistory(List<Transaction> transactions){
        int longestAmountLength = getlongestAmountLength(transactions);

        String header = buildHeader(longestAmountLength);

        printlnc(header, Colors.BLUE);

        for (Transaction t : transactions){
            int amountSpacesNumber = 0;
            if (longestAmountLength > 5)
                amountSpacesNumber = longestAmountLength- t.getAmount().formatedValue().length();
            else
                amountSpacesNumber = 1;

            printlnc(t.toString(amountSpacesNumber), Colors.BLACK);
        }
    }

    public static int getlongestAmountLength(List<Transaction> transactions){
        int longestAmountLength = 0;

        for (Transaction t: transactions) {
            String amountString = t.getAmount().formatedValue();
            if (amountString.length() > longestAmountLength) longestAmountLength = amountString.length();
        }

        return longestAmountLength;
    }

    public static String buildHeader(int longestAmountLength){
        String[] columns = {"OPERATION", "TIME", "AMOUNT", "BALANCE AFTER TRANSACTION"};
        StringBuilder headerBuilder = new StringBuilder();

        String spacesAfterTimeTitle = " ".repeat(Printer.TIME_FORMAT.length()-columns[1].length());
        String spacesAfterAmountTitle = "";
        if (longestAmountLength > columns[2].length()){
            int numberOfSpacesToAdd = longestAmountLength-columns[2].length();
            spacesAfterAmountTitle = " ".repeat(numberOfSpacesToAdd);
        }

        headerBuilder.append(" ".repeat(2));
        headerBuilder.append(columns[0]+" ");
        headerBuilder.append(" | ");
        headerBuilder.append(columns[1]);
        headerBuilder.append(spacesAfterTimeTitle);
        headerBuilder.append(" | ");
        headerBuilder.append(columns[2]);
        headerBuilder.append(spacesAfterAmountTitle);
        headerBuilder.append(" | ");
        headerBuilder.append(columns[3]);

        return headerBuilder.toString();
    }

}
