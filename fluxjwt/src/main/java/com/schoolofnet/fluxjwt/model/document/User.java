package com.schoolofnet.fluxjwt.model.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */
@Getter
@Setter
@Document
public class User {

    @Id
    private String id;
    private String username;
    private String password;

    public User() {

    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
