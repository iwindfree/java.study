package io.windfree.util;

import java.util.Scanner;

public class ConsoleUtil {
    public static void readConsole() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("input = " + input);
    }
    public static void main(String[] args) {
        ConsoleUtil.readConsole();
    }
}
