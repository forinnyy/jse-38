package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserListProfilesResponse extends AbstractResponse {

    private List<String> profiles;

    public UserListProfilesResponse(List<String> profiles) {
        this.profiles = profiles;
    }

}
