package ru.forinnyy.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public final class ArgumentListCommand extends AbstractSystemCommand {

    @NotNull
    private static final String DESCRIPTION = "Display list of arguments.";

    @NotNull
    private static final String NAME = "arguments";

    @NotNull
    private static final String ARGUMENT = "-args";

    @Override
    public void execute() {
        System.out.println("[LIST OF COMMANDS]");
        final Collection<AbstractCommand> arguments = getCommandService().getTerminalArguments();
        for (@NotNull final ICommand argument : arguments) System.out.println(argument);
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
