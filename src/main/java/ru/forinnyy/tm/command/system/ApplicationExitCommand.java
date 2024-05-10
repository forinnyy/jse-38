package ru.forinnyy.tm.command.system;


public class ApplicationExitCommand extends AbstractSystemCommand {

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
    public String getDecription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
