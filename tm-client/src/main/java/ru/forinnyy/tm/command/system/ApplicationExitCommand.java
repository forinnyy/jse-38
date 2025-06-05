package ru.forinnyy.tm.command.system;


import lombok.NonNull;

public final class ApplicationExitCommand extends AbstractSystemCommand {

    @NonNull
    private static final String DESCRIPTION = "Close application.";

    @NonNull
    private static final String NAME = "exit";

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

}
