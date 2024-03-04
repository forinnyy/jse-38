package ru.forinnyy.tm;

import ru.forinnyy.tm.constant.ArgumentConst;

public class Application {
    
    public static void main(String[] args) {
        processArguments(args);
    }

    public static void processArguments(final String[] args) {
        if (args == null || args.length < 1) {
            showError();
            return;
        }
        processArgument(args[0]);
    }

    public static void showError() {
        System.out.println("[ERROR]");
        System.err.println("The program arguments are not correct...");
    }

    public static void processArgument(final String arg) {
        switch (arg) {
            case ArgumentConst.VERSION:
                showVersion();
                break;
            case ArgumentConst.ABOUT:
                showAbout();
                break;
            case ArgumentConst.HELP:
                showHelp();
                break;
            default:
                showError();
        }
    }

    public static void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.2.0");
    }

    public static void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("Name: Pavel Forinnyy");
        System.out.println("E-mail: forinnyy@gmail.com");
    }

    public static void showHelp() {
        System.out.println("[HELP]");
        System.out.printf("%s - Show version info.\n", ArgumentConst.VERSION);
        System.out.printf("%s - Show developer info.\n", ArgumentConst.ABOUT);
        System.out.printf("%s - Show command list.\n", ArgumentConst.HELP);
    }

}