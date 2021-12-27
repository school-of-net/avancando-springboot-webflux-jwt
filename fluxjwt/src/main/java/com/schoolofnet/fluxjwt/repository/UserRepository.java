package com.schoolofnet.fluxjwt.repository;

import com.schoolofnet.fluxjwt.model.document.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * created by:
 *
 * @author rafael for DevDusCorre on 27/12/2021
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
