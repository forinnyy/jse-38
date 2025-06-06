package ru.forinnyy.tm.client;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IDomainEndpointClient;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

@NoArgsConstructor
public class DomainEndpointClient extends AbstractEndpointClient implements IDomainEndpointClient {


    @NonNull
    @Override
    @SneakyThrows
    public DataBackupLoadResponse loadDataBackup(@NonNull DataBackupLoadRequest request) {
        return call(request, DataBackupLoadResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataBackupSaveResponse saveDataBackup(@NonNull DataBackupSaveRequest request) {
        return call(request, DataBackupSaveResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataBase64LoadResponse loadDataBase64(@NonNull DataBase64LoadRequest request) {
        return call(request, DataBase64LoadResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataBase64SaveResponse saveDataBase64(@NonNull DataBase64SaveRequest request) {
        return call(request, DataBase64SaveResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataBinaryLoadResponse loadDataBinary(@NonNull DataBinaryLoadRequest request) {
        return call(request, DataBinaryLoadResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataBinarySaveResponse saveDataBinary(@NonNull DataBinarySaveRequest request) {
        return call(request, DataBinarySaveResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(@NonNull DataJsonLoadFasterXmlRequest request) {
        return call(request, DataJsonLoadFasterXmlResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(@NonNull DataJsonSaveFasterXmlRequest request) {
        return call(request, DataJsonSaveFasterXmlResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataJsonLoadJaxBResponse loadDataJsonJaxB(@NonNull DataJsonLoadJaxBRequest request) {
        return call(request, DataJsonLoadJaxBResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataJsonSaveJaxBResponse saveDataJsonJaxB(@NonNull DataJsonSaveJaxBRequest request) {
        return call(request, DataJsonSaveJaxBResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(@NonNull DataXmlLoadFasterXmlRequest request) {
        return call(request, DataXmlLoadFasterXmlResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(@NonNull DataXmlSaveFasterXmlRequest request) {
        return call(request, DataXmlSaveFasterXmlResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataXmlLoadJaxBResponse loadDataXmlJaxB(@NonNull DataXmlLoadJaxBRequest request) {
        return call(request, DataXmlLoadJaxBResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataXmlSaveJaxBResponse saveDataXmlJaxB(@NonNull DataXmlSaveJaxBRequest request) {
        return call(request, DataXmlSaveJaxBResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataYamlLoadFasterXmlResponse loadDataYamlFasterXml(@NonNull DataYamlLoadFasterXmlRequest request) {
        return call(request, DataYamlLoadFasterXmlResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public DataYamlSaveFasterXmlResponse saveDataYamlFasterXml(@NonNull DataYamlSaveFasterXmlRequest request) {
        return call(request, DataYamlSaveFasterXmlResponse.class);
    }

}
