package ru.forinnyy.tm.service;

import lombok.SneakyThrows;
import lombok.NonNull;
import ru.forinnyy.tm.api.service.IPropertyService;

import java.util.Properties;

public final class PropertyService implements IPropertyService {

    @NonNull
    public static final String FILE_NAME = "application.properties";

    @NonNull
    public static final String APPLICATION_VERSION_KEY = "application.version";

    @NonNull
    public static final String AUTHOR_EMAIL_KEY = "author.email";

    @NonNull
    public static final String AUTHOR_NAME_KEY = "author.name";

    @NonNull
    public static final String PASSWORD_ITERATION_DEFAULT = "25456";

    @NonNull
    public static final String PASSWORD_ITERATION_KEY = "password.iteration";

    @NonNull
    public static final String PASSWORD_SECRET_DEFAULT = "345345345345";

    @NonNull
    public static final String PASSWORD_SECRET_KEY = "password.secret";

    @NonNull
    public static final String EMPTY_VALUE = "--//--";

    @NonNull
    private final Properties properties = new Properties();

    @SneakyThrows
    public PropertyService() {
        properties.load(ClassLoader.getSystemResourceAsStream(FILE_NAME));
    }

    @NonNull
    @Override
    public String getApplicationVersion() {
        return getStringValue(APPLICATION_VERSION_KEY);
    }

    @NonNull
    @Override
    public String getAuthorEmail() {
        return getStringValue(AUTHOR_EMAIL_KEY);
    }

    @NonNull
    @Override
    public String getAuthorName() {
        return getStringValue(AUTHOR_NAME_KEY);
    }

    @NonNull
    private String getEnvKey(@NonNull final String key) {
        return key.replace(".", "_").toUpperCase();
    }

    @NonNull
    @Override
    public Integer getPasswordIteration() {
        return getIntegerValue(PASSWORD_ITERATION_KEY, PASSWORD_ITERATION_DEFAULT);
    }

    @NonNull
    @Override
    public String getPasswordSecret() {
        return getStringValue(PASSWORD_SECRET_KEY, PASSWORD_SECRET_DEFAULT);
    }

    @NonNull
    private Integer getIntegerValue(@NonNull final String key, @NonNull final String defaultValue) {
        return Integer.parseInt(getStringValue(key, defaultValue));
    }

    @NonNull
    private String getStringValue(@NonNull final String key, @NonNull final String defaultValue) {
        if (System.getProperties().containsKey(key)) return System.getProperties().getProperty(key);
        @NonNull final String envKey = getEnvKey(key);
        if (System.getenv().containsKey(envKey)) return System.getenv(envKey);
        return properties.getProperty(key, defaultValue);
    }

    @NonNull
    private String getStringValue(@NonNull final String key) {
        return getStringValue(key, EMPTY_VALUE);
    }

}
