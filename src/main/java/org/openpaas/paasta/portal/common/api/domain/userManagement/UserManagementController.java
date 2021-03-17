package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SEJI on 2018-03-08.
 */
@RestController
public class UserManagementController {
    /**
     * 로그객체
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);
    private final String V2_URL = "/v2";
    @Autowired
    private UserManagementService userManagementService;

    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/usermgnts/{filter}/user")
    public Map<String, Object> getUserInfoList(@PathVariable String filter, @ModelAttribute UserDetail detail ,HttpServletRequest request) {
        LOGGER.info("detail ::: " + detail.toString());
        return userManagementService.getUserInfoList(filter,detail);
    }


    @GetMapping(V2_URL + "/usermgnts/{userid}")
    public Map<String, Object> getUserInfoList(@PathVariable String userid) {
        return userManagementService.getUserInfo(userid);
    }

    /**
     * 운영권한을 부여한다.
     *
     * @param userDetail UserManagement(모델클래스)
     * @param userid     userId
     * @return Map(자바클래스)
     */
    @PutMapping(V2_URL + "/usermgnts/{userid}/authority")
    public Map<String, Object> updateOperatingAuthority(@PathVariable String userid, @RequestBody UserDetail userDetail) {
        return userManagementService.updateOperatingAuthority(userid);
    }

    /**
     * 사용자 계정을 삭제한다.
     *
     * @param userDetail UserManagement(모델클래스)
     * @param userid     userId
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @DeleteMapping(V2_URL + "/usermgnts/{userid}")
    public Map<String, Object> deleteUserAccount(@PathVariable String userid, @RequestBody UserDetail userDetail) throws Exception {
        return userManagementService.deleteUserAccount(userDetail.getUserId());
    }


    /**
     * 사용자 포탈 접속 가능 유무 수정
     *
     * @param userid     userId
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @PutMapping(V2_URL + "/usermgnts/{userid}/active")
    public Map<String, Object> updateUserActive(@PathVariable String userid, @RequestBody UserDetail userDetail) throws Exception {
        return userManagementService.updateUserActive(userid, userDetail);
    }

}
