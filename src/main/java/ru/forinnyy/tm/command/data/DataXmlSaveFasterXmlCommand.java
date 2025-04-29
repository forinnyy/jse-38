package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Cleanup;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

import javax.naming.AuthenticationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public final class DataXmlSaveFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public @NonNull String getDescription() {
        return "Save data in xml file.";
    }

    @Override
    public @NonNull String getName() {
        return "data-save-xml";
    }

    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println("[DATA SAVE XML]");
        @NonNull final Domain domain = getDomain();
        @NonNull final File file = new File(FILE_XML);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @Cleanup @NonNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        @NonNull final ObjectMapper objectMapper = new XmlMapper();
        @NonNull final String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(domain);
        fileOutputStream.write(xml.getBytes());
        fileOutputStream.flush();
    }

}
