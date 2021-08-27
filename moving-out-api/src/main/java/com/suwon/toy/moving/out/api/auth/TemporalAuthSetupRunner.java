/**
 * ===============================================================
 * File name : TemporalAuthSetupRunner.java
 * Created by injeahwang on 2021-08-27
 * ===============================================================
 */
package com.suwon.toy.moving.out.api.auth;

import com.suwon.toy.moving.out.common.movinguser.Authority;
import com.suwon.toy.moving.out.common.movinguser.AuthorityRole;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**TODO : DB 자동 로컬 환경구성이 완료되면(초기 Data Initilization) 삭제하기
 */
@Component
public class TemporalAuthSetupRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        // 임시로, App이 로드되면서 기본 권한을 Insert 한다.
        em.persist(new Authority(AuthorityRole.USER.getValue()));
        em.persist(new Authority(AuthorityRole.ADMIN.getValue()));
    }
}
