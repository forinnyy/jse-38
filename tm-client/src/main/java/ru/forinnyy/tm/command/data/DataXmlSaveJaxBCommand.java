package ru.forinnyy.tm.command.data;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

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
        getDomainService().saveDataXmlJaxB();
    }

}
