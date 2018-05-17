package org.openpaas.paasta.portal.common.api.domain.login;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.openpaas.paasta.portal.common.api.domain.user.UserController;
import org.openpaas.paasta.portal.common.api.domain.user.UserService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Request email authentication map.
     *
     * @param userDetail the user detail
     * @param response   the response
     * @return the map
     * @throws IOException        the io exception
     * @throws MessagingException the messaging exception
     */
    @RequestMapping(value = {"/requestEmailAuthentication"}, method = RequestMethod.POST)
    public Map<String, Object> requestEmailAuthentication(@RequestBody UserDetail userDetail, HttpServletResponse response) throws IOException, MessagingException {
        HashMap body = new HashMap();
        Map<String, Object> resultMap = new HashMap();

        body.put("userId", userDetail.getUserId());

        LOGGER.info("userId : " + userDetail.getUserId() + " : request : " + response.toString());

        List<Map<String, Object>> listUser = userService.getUserDetailInfo(body);
        resultMap.put("resultUserDetail", listUser);

//        if (listUser.size() > 0) {
//            UserDetail userDetail1 = listUser.get(0);
//            if (!"0".equals(userDetail1.getStatus())) {
//                resultMap.put("bRtn", false);
//                resultMap.put("error", "계정이 이미 존재합니다.");
//                return resultMap;
//            }
//        }
//        boolean resultSendEmail = userService.createRequestUser(body);
//        resultMap.put("bRtn", resultSendEmail);

        return resultMap;
    }

}
