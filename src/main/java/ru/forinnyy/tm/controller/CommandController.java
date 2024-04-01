package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.ICommandController;
import ru.forinnyy.tm.api.ICommandService;
import ru.forinnyy.tm.model.Command;

import static ru.forinnyy.tm.util.FormatUtil.formatBytes;

public final class CommandController implements ICommandController {

    private final ICommandService commandService;

    public CommandController(final ICommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void showErrorArgument() {
        System.out.println("[ERROR]");
        System.err.println("The program arguments are not correct...");
        System.exit(1);
    }

    @Override
    public void showSystemInfo() {
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

    @Override
    public void showErrorCommand() {
        System.out.println("[ERROR]");
        System.err.println("The program command is not correct...");
    }

    @Override
    public void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.9.0");
    }

    @Override
    public void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("Name: Pavel Forinnyy");
        System.out.println("E-mail: forinnyy@gmail.com");
    }

    @Override
    public void showHelp() {
        System.out.println("[HELP]");
        for (Command command: commandService.getCommands()) System.out.println(command);
    }

}
