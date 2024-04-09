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

    private static final Command PROJECT_SHOW_BY_INDEX = new Command(CommandConst.PROJECT_SHOW_BY_INDEX, null, "Show project by index.");

    private static final Command PROJECT_SHOW_BY_ID = new Command(CommandConst.PROJECT_SHOW_BY_ID, null, "Show project by id.");

    private static final Command PROJECT_UPDATE_BY_INDEX = new Command(CommandConst.PROJECT_UPDATE_BY_INDEX, null, "Update project by index.");

    private static final Command PROJECT_UPDATE_BY_ID = new Command(CommandConst.PROJECT_UPDATE_BY_ID, null, "Update project by id.");

    private static final Command PROJECT_REMOVE_BY_INDEX = new Command(CommandConst.PROJECT_REMOVE_BY_INDEX, null, "Remove project by index.");

    private static final Command PROJECT_REMOVE_BY_ID = new Command(CommandConst.PROJECT_REMOVE_BY_ID, null, "Remove project by id.");

    private static final Command TASK_LIST = new Command(CommandConst.TASK_LIST, null, "Show task list.");

    private static final Command TASK_CREATE = new Command(CommandConst.TASK_CREATE, null, "Create new task.");

    private static final Command TASK_CLEAR = new Command(CommandConst.TASK_CLEAR, null, "Clear all tasks.");

    private static final Command TASK_SHOW_BY_INDEX = new Command(CommandConst.TASK_SHOW_BY_INDEX, null, "Show task by index.");

    private static final Command TASK_SHOW_BY_ID = new Command(CommandConst.TASK_SHOW_BY_ID, null, "Show task by id.");

    private static final Command TASK_UPDATE_BY_INDEX = new Command(CommandConst.TASK_UPDATE_BY_INDEX, null, "Update task by index.");

    private static final Command TASK_UPDATE_BY_ID = new Command(CommandConst.TASK_UPDATE_BY_ID, null, "Update task by id.");

    private static final Command TASK_REMOVE_BY_INDEX = new Command(CommandConst.TASK_REMOVE_BY_INDEX, null, "Remove task by index.");

    private static final Command TASK_REMOVE_BY_ID = new Command(CommandConst.TASK_REMOVE_BY_ID, null, "Remove task by id.");

    private static final Command[] COMMANDS = new Command[] {
            HELP, ABOUT, VERSION, INFO, EXIT,

            PROJECT_LIST, PROJECT_CREATE, PROJECT_CLEAR,
            PROJECT_SHOW_BY_INDEX, PROJECT_SHOW_BY_ID, PROJECT_UPDATE_BY_INDEX,
            PROJECT_UPDATE_BY_ID, PROJECT_REMOVE_BY_INDEX, PROJECT_REMOVE_BY_ID,

            TASK_CLEAR, TASK_CREATE, TASK_LIST,
            TASK_SHOW_BY_INDEX, TASK_SHOW_BY_ID, TASK_UPDATE_BY_INDEX,
            TASK_UPDATE_BY_ID, TASK_REMOVE_BY_INDEX, TASK_REMOVE_BY_ID
    };

    public Command[] getCommands() {
        return COMMANDS;
    }

}
