package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataYamlSaveFasterXmlRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataYamlSaveFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save data in yaml file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-save-yaml";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataYamlSaveFasterXmlRequest request = new DataYamlSaveFasterXmlRequest();
        getDomainEndpoint().saveDataYamlFasterXml(request);
    }

}
