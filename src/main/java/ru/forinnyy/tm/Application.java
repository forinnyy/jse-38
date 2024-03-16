package ru.forinnyy.tm;

import ru.forinnyy.tm.constant.ArgumentConst;
import ru.forinnyy.tm.constant.CommandConst;
import ru.forinnyy.tm.util.TerminalUtil;

public class Application {
    
    public static void main(String[] args) {
        processArguments(args);
        processCommands();
    }

    private static void processCommands() {
        System.out.println("*** *** WELCOME TO TASK MANAGER *** ***");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("ENTER COMMAND: ");
            final String command = TerminalUtil.nextLine();
            processCommand(command);
        }
    }

    private static void processArguments(final String[] args) {
        if (args == null || args.length < 1) return;
        processArgument(args[0]);
        exit();
    }

    private static void exit() {
        System.exit(0);
    }

    private static void showErrorArgument() {
        System.out.println("[ERROR]");
        System.err.println("The program arguments are not correct...");
        System.exit(1);
    }

    private static void showErrorCommand() {
        System.out.println("[ERROR]");
        System.err.println("The program command is not correct...");
    }

    private static void processArgument(final String arg) {
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
                showErrorArgument();
        }
    }

    private static void processCommand(final String arg) {
        switch (arg) {
            case CommandConst.VERSION:
                showVersion();
                break;
            case CommandConst.ABOUT:
                showAbout();
                break;
            case CommandConst.HELP:
                showHelp();
                break;
            case CommandConst.EXIT:
                exit();
                break;
            default:
                showErrorCommand();
        }
    }

    private static void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.6.0");
    }

    private static void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("Name: Pavel Forinnyy");
        System.out.println("E-mail: forinnyy@gmail.com");
    }

    private static void showHelp() {
        System.out.println("[HELP]");
        System.out.printf("%s, %s - Show version info.\n", CommandConst.VERSION, ArgumentConst.VERSION);
        System.out.printf("%s, %s - Show developer info.\n", CommandConst.ABOUT, ArgumentConst.ABOUT);
        System.out.printf("%s, %s - Show command list.\n", CommandConst.HELP, ArgumentConst.HELP);
        System.out.printf("%s - Close application.\n", CommandConst.EXIT);
    }

}