package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataJsonSaveFasterXmlRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataJsonSaveFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save data in json file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-save-json";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataJsonSaveFasterXmlRequest request = new DataJsonSaveFasterXmlRequest(getToken());
        getDomainEndpoint().saveDataJsonFasterXml(request);
    }

}
