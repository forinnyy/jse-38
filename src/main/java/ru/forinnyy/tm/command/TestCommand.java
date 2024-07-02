package ru.forinnyy.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public class TestCommand extends AbstractCommand {

    @Nullable
    @Override
    public String getArgument() {
        return null;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "test command";
    }

    @NotNull
    @Override
    public String getName() {
        return "test";
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return null;
    }

    @Override
    public void execute() throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        System.out.println("TEST");
    }

}
