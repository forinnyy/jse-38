package ru.forinnyy.tm.command.system;

import org.jetbrains.annotations.NotNull;

public final class ApplicationVersionCommand extends AbstractSystemCommand {

    @NotNull
    private static final String DESCRIPTION = "Show version info.";

    @NotNull
    private static final String NAME = "version";

    @NotNull
    private static final String ARGUMENT = "-v";

    @Override
    public void execute() {
        System.out.println("[VERSION]");
        System.out.println(getPropertyService().getApplicationVersion());
    }

    @NotNull
    @Override
    public String getArgument() {
        return ARGUMENT;
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
