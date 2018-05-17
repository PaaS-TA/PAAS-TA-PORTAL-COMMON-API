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
    @PostMapping(value = {"/v2/email/expired"})
    public Map<String, Object> expiredEmail(@RequestBody Map body) {
        Map<String, Object> resultMap = emailService.expiredEmail(body.get("userid").toString());
        return resultMap;
    }
}
