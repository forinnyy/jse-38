package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Command;

public interface ICommandRepository {

    Command[] getCommands();

}
