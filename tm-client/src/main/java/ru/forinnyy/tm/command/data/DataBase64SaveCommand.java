package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataBase64SaveRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataBase64SaveCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "data-save-base64";

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataBase64SaveRequest request = new DataBase64SaveRequest();
        getDomainEndpoint().saveDataBase64(request);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save data to base64 file.";
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
