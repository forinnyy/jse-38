package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataBackupSaveRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataBackupSaveCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "backup-save";

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataBackupSaveRequest request = new DataBackupSaveRequest(getToken());
        getDomainEndpoint().saveDataBackup(request);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save backup in file.";
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

}
