package ru.forinnyy.tm.command.system;

public final class ApplicationVersionCommand extends AbstractSystemCommand {

    private static final String DESCRIPTION = "Show version info.";

    private static final String NAME = "version";

    private static final String ARGUMENT = "-v";

    @Override
    public void execute() {
        System.out.println("[VERSION]");
        System.out.println("1.20.0");
    }

    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
