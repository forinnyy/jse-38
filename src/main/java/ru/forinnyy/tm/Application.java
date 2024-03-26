package ru.forinnyy.tm;

import ru.forinnyy.tm.api.ICommandRepository;
import ru.forinnyy.tm.constant.ArgumentConst;
import ru.forinnyy.tm.constant.CommandConst;
import ru.forinnyy.tm.model.Command;
import ru.forinnyy.tm.repository.CommandRepository;
import ru.forinnyy.tm.util.TerminalUtil;

import static ru.forinnyy.tm.util.FormatUtil.formatBytes;

public final class Application {

    private static final ICommandRepository COMMAND_REPOSITORY = new CommandRepository();
    
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

    private static void showSystemInfo() {
        System.out.println("[SYSTEM INFO]");
        final int processorCount = Runtime.getRuntime().availableProcessors();
        System.out.println("PROCESSORS: " + processorCount);

        final long maxMemory = Runtime.getRuntime().maxMemory();
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long usedMemory = totalMemory - freeMemory;

        System.out.println("MAX MEMORY: " + formatBytes(maxMemory));
        System.out.println("TOTAL MEMORY: " + formatBytes(totalMemory));
        System.out.println("FREE MEMORY: " + formatBytes(freeMemory));
        System.out.println("USED MEMORY: " + formatBytes(usedMemory));
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
            case ArgumentConst.INFO:
                showSystemInfo();
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
            case CommandConst.INFO:
                showSystemInfo();
                break;
            default:
                showErrorCommand();
        }
    }

    private static void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.8.0");
    }

    private static void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("Name: Pavel Forinnyy");
        System.out.println("E-mail: forinnyy@gmail.com");
    }

    private static void showHelp() {
        System.out.println("[HELP]");
        for (Command command: COMMAND_REPOSITORY.getCommands()) System.out.println(command);
    }

}