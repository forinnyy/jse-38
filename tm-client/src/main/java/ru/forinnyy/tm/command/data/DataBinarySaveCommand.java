package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataBinarySaveRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataBinarySaveCommand extends AbstractDataCommand {

    @NonNull
    public static final String NAME = "data-save-bin";

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataBinarySaveRequest request = new DataBinarySaveRequest(getToken());
        getDomainEndpoint().saveDataBinary(request);
    }

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save data to binary file.";
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
