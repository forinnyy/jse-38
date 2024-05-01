package ru.forinnyy.tm.api.service;

public interface ILoggerService {

    void info(String message);

    void command(String message);

    void debug(String message);

    void error(Exception e);

}
