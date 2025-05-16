package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerAboutResponse extends AbstractResponse {

    private String email;

    private String name;

}
