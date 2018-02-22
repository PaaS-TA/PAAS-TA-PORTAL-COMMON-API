package org.openpaas.paasta.portal.common.api.domain.user;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by SEJI on 2018-02-20.
 */
@RestController
@Transactional
@RequestMapping(value = {"/user"})
public class UserController {

    private final Logger LOGGER = getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 사용자 총 명수
     *
     * @param
     * @return App user count
     */
    @RequestMapping(value = {"/getUserCount"}, method = RequestMethod.GET)
    public Map<String, Object> getUserCount() {

        int userCnt = userService.getUserCount();

        LOGGER.info("count : " + userCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", userCnt);
//        UserDetail user = new UserDetail();
//        user.setCount(userCnt);

        return resultMap;
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return Map user
     */
    @RequestMapping(value = {"/getUser/{userId:.+}"}, method = RequestMethod.GET)
    public Map getUser(@PathVariable String userId) {
        LOGGER.info("> into getUser...");
        UserDetail user= userService.getUser(userId);;
        Map<String, Object> result = new HashMap<>();
        result.put("User", user);
        return result;
    }

    /**
     * Gets user and orgs.
     *
     * @param userId   the user id
     * @param request  the request
     * @param response the response
     * @return Map user and orgs
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getUserAndOrgs/{userId:.+}"}, method = RequestMethod.GET)
    public Map getUserAndOrgs(@PathVariable String userId , HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOGGER.info(">getUserAndOrgs...");

        Map<String, Object> result = new HashMap<>();

        UserDetail user= userService.getUser(userId);;

        if( user == null ) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User does not exist.");
        } else {
//            CloudCredentials credentials = new CloudCredentials(new DefaultOAuth2AccessToken(request.getHeader(AUTHORIZATION_HEADER_KEY)),false);
            result.put("User", user);
        }

        return result;
    }


    /**
     * 이메일 인증 사용자 확인
     *
     * @param userDetail the user detail
     * @param response   the response
     * @return the map
     * @throws IOException        the io exception
     * @throws MessagingException the messaging exception
     */
    @RequestMapping(value = {"/confirmAuthUser"})
    public Map<String, Object> confirmAuthUser(@RequestBody UserDetail userDetail, HttpServletResponse response) throws IOException, MessagingException {
        HashMap body = new HashMap();
        Map<String, Object> resultMap = new HashMap();
        HashMap<String, Object> requestMap = new HashMap();

        body.put("userId", userDetail.getUserId());
        body.put("refreshToken", userDetail.getRefreshToken());
        body.put("status", userDetail.getStatus());

        List<Map<String, Object>> listUser = userService.getUserDetailInfo(body);
//        UserDetail userDetail1 = (UserDetail)listUser.get(0);
//        requestMap.put("userId",userDetail1.getUserId());
//        requestMap.put("authAccessCnt",userDetail1.getAuthAccessCnt());

//        userService.authAddAccessCnt(requestMap);
        resultMap.put("resultUser",listUser.size());
        resultMap.put("listResultUser",listUser);
        return resultMap;
    }


}
