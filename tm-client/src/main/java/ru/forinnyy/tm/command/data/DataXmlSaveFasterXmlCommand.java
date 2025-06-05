package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

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
        getDomainService().saveDataXmlFasterXml();
    }

}
