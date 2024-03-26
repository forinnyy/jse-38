package ru.forinnyy.tm.model;

public final class Command {

    private String name;

    private String argument;

    private String description;

    public Command() {
    }

    public Command(
            final String name,
            final String argument,
            final String description
    ) {
        this.name = name;
        this.argument = argument;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "";
        final boolean hasName = name != null && !name.isEmpty();
        final boolean hasArgument = argument != null && !argument.isEmpty();
        final boolean hasDescription = description != null && !description.isEmpty();
        if (hasName) result += name;
        if (hasArgument) result += hasName ? ", " + argument : argument;
        if (hasDescription) result += " : " + description;
        return result;
    }
    
}
