package ru.forinnyy.tm.command.system;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ApplicationExitCommand extends AbstractSystemCommand {

    @NotNull
    private static final String DESCRIPTION = "Close application.";

    @NotNull
    private static final String NAME = "exit";

    @Override
    public void execute() {
        System.exit(0);
    }

    @Nullable
    @Override
    public String getArgument() {
        return null;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

}
