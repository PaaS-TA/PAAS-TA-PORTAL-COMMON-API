package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    @RequestMapping(value = {"/getUserInfoList"}, method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> getUserInfoList(@RequestBody HashMap param) {
        return userManagementService.getUserInfoList(param);
    }

    /**
     * 운영권한을 부여한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/updateOperatingAuthority"}, method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> updateOperatingAuthority(@RequestBody HashMap param) {
        Map<String, Object> resultMap = new HashMap<>();
        String resultStr = userManagementService.updateOperatingAuthority(param);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }

    /**
     * 사용자 계정을 삭제한다.
     *
     * @param param UserManagement(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @RequestMapping(value = {"/deleteUserAccount"}, method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> deleteUserAccount(@RequestBody HashMap param) throws Exception {
        return userManagementService.deleteUserAccount(param);
    }


}
