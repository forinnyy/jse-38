package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ApplicationAboutResponse extends AbstractResponse {

    private String email;

    private String name;

    private String gitBranch;

    private String gitCommitId;

    private String gitCommitterName;

    private String gitCommitterEmail;

    private String gitCommitMessage;

    private String gitCommitTime;

}
