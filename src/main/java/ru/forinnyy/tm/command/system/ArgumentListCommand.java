package ru.forinnyy.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public final class ArgumentListCommand extends AbstractSystemCommand {

    private static final String DESCRIPTION = "Display list of arguments.";

    private static final String NAME = "arguments";

    private static final String ARGUMENT = "-args";

    @Override
    public void execute() {
        System.out.println("[LIST OF COMMANDS]");
        final Collection<AbstractCommand> arguments = getCommandService().getTerminalArguments();
        for (final ICommand argument : arguments) System.out.println(argument);
    }

    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @Override
    public @NotNull String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getName() {
        return NAME;
    }

}
