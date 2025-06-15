package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.DataXmlSaveJaxBRequest;
import ru.forinnyy.tm.enumerated.Role;

public final class DataXmlSaveJaxBCommand extends AbstractDataCommand {

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
        return "data-save-xml-jaxb";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[0];
    }

    @Override
    @SneakyThrows
    public void execute() {
        @NonNull final DataXmlSaveJaxBRequest request = new DataXmlSaveJaxBRequest(getToken());
        getDomainEndpoint().saveDataXmlJaxB(request);
    }

}
