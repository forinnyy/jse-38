package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.ICommandRepository;
import ru.forinnyy.tm.api.service.ICommandService;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;

public final class CommandService implements ICommandService {

    private final ICommandRepository commandRepository;

    public CommandService(final ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public void add(@Nullable final AbstractCommand command) {
        if (command == null) return;
        commandRepository.add(command);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByArgument(@Nullable final String argument) {
        if (argument == null || argument.isEmpty()) return null;
        return commandRepository.getCommandByArgument(argument);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByName(@NotNull final String name) {
        if (name.isEmpty()) return null;
        return commandRepository.getCommandByName(name);
    }

    @NotNull
    @Override
    public Collection<AbstractCommand> getTerminalCommands() {
        return commandRepository.getTerminalCommands();
    }

    @NotNull
    @Override
    public Collection<AbstractCommand> getTerminalArguments() {
        return commandRepository.getTerminalArguments();
    }

}
