package ru.forinnyy.tm.command;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public class TestCommand extends AbstractCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "test command";
    }

    @NonNull
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public Role[] getRoles() {
        return null;
    }

    @Override
    public void execute() throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        System.out.println("TEST");
    }

}
