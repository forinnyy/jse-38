package ru.forinnyy.tm.service;

import com.jcabi.manifests.Manifests;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.service.IPropertyService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.ClassLoader.getSystemResourceAsStream;

public final class PropertyService implements IPropertyService {

    @NonNull
    public static final String APPLICATION_FILE_NAME_KEY = "config";

    @NonNull
    public static final String APPLICATION_FILE_NAME_DEFAULT = "application.properties";

    @NonNull
    public static final String APPLICATION_VERSION_KEY = "buildNumber";

    @NonNull
    public static final String PORT = "server.port";

    @NonNull
    public static final String PORT_DEFAULT = "6060";

    @NonNull
    public static final String EMPTY_VALUE = "---";


    @NonNull
    private final Properties properties = new Properties();

    @SneakyThrows
    public PropertyService() {
        final boolean existsConfig = isExistsExternalConfig();
        if (existsConfig) loadExternalConfig(properties);
        else loadInternalConfig(properties);
    }

    @SneakyThrows
    private void loadInternalConfig(@NonNull final Properties properties) {
        @NonNull final String name = APPLICATION_FILE_NAME_DEFAULT;
        @Cleanup final InputStream inputStream = getSystemResourceAsStream(name);
        if (inputStream == null) return;
        properties.load(inputStream);
    }

    @SneakyThrows
    private void loadExternalConfig(@NonNull final Properties properties) {
        @NonNull final String name = getApplicationConfig();
        @NonNull final File file = new File(name);
        @Cleanup @NonNull final InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
    }

    private boolean isExistsExternalConfig() {
        @NonNull final String name = getApplicationConfig();
        @NonNull final File file = new File(name);
        return file.exists();
    }

    @NonNull
    @Override
    public String getApplicationConfig() {
        return getStringValue(APPLICATION_FILE_NAME_KEY, APPLICATION_FILE_NAME_DEFAULT);
    }

    @NonNull
    private String read(final String key) {
        if (key == null || key.isEmpty()) return EMPTY_VALUE;
        if (!Manifests.exists(key)) return EMPTY_VALUE;
        return Manifests.read(key);
    }

    @Override
    public @NonNull Integer getServerPort() {
        return getIntegerValue(PORT, PORT_DEFAULT);
    }

    @NonNull
    private String getEnvKey(@NonNull final String key) {
        return key.replace(".", "_").toUpperCase();
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
