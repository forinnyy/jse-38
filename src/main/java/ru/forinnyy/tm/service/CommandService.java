package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.ICommandRepository;
import ru.forinnyy.tm.api.service.ICommandService;
import ru.forinnyy.tm.model.Command;

public final class CommandService implements ICommandService {

    private final ICommandRepository commandRepository;

    public CommandService(final ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    public Command[] getCommands() {
        return commandRepository.getCommands();
    }

}
