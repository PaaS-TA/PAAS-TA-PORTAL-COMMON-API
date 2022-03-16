package org.openpaas.paasta.portal.common.api.domain.email;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@RestController

public class EmailControllerV3 {

    private static final Logger logger = getLogger(EmailControllerV3.class);

    @Autowired
    private EmailServiceV3 emailServiceV3;


    /**
     * 사용자 패스워드를 만료하여, 초기화 하도록 메일을 발송한다.
     *
     * @return the menu list
     */
    @PostMapping(value = {"/v3/email/reset"})
    public Map<String, Object> expiredEmail(HttpServletRequest request, @RequestBody Map body, @RequestHeader(value = "useLang") String lang) {
        String seq =  request.getParameter("seq");
        body.put("seq",seq);
        String refreshToken = "";
        if (body.get("refreshToken") != null) {
            refreshToken = body.get("refreshToken").toString();
        }
        Map<String, Object> resultMap = emailServiceV3.resetEmail(body.get("userid").toString(), refreshToken, body.get("seq").toString(), lang);
        return resultMap;
    }

    /**
     * 사용자 계정을 생성하기 위하여, 이메일 발송
     *
     * @return the menu list
     */
    @PostMapping(value = {"/v3/email/create"})
    public Map<String, Object> createEmail(HttpServletRequest request, @RequestBody Map body, @RequestHeader(value = "useLang") String lang) {
        String seq =  request.getParameter("seq");
        body.put("seq",seq);
        String refreshToken = "";
        if (body.get("refreshToken") != null) {
            refreshToken = body.get("refreshToken").toString();
        }
        Map<String, Object> resultMap = emailServiceV3.createEmail(body.get("userid").toString(), refreshToken, body.get("seq").toString(), lang);
        return resultMap;
    }

    @PostMapping( "/v3" + "/email/inviteOrg" )
    public boolean inviteOrgEmail(@RequestBody Map<String, Object> body, @RequestHeader("useLang") String lang) {
        return emailServiceV3.inviteOrgEmail(body, lang);
    }

    @PostMapping( "/v3" + "/email/inviteAccept" )
    public Map inviteAccept(@RequestBody Map<String, Object> body) {
        return emailServiceV3.inviteAccept(body);
    }

    @PostMapping( "/v3" + "/email/inviteAcceptUpdate" )
    public Map inviteAcceptUpdate(@RequestBody Map<String, Object> body) {
        return emailServiceV3.inviteAcceptUpdate(body);
    }


}
