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
}
