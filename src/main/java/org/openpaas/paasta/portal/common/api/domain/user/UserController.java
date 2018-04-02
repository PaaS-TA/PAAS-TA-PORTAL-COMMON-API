package org.openpaas.paasta.portal.common.api.domain.user;



import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by SEJI on 2018-02-20.
 */
@RestController
@Transactional
@RequestMapping(value = {"/user"})
public class UserController  {

    /** 로그객체*/
    private  static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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

        UserDetail user= userService.getUser(userId);

        Map<String, Object> result = new HashMap<>();

        result.put("User", user);

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
    public Map<String, Object> confirmAuthUser(@RequestBody UserDetail userDetail, HttpServletResponse response) throws IOException {
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

    /**
     * 이메일 인증을 통한 CF 사용자 추가
     *
     * @param userDetail the user detail
     * @return map
     * @throws Exception the exception
     */
    @Transactional
    @RequestMapping(value = {"/authAddUser"}, method = RequestMethod.POST)
    public Map<String, Object> authAddUser(@RequestBody HashMap userDetail) throws Exception {
        UserDetail updateUser = new UserDetail();
        Map<String, Object> resultMap = new HashMap();
        HashMap<String, Object> paramMap = userDetail;
        String userId = (String)userDetail.getOrDefault("userId","");
        updateUser.setUserId(userId);
        updateUser.setUserName((String)userDetail.getOrDefault("username",""));
        updateUser.setAdminYn("Y");
//        updateUser.setPassword((String)userDetail.getOrDefault("password",""));
//        if(adminUserName.equals(updateUser.getUserId())){
//            updateUser.setAdminYn("Y");
//        } else {
//            updateUser.setAdminYn("N");
//        }
        updateUser.setStatus("1");
        int resultCreateUser = userService.updateUser(userId, updateUser); //일단 status를 1로 만들어준다.

//        todo CF 생략.
//        if(resultCreateUser > 0) {
//            CustomCloudFoundryClient adminCcfc = getCustomCloudFoundryClient(adminUserName, adminPassword);
//            boolean resultUaa = userService.create(userDetail);
//        }
        paramMap.put("searchUserId", paramMap.get("userId"));
        paramMap.put("refreshToken", "");
//        paramMap.put("authAccessTime", new Date());
        paramMap.put("authAccessCnt", 0);
//        userService.authAddUser(paramMap);
        resultMap.put("bRtn", true);
        return resultMap;
    }

    /**
     * 계정등록
     *
     * @param userDetail the user detail
     * @param response   the response
     * @return map
     * @throws IOException        the io exception
     */
    @RequestMapping(value = {"/authUser"},  method = RequestMethod.POST)
    public Map<String, Object> authUser(@RequestBody UserDetail userDetail, HttpServletResponse response) throws IOException {
        HashMap body = new HashMap();
        Map<String, Object> resultMap = new HashMap();

        body.put("userId", userDetail.getUserId());
//        body.put("password", userDetail.getPassword());
        body.put("refreshToken", userDetail.getRefreshToken());
//        body.put("password", userDetail.getPassword());
//        body.put("password", userDetail.getPassword());

        LOGGER.info("userId : " + userDetail.getUserId() + " : request : " + response.toString());
        List<Map<String, Object>> listUser = userService.getUserDetailInfo(body);

        resultMap.put("resultUser",listUser.size());
        return resultMap;
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
    @RequestMapping(value = {"/updateUser/{userId:.+}"}, method = RequestMethod.PUT, consumes="application/json")
    public Map updateUser(@PathVariable String userId, @RequestBody Map<String, Object> body, HttpServletResponse response) throws Exception{

        LOGGER.info("> into updateUser...");

        UserDetail user = null;
        Map<String, Object> result = new HashMap<>();

        user = userService.getUser(userId);

        if( user == null ) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User does not exist.");
        } else {

            if ( body.containsKey("userName") )         user.setUserName((String)body.get("userName"));
            if ( body.containsKey("status") )           user.setStatus((String) body.get("status"));
            if ( body.containsKey("addressDetail") )    user.setAddressDetail((String) body.get("addressDetail"));
            if ( body.containsKey("address") )          user.setAddress((String) body.get("address"));
            if ( body.containsKey("tellPhone") )        user.setTellPhone((String) body.get("tellPhone"));
            if ( body.containsKey("zipCode") )          user.setZipCode((String) body.get("zipCode"));
            if ( body.containsKey("adminYn") )          user.setAdminYn((String) body.get("adminYn"));
            if ( body.containsKey("imgPath") ) {
//                if (user.getImgPath() != null) glusterfsService.delete(user.getImgPath());
                user.setImgPath((String) body.get("imgPath"));
            }
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
    @RequestMapping(value = {"/insertUser"},method = RequestMethod.POST)
    public Map insertUser(@RequestBody UserDetail user, HttpServletResponse response) throws Exception {

        LOGGER.info("> into insertUser...");

        Map<String, Object> result = new HashMap<>();

        int createResult = 0;

        System.out.println("Constants.adminUserName : "+Constants.adminUserName);
        System.out.println("user.getUserId() : "+user.getUserId());

        if ( userService.getUser(user.getUserId()) != null && !Constants.adminUserName.equals(user.getUserId())) {
            System.out.println("if");
            response.sendError(HttpServletResponse.SC_CONFLICT, "User already exists.");
        } else {
            System.out.println("else");

            if(Constants.adminUserName.equals(user.getUserId())){
                user.setAdminYn("Y");
            }
            LOGGER.info("> constants.ADMIN_USER_NAME");

            createResult = userService.createUser(user);
            result.put("result", createResult);
        }
        return result;
    }

    /**
     * Delete user map.
     *
     * @param userId   the user id
     * @param user     the user
     * @param response the response
     * @return the map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/deleteUser/{userId:.+}"}, method = RequestMethod.DELETE)
    public Map deleteUser(@PathVariable String userId, @RequestBody UserDetail user, HttpServletResponse response) throws Exception {
        LOGGER.info("> into deleteUser");

        Map<String, Object> result = new HashMap<>();
        int deleteResult = -1;

            deleteResult = userService.deleteUser(userId);

            if (deleteResult < 1) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User does not exist.");
            }

        result.put("result", deleteResult);
        return result;
    }

    /**
     * Reset password map.
     *
     * @param userDetail the user detail
     * @param response   the response
     * @return map
     * @throws IOException        the io exception
     * @throws MessagingException the messaging exception
     */
//    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.GET)
//    public Map<String, Object> resetPassword(@RequestBody UserDetail userDetail, HttpServletResponse response) throws IOException, MessagingException {
//        HashMap body = new HashMap();
//        Map<String, Object> resultMap = new HashMap();
//
//        body.put("userId", userDetail.getUserId());
//
//        LOGGER.info("userId : " + userDetail.getUserId() + " : request : " + response.toString());
////        body.put("status", "1");
//        List<Map<String, Object>> listUser = userService.getUserDetailInfo(body);
//        if(listUser.size() >= 1) {
//            HashMap map = body;
//            map.put("searchUserId", userDetail.getUserId());
//            Boolean resultCreateUser = userService.resetPassword(map);
//            resultMap.put("resetPassword", resultCreateUser);
//
//        }
//        resultMap.put("resultUser",listUser.size());
//        return resultMap;
//    }
//

    /**
     * 모든 Uaa 유저의 이름과 Guid를 목록으로 가져온다.
     *
     * @return map all user name
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getUserInfo"}, method = RequestMethod.GET)
    public Map<String, Object> getAllUserName() throws Exception {
        List<Map<String, Object>> userInfo = userService.getUserInfo();
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("userInfo", userInfo);
        return resultMap;
    }

}
