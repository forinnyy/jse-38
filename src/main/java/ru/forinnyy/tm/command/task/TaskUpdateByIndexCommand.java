package ru.forinnyy.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class TaskUpdateByIndexCommand extends AbstractTaskCommand {

    @NotNull
    private static final String NAME = "task-update-by-index";

    @NotNull
    private static final String DESCRIPTION = "Update task by index.";

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() -1;
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getTaskService().updateByIndex(userId, index, name, description);
    }

}
