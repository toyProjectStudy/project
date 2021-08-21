/**
 * ===============================================================
 * File name : MovingUserRepository.java
 * Created by injeahwang on 2021-08-21
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.movinguser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovingUserRepository extends JpaRepository<MovingUser, String> {
}
