package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

public interface IDomainEndpoint {

    @NonNull
    DataBackupLoadResponse loadDataBackup(@NonNull DataBackupLoadRequest request);

    @NonNull
    DataBackupSaveResponse saveDataBackup(@NonNull DataBackupSaveRequest request);

    @NonNull
    DataBase64LoadResponse loadDataBase64(@NonNull DataBase64LoadRequest request);

    @NonNull
    DataBase64SaveResponse saveDataBase64(@NonNull DataBase64SaveRequest request);

    @NonNull
    DataBinaryLoadResponse loadDataBinary(@NonNull DataBinaryLoadRequest request);

    @NonNull
    DataBinarySaveResponse saveDataBinary(@NonNull DataBinarySaveRequest request);

    @NonNull
    DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(@NonNull DataJsonLoadFasterXmlRequest request);

    @NonNull
    DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(@NonNull DataJsonSaveFasterXmlRequest request);

    @NonNull
    DataJsonLoadJaxBResponse loadDataJsonJaxB(@NonNull DataJsonLoadJaxBRequest request);

    @NonNull
    DataJsonSaveJaxBResponse saveDataJsonJaxB(@NonNull DataJsonSaveJaxBRequest request);

    @NonNull
    DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(@NonNull DataXmlLoadFasterXmlRequest request);

    @NonNull
    DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(@NonNull DataXmlSaveFasterXmlRequest request);

    @NonNull
    DataXmlLoadJaxBResponse loadDataXmlJaxB(@NonNull DataXmlLoadJaxBRequest request);

    @NonNull
    DataXmlSaveJaxBResponse saveDataXmlJaxB(@NonNull DataXmlSaveJaxBRequest request);

}
