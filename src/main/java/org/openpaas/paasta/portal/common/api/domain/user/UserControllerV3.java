package org.openpaas.paasta.portal.common.api.domain.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Transactional
public class UserControllerV3 {

    /**
     * 로그객체
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerV3.class);
    private final String V3_URL = "/v3";

    @Autowired
    private UserServiceV3 userServiceV3;


    /**
     * 사용자를 생성하기 위해 인증 메일을 발송한다.
     *
     * @return map all user name
     * @throws Exception the exception
     */
    @PostMapping(V3_URL + "/users/create/email")
    public Map<String, Object> createUserEmail(@RequestBody Map body) throws Exception {
        Map result = userServiceV3.createRequestUser(body);
        return result;
    }

    /**
     * 사용자를 생성하기 위해 인증 메일을 발송한다.
     *
     * @return map all user name
     * @throws Exception the exception
     */

    @PostMapping(V3_URL + "/users/password/email")
    public Map<String, Object> resetRequestUser(@RequestBody Map body) throws Exception {
        Map result = userServiceV3.resetRequestUser(body);
        return result;
    }

}
