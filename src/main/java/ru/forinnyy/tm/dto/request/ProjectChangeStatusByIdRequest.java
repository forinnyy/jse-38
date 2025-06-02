package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectChangeStatusByIdRequest extends AbstractUserRequest {

    private String id;

    private String status;

}
