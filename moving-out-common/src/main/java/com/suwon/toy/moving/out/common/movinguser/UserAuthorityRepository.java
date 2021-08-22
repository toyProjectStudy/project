/**
 * ===============================================================
 * File name : UserAuthorityRepository.java
 * Created by injeahwang on 2021-08-22
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.movinguser;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserAuthorityRepository extends JpaRepository<MovingUser, String> {

    @EntityGraph(attributePaths = "authoritySet")
    Optional<MovingUser> findOneWithAuthoritiesByUserId(String userId);
}
