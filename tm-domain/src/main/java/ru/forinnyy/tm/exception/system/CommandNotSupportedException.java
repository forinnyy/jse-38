package ru.forinnyy.tm.exception.system;

public final class CommandNotSupportedException extends AbstractSystemException {

    public CommandNotSupportedException() {
        super("Error! Command is not supported...");
    }

    public CommandNotSupportedException(String command) {
        super("Error! Command ``" + command + "`` is not supported...");
    }

}
