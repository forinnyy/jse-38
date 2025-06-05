package ru.forinnyy.tm.api.service;

public interface IDomainService {

    void loadDataBackup();

    void saveDataBackup();

    void loadDataBase64();

    void saveDataBase64();

    void loadDataBinary();

    void saveDataBinary();

    void loadDataJsonFasterXml();

    void saveDataJsonFasterXml();

    void loadDataJsonJaxB();

    void saveDataJsonJaxB();

    void loadDataXmlFasterXml();

    void saveDataXmlFasterXml();

    void loadDataXmlJaxB();

    void saveDataXmlJaxB();

    void loadDataYamlFasterXml();

    void saveDataYamlFasterXml();

}
