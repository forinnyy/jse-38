package ru.forinnyy.tm.component;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.command.data.AbstractDataCommand;
import ru.forinnyy.tm.command.data.DataBackupLoadCommand;
import ru.forinnyy.tm.command.data.DataBackupSaveCommand;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class Backup extends Thread {

    @NonNull
    private final Bootstrap bootstrap;

    public Backup(@NonNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        this.setDaemon(true);
    }

    public void init() {
        load();
        start();
    }

    public void save() {
        bootstrap.processCommand(DataBackupSaveCommand.NAME, false);
    }

    public void load() {
        if (!Files.exists(Paths.get(AbstractDataCommand.FILE_BACKUP))) return;
        bootstrap.processCommand(DataBackupLoadCommand.NAME, false);
    }

    @Override
    @SneakyThrows
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(3000);
            save();
        }
    }

}
