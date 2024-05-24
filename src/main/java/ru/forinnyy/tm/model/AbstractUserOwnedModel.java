package ru.forinnyy.tm.model;


public abstract class AbstractUserOwnedModel extends AbstractModel {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
