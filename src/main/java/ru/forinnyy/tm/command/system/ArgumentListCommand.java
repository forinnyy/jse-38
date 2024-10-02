package ru.forinnyy.tm.command.system;

import lombok.NonNull;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public final class ArgumentListCommand extends AbstractSystemCommand {

    @NonNull
    private static final String DESCRIPTION = "Display list of arguments.";

    @NonNull
    private static final String NAME = "arguments";

    @NonNull
    private static final String ARGUMENT = "-args";

    @Override
    public void execute() {
        System.out.println("[LIST OF COMMANDS]");
        final Collection<AbstractCommand> arguments = getCommandService().getTerminalArguments();
        for (@NonNull final ICommand argument : arguments) System.out.println(argument);
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
