package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public interface ICommandRepository {

    void add(AbstractCommand command);

    @Nullable
    AbstractCommand getCommandByArgument(String argument);

    @Nullable
    AbstractCommand getCommandByName(String name);

    @NotNull
    Collection<AbstractCommand> getTerminalCommands();

    @NotNull
    Collection<AbstractCommand> getTerminalArguments();

}
