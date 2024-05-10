package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.model.Command;

import java.util.Collection;

public interface ICommandRepository {

    void add(AbstractCommand command);

    AbstractCommand getCommandByArgument(String argument);

    AbstractCommand getCommandByName(String name);

    Collection<AbstractCommand> getTerminalCommands();

}
