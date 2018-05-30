package org.openpaas.paasta.portal.common.api.domain.email;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-22.
 */
@RestController

public class EmailController {

    private static final Logger logger = getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;


    /**
     * 사용자 패스워드를 만료하여, 초기화 하도록 메일을 발송한다.
     *
     * @return the menu list
     */
    @PostMapping(value = {"/v2/email/reset"})
    public Map<String, Object> expiredEmail(@RequestBody Map body) {
        String refreshToken = "";
        if (body.get("refreshToken") != null) {
            refreshToken = body.get("refreshToken").toString();
        }
        Map<String, Object> resultMap = emailService.resetEmail(body.get("userid").toString(), refreshToken);
        return resultMap;
    }




    /**
     * 사용자 계정을 생성하기 위하여, 이메일 발송
     *
     * @return the menu list
     */
    @PostMapping(value = {"/v2/email/create"})
    public Map<String, Object> createEmail(@RequestBody Map body) {
        String refreshToken = "";
        if (body.get("refreshToken") != null) {
            refreshToken = body.get("refreshToken").toString();
        }
        Map<String, Object> resultMap = emailService.createEmail(body.get("userid").toString(), refreshToken);
        return resultMap;
    }

    @PostMapping( "/v2" + "/email/inviteOrg" )
    public Map<String, Object> inviteOrgEmail(@RequestBody Map<String, Object> body) {
        String refreshToken = body.get( "refreshToken" ).toString();
        if (refreshToken == null)
            refreshToken = "";

        final String userId = body.get( "userId" ).toString();
        final String orgName = body.get( "orgName" ).toString();

        return emailService.inviteOrgEmail( userId, orgName, refreshToken );
    }
}
