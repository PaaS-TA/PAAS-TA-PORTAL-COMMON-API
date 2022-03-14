package org.openpaas.paasta.portal.common.api.domain.user;


import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by SEJI on 2018-02-20.
 */
@RestController
@Transactional
public class UserController {

    /**
     * 로그객체
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final String V2_URL = "/v2";
    @Autowired
    private UserService userService;


    /**
     * Gets user.
     *
     * @param userId the user id
     * @return Map user
     */
    @GetMapping(V2_URL + "/user/{userId:.+}")
    public Map getUser(@PathVariable String userId) {
        LOGGER.info("> into getUser...");
        UserDetail user = userService.getUser(userId);
        if(user != null){
            if(user.getUserName() == null)
            {
                user = null;
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("User", user);
        return result;
    }


    /**
     * Update user map.
     *
     * @param userId   the user id
     * @param body     the body
     * @param response the response
     * @return Map { "result": updateCount}
     * @throws Exception the exception
     */
    @PutMapping(V2_URL + "/user/{userId:.+}")
    public Map updateUser(@PathVariable String userId, @RequestBody Map<String, Object> body, HttpServletResponse response) throws Exception {

        LOGGER.info("> into updateUser...");

        UserDetail user = null;
        Map<String, Object> result = new HashMap<>();

        user = userService.getUser(userId);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User does not exist.");
        } else {

            if (body.containsKey("userName")) user.setUserName((String) body.get("userName"));
            if (body.containsKey("status")) user.setStatus((String) body.get("status"));
            if (body.containsKey("addressDetail")) user.setAddressDetail((String) body.get("addressDetail"));
            if (body.containsKey("address")) user.setAddress((String) body.get("address"));
            if (body.containsKey("tellPhone")) user.setTellPhone((String) body.get("tellPhone"));
            if (body.containsKey("zipCode")) user.setZipCode((String) body.get("zipCode"));
            if (body.containsKey("adminYn")) user.setAdminYn((String) body.get("adminYn"));
            if (body.containsKey("active")) user.setActive((String)body.get("active"));
            if (body.containsKey("refreshToken")) user.setRefreshToken((String) body.get("refreshToken"));
//            if (body.containsKey("authAccessTime")) (user.setAuthAccessTime(new Date(Long.parseLong(body.get("refreshToken").toString()));
            if (body.containsKey("authAccessCnt")) user.setAuthAccessCnt((int) body.get("authAccessCnt"));
            if (body.containsKey("imgPath")) {
//                if (user.getImgPath() != null) glusterfsService.delete(user.getImgPath());
                user.setImgPath((String) body.get("imgPath"));
            }
            LOGGER.info(user.toString());
            int cnt = userService.updateUser(userId, user);
            result.put("result", cnt);
        }
        return result;
    }

    /**
     * Insert user map.
     *
     * @param user     the user
     * @param response the response
     * @return Map map
     * @throws Exception the exception
     */
    @PostMapping(V2_URL + "/user")
    public Map insertUser(@RequestBody UserDetail user, HttpServletResponse response) throws Exception {

        LOGGER.info("> into insertUser...");
        LOGGER.info(user.toString());
        Map<String, Object> result = new HashMap<>();

        int createResult = 0;
        if (userService.getUser(user.getUserId()) != null && !Constants.adminUserName.equals(user.getUserId())) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "User already exists.");
        } else {
            if (Constants.adminUserName.equals(user.getUserId())) {
                user.setAdminYn("Y");
            }
            LOGGER.info("> constants.ADMIN_USER_NAME");
            createResult = userService.createUser(user);
            result.put("result", createResult);
        }
        return result;
    }

    /**
     * Login Insert user
     *
     * @param username     the username
     * @param response the response
     * @return boolean boolean
     * @throws Exception the exception
     */
    @GetMapping(V2_URL + "/user/{username:.+}/uaa")
    public Users isExistUser(@PathVariable String username, HttpServletResponse response) throws Exception {
        return userService.getUaaUser(username);
    }






    /**
     * Delete user map.
     *
     * @param userId   the user id
     * @param response the response
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping(V2_URL + "/user/{userId:.+}")
    public Map deleteUser(@PathVariable String userId,HttpServletResponse response) throws Exception {
        LOGGER.info("> into deleteUser :: " + userId);

        Map<String, Object> result = userService.deleteUser(userId);

        return result;
    }

    /**
     * Delete user map.
     *
     * @param response the response
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping(V2_URL + "/user/{guid}/all")
    public Map deleteUserInfra(@PathVariable String guid, @RequestHeader("Authorization") String token, HttpServletResponse response) throws Exception {
        LOGGER.info("> into deleteUser");
        Map<String, Object> result = userService.deleteUserInfra(guid, token);
        return result;
    }


    /**
     * 모든 Uaa 유저의 이름과 Guid를 목록으로 가져온다.
     *
     * @return map all user name
     * @throws Exception the exception
     */
    @GetMapping(V2_URL + "/users")
    public Map<String, Object> getAllUserName() throws Exception {
        List<Map<String, Object>> userInfo = userService.getUserInfo();
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("userInfo", userInfo);
        return resultMap;
    }


    /**
     * 사용자 정보를 토큰과 비교하여 가져온다.
     *
     * @return map all user name
     * @throws Exception the exception
     */

    @GetMapping(V2_URL + "/users/{userid}/search/refreshtoken")
    public UserDetail c(@PathVariable String userid, @ModelAttribute UserDetail body) throws Exception {
        body.setUserId(userid);
        UserDetail result = userService.getRefreshTokenUser(body);
        return result;
    }


    /**
     * 사용자를 생성하기 위해 인증 메일을 발송한다.
     *
     * @return map all user name
     * @throws Exception the exception
     */
    @PostMapping(V2_URL + "/users/create/email")
    public Map<String, Object> createUserEmail(@RequestBody Map body, @RequestHeader("useLang") String lang) throws Exception {
        Map result = userService.createRequestUser(body, lang);
        return result;
    }

    /**
     * 비밀번호를 초기화하기 위해 인증 메일을 발송한다.
     *
     * @return map all user name
     * @throws Exception the exception
     */

    @PostMapping(V2_URL + "/users/password/email")
    public Map<String, Object> resetRequestUser(@RequestBody Map body, @RequestHeader("useLang") String lang) throws Exception {
        Map result = userService.resetRequestUser(body, lang);
        return result;
    }

}
