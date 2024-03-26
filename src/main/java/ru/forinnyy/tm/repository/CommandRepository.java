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

    private static final Command[] COMMANDS = new Command[] {
            HELP, ABOUT, VERSION, INFO, EXIT
    };

    public Command[] getCommands() {
        return COMMANDS;
    }

}
