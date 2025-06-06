package ru.forinnyy.tm.command.system;


import lombok.NonNull;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.ApplicationAboutRequest;
import ru.forinnyy.tm.dto.request.ApplicationGitRequest;
import ru.forinnyy.tm.dto.request.ApplicationVersionRequest;
import ru.forinnyy.tm.dto.response.ApplicationAboutResponse;
import ru.forinnyy.tm.dto.response.ApplicationGitResponse;

public final class ApplicationAboutCommand extends AbstractSystemCommand {

    @NonNull
    private static final String DESCRIPTION = "Show developer info";

    @NonNull
    private static final String NAME = "about";

    @NonNull
    private static final String ARGUMENT = "-a";

    @Override
    public void execute() {
        @NonNull final ApplicationAboutRequest requestA = new ApplicationAboutRequest();
        @NonNull final ApplicationGitRequest requestG = new ApplicationGitRequest();
        @NonNull final ApplicationAboutResponse responseA = getSystemEndpointClient().getAbout(requestA);
        @NonNull final ApplicationGitResponse responseG = getSystemEndpointClient().getGit(requestG);

        System.out.println("[ABOUT]");
        System.out.println("AUTHOR: " + responseA.getName());
        System.out.println("E-MAIL: " + responseA.getEmail());
        System.out.println();

        System.out.println("[GIT]");
        System.out.println("BRANCH: " + responseG.getGitBranch());
        System.out.println("COMMIT ID: " + responseG.getGitCommitId());
        System.out.println("COMMITTER: " + responseG.getGitCommitterName());
        System.out.println("E-MAIL: " + responseG.getGitCommitterEmail());
        System.out.println("MESSAGE: " + responseG.getGitCommitMessage());
        System.out.println("TIME: " + responseG.getGitCommitTime());
    }

    @NonNull
    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @NonNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

}
