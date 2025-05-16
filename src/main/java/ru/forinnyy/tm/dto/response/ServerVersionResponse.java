package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerVersionResponse extends AbstractResponse {

    private String version;

}
