package ru.forinnyy.tm.command.system;

import lombok.NonNull;

public final class ApplicationVersionCommand extends AbstractSystemCommand {

    @NonNull
    private static final String DESCRIPTION = "Show version info.";

    @NonNull
    private static final String NAME = "version";

    @NonNull
    private static final String ARGUMENT = "-v";

    @Override
    public void execute() {
        System.out.println("[VERSION]");
        System.out.println(getPropertyService().getApplicationVersion());
    }

    @NonNull
    @Override
    public String getArgument() {
        return ARGUMENT;
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
