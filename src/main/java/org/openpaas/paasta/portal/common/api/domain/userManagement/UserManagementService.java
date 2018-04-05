package org.openpaas.paasta.portal.common.api.domain.userManagement;

import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.domain.user.UserService;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-08.
 */
@Transactional
@Service
public class UserManagementService {
    private final Logger logger = getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    public UserManagementService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    JinqSource jinqSource;
    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    public Map<String, Object> getUserInfoList() {

        return new HashMap<String, Object>() {{
            put("list",userDetailRepository.findAll());
        }};
    }

    public Map<String, Object> getUserInfoList(String userid) {
        JinqStream<UserDetail> streams = jinqSource.streamAllPortal(UserDetail.class);
        if (null != userid && !"".equals(userid)) {
            streams = streams.where(d -> d.getUserId().contains(userid));
        }
        List<UserDetail> starterCategoryList = streams.toList();
        return new HashMap<String, Object>() {{
            put("list", starterCategoryList);
        }};
    }
    /**
     * 사용자 패스워드 초기화를 한다.
     *
     * @param userId userid
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public Map<String, Object> setResetPassword(String userId) throws Exception {
        userService.resetPassword(userId);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


    /**사용자에게 운영자 권한을 부여한다.*/
    public Map<String, Object> updateOperatingAuthority(String userId) {
        UserDetail userDetail = userDetailRepository.findByUserId(userId);
        userDetail.setAdminYn(!userDetail.getAdminYn().equals("Y") ? "Y" : "N");
        userDetailRepository.save(userDetail);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**사용자를 삭제한다.*/
    public Map<String, Object> deleteUserAccount(String userId)  {
        userDetailRepository.deleteByUserId(userId);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

}

