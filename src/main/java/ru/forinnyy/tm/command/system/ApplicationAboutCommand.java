package ru.forinnyy.tm.command.system;


public final class ApplicationAboutCommand extends AbstractSystemCommand {

    private static final String DESCRIPTION = "Show developer info";

    private static final String NAME = "about";

    private static final String ARGUMENT = "-a";

    @Override
    public void execute() {
        System.out.println("[ABOUT]");
        System.out.println("Name: Pavel Forinnyy");
        System.out.println("E-mail: forinnyy@gmail.com");
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
