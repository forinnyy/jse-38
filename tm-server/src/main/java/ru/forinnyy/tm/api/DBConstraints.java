package ru.forinnyy.tm.api;

import lombok.NonNull;

public interface DBConstraints {

    @NonNull
    String TABLE_PROJECT = "tm_project";

    @NonNull
    String TABLE_TASK = "tm_task";

    @NonNull
    String TABLE_USER = "tm_user";

    @NonNull
    String TABLE_SESSION = "tm_session";

    @NonNull
    String COLUMN_ID = "id";

    @NonNull
    String COLUMN_NAME = "name";

    @NonNull
    String COLUMN_CREATED = "created";

    @NonNull
    String COLUMN_DESCRIPTION = "description";

    @NonNull
    String COLUMN_USER_ID = "user_id";

    @NonNull
    String COLUMN_STATUS = "status";

    @NonNull
    String COLUMN_ROLE = "role";

    @NonNull
    String COLUMN_PROJECT_ID = "project_id";

    @NonNull
    String COLUMN_LOGIN = "login";

    @NonNull
    String COLUMN_PASSWORD = "password";

    @NonNull
    String COLUMN_EMAIL = "email";

    @NonNull
    String COLUMN_LOCKED = "locked";

    @NonNull
    String COLUMN_FIRST_NAME = "first_name";

    @NonNull
    String COLUMN_LAST_NAME = "last_name";

    @NonNull
    String COLUMN_MIDDLE_NAME = "middle_name";

    @NonNull
    String COLUMN_DATE = "date";

}
