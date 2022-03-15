package org.openpaas.paasta.portal.common.api.domain.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, Object> createUserEmail(@RequestBody Map body, @RequestHeader("useLang") String lang) throws Exception {
        Map result = userServiceV3.createRequestUser(body, lang);
        return result;
    }

    /**
     * 비밀번호를 초기화하기 위해 인증 메일을 발송한다.
     *
     * @return map all user name
     * @throws Exception the exception
     */

    @PostMapping(V3_URL + "/users/password/email")
    public Map<String, Object> resetRequestUser(@RequestBody Map body, @RequestHeader("useLang") String lang) throws Exception {
        Map result = userServiceV3.resetRequestUser(body, lang);
        return result;
    }

}
