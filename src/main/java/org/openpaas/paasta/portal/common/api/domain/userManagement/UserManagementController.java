package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SEJI on 2018-03-08.
 */
@RestController
@RequestMapping(value = {"/userManagement"})
public class UserManagementController {
    /** 로그객체*/
    private  static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    private UserManagementService userManagementService;

    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/UserInfoList"}, method = RequestMethod.GET)
    public Map<String, Object> getUserInfoList() {
        return userManagementService.getUserInfoList();
    }

    /**
     * 비밀번호를 초기화한다.
     *
     * @param userId userId
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @RequestMapping(value = {"/ResetPassword/{userId}"}, method = RequestMethod.PUT)
    public Map<String, Object> setResetPassword(@PathVariable String userId) throws Exception {
        return userManagementService.setResetPassword(userId);
    }




    /**
     * 운영권한을 부여한다.
     *
     * @param userId userId
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/OperatingAuthority/{userId}"}, method = RequestMethod.DELETE)
    public Map<String, Object> updateOperatingAuthority(@PathVariable String userId) {
        return userManagementService.updateOperatingAuthority(userId);
    }

    /**
     * 사용자 계정을 삭제한다.
     *
     * @param userId UserManagement(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @RequestMapping(value = {"/UserAccount/{userId}"}, method = RequestMethod.DELETE)
    public Map<String, Object> deleteUserAccount(@PathVariable String userId) throws Exception {
        return userManagementService.deleteUserAccount(userId);
    }


}
