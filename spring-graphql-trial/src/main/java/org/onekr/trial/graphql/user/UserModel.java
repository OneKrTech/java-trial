package org.onekr.trial.graphql.user;

import lombok.Data;

@Data
public class UserModel {

    private String id;

    private String name;

    private AuthModel auth;

}
