package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.ICommandRepository;
import ru.forinnyy.tm.constant.ArgumentConst;
import ru.forinnyy.tm.constant.CommandConst;
import ru.forinnyy.tm.model.Command;

public final class CommandRepository implements ICommandRepository {

    private static final Command HELP = new Command(CommandConst.HELP, ArgumentConst.HELP, "Show command list.");

    private static final Command ABOUT = new Command(CommandConst.ABOUT, ArgumentConst.ABOUT, "Show developer info");

    private static final Command VERSION = new Command(CommandConst.VERSION, ArgumentConst.VERSION, "Show version info.");

    private static final Command INFO = new Command(CommandConst.INFO, ArgumentConst.INFO, "Show system info.");

    private static final Command EXIT = new Command(CommandConst.EXIT, null, "Close application.");

    private static final Command PROJECT_LIST = new Command(CommandConst.PROJECT_LIST, null, "Show project list.");

    private static final Command PROJECT_CREATE = new Command(CommandConst.PROJECT_CREATE, null, "Create new project.");

    private static final Command PROJECT_CLEAR = new Command(CommandConst.PROJECT_CLEAR, null, "Delete all projects.");

    private static final Command TASK_LIST = new Command(CommandConst.TASK_LIST, null, "Show task list.");

    private static final Command TASK_CREATE = new Command(CommandConst.TASK_CREATE, null, "Create new task.");

    private static final Command TASK_CLEAR = new Command(CommandConst.TASK_CLEAR, null, "Clear all tasks.");

    private static final Command[] COMMANDS = new Command[] {
            HELP, ABOUT, VERSION, INFO,
            PROJECT_LIST, PROJECT_CREATE, PROJECT_CLEAR,
            TASK_CLEAR, TASK_CREATE, TASK_LIST,
            EXIT
    };

    public Command[] getCommands() {
        return COMMANDS;
    }

}
