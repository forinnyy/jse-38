package ru.forinnyy.tm.component;

import lombok.NonNull;
import ru.forinnyy.tm.command.data.AbstractDataCommand;
import ru.forinnyy.tm.command.data.DataBackupLoadCommand;
import ru.forinnyy.tm.command.data.DataBackupSaveCommand;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Backup {

    @NonNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    @NonNull
    private final Bootstrap bootstrap;

    public Backup(@NonNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void start() {
        load();
        es.scheduleWithFixedDelay(this::save, 0, 3, TimeUnit.SECONDS);
    }

    public void stop() {
        es.shutdown();
    }

    public void save() {
        bootstrap.processCommand(DataBackupSaveCommand.NAME, false);
    }

    public void load() {
        if (!Files.exists(Paths.get(AbstractDataCommand.FILE_BACKUP))) return;
        bootstrap.processCommand(DataBackupLoadCommand.NAME, false);
    }

}
