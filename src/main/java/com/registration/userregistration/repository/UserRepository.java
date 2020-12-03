package com.registration.userregistration.repository;

import com.registration.userregistration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tatevik Mirzoyan
 * Created on 03-Dec-20
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
