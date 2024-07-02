package ru.forinnyy.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.ICommandRepository;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public final class CommandRepository implements ICommandRepository {

    @NotNull
    private final Map<String, AbstractCommand> mapByArgument = new TreeMap<>();

    @NotNull
    private final Map<String, AbstractCommand> mapByName = new TreeMap<>();

    @Override
    public void add(@Nullable final AbstractCommand command) {
        if (command == null) return;
        @NotNull final String name = command.getName();
        if (!name.isEmpty()) mapByName.put(name, command);
        @Nullable final String argument = command.getArgument();
        if (argument != null && !argument.isEmpty()) mapByArgument.put(argument, command);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByArgument(@Nullable final String argument) {
        if (argument == null || argument.isEmpty()) return null;
        return mapByArgument.get(argument);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByName(@NotNull final String name) {
        if (name.isEmpty()) return null;
        return mapByName.get(name);
    }

    @NotNull
    @Override
    public Collection<AbstractCommand> getTerminalCommands() {
        return mapByName.values();
    }

    @NotNull
    @Override
    public Collection<AbstractCommand> getTerminalArguments() {
        return mapByArgument.values();
    }

}
