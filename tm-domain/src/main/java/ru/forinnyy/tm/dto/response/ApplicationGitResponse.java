package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationGitResponse extends AbstractResponse {

    private String gitBranch;

    private String gitCommitId;

    private String gitCommitterName;

    private String gitCommitterEmail;

    private String gitCommitMessage;

    private String gitCommitTime;

}
