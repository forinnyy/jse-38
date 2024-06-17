package ru.forinnyy.tm.command.system;


import org.jetbrains.annotations.NotNull;

public final class ApplicationExitCommand extends AbstractSystemCommand {

    private static final String DESCRIPTION = "Close application.";

    private static final String NAME = "exit";

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public @NotNull String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getName() {
        return NAME;
    }

}
