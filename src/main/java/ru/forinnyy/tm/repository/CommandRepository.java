package ru.forinnyy.tm.repository;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.ICommandRepository;
import ru.forinnyy.tm.command.AbstractCommand;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public final class CommandRepository implements ICommandRepository {

    @NonNull
    private final Map<String, AbstractCommand> mapByArgument = new TreeMap<>();

    @NonNull
    private final Map<String, AbstractCommand> mapByName = new TreeMap<>();

    @Override
    public void add(final AbstractCommand command) {
        if (command == null) return;
        @NonNull final String name = command.getName();
        if (!name.isEmpty()) mapByName.put(name, command);
        final String argument = command.getArgument();
        if (argument != null && !argument.isEmpty()) mapByArgument.put(argument, command);
    }

    @Override
    public AbstractCommand getCommandByArgument(final String argument) {
        if (argument == null || argument.isEmpty()) return null;
        return mapByArgument.get(argument);
    }

    @Override
    public AbstractCommand getCommandByName(@NonNull final String name) {
        if (name.isEmpty()) return null;
        return mapByName.get(name);
    }

    @NonNull
    @Override
    public Collection<AbstractCommand> getTerminalCommands() {
        return mapByName.values();
    }

    @NonNull
    @Override
    public Collection<AbstractCommand> getTerminalArguments() {
        return mapByArgument.values();
    }

}
