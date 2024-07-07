package ru.forinnyy.tm.command.system;


import org.jetbrains.annotations.NotNull;

public final class ApplicationAboutCommand extends AbstractSystemCommand {

    @NotNull
    private static final String DESCRIPTION = "Show developer info";

    @NotNull
    private static final String NAME = "about";

    @NotNull
    private static final String ARGUMENT = "-a";

    @Override
    public void execute() {
        System.out.println("[ABOUT]");
        System.out.println("Name: " + getPropertyService().getAuthorName());
        System.out.println("E-mail: " + getPropertyService().getAuthorEmail());
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
