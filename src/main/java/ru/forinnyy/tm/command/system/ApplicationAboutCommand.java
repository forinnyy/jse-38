package ru.forinnyy.tm.command.system;


import lombok.NonNull;
import ru.forinnyy.tm.api.service.IPropertyService;

public final class ApplicationAboutCommand extends AbstractSystemCommand {

    @NonNull
    private static final String DESCRIPTION = "Show developer info";

    @NonNull
    private static final String NAME = "about";

    @NonNull
    private static final String ARGUMENT = "-a";

    @Override
    public void execute() {
        @NonNull final IPropertyService service = getPropertyService();
        System.out.println("[ABOUT]");
        System.out.println("AUTHOR: " + service.getAuthorName());
        System.out.println("E-MAIL: " + service.getAuthorEmail());
        System.out.println();

        System.out.println("[GIT]");
        System.out.println("BRANCH: " + service.getGitBranch());
        System.out.println("COMMIT ID: " + service.getGitCommitId());
        System.out.println("COMMITTER: " + service.getGitCommitterName());
        System.out.println("E-MAIL: " + service.getGitCommitterEmail());
        System.out.println("MESSAGE: " + service.getGitCommitMessage());
        System.out.println("TIME: " + service.getGitCommitTime());
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
