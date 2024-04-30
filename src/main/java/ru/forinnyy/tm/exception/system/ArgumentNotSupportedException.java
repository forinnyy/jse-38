package ru.forinnyy.tm.exception.system;

public final class ArgumentNotSupportedException extends AbstractSystemException {

    public ArgumentNotSupportedException() {
        super("Error! Argument is not supported...");
    }

    public ArgumentNotSupportedException(String argument) {
        super("Error! Argument ``" + argument + "`` is not supported...");
    }

}
