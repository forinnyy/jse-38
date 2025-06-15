package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataXmlSaveFasterXmlRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataXmlSaveFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save data in xml file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-save-xml";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataXmlSaveFasterXmlRequest request = new DataXmlSaveFasterXmlRequest(getToken());
        getDomainEndpoint().saveDataXmlFasterXml(request);
    }

}
