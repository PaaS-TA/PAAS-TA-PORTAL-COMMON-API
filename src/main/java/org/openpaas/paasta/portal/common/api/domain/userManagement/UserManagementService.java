package org.openpaas.paasta.portal.common.api.domain.userManagement;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.user.UserService;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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

    @Autowired
    CommonService commonService;

    /**
     * 사용자 정보 목록을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @HystrixCommand(fallbackMethod = "getUserInfoList")
    public Map<String, Object> getUserInfoList(UserDetail detail) {

        JinqStream<UserDetail> streams = jinqSource.streamAllPortal(UserDetail.class);
        if (null != detail.getSearchKeyword() && !"".equals(detail.getSearchKeyword())) {
            String keyword = detail.getSearchKeyword();
            streams = streams.where(d -> d.getUserId().contains(keyword) || d.getUserName().contains(keyword));
        }
        streams = streams.sortedDescendingBy(c -> c.getUserId());
        List<UserDetail> userDetailList = streams.toList();




        return new HashMap<String, Object>() {{
            put("list", setUserGuid(userDetailList));
        }};

    }
    @HystrixCommand(fallbackMethod = "getUserInfo")
    public Map<String, Object> getUserInfo(String userid) {
        JinqStream<UserDetail> streams = jinqSource.streamAllPortal(UserDetail.class);
        if (null != userid && !"".equals(userid)) {
            streams = streams.where(d -> d.getUserId().equals(userid));
        }
        List<UserDetail> userDetailList = streams.toList();

        return new HashMap<String, Object>() {{
            put("list", setUserGuid(userDetailList));
        }};
    }
    //@HystrixCommand(fallbackMethod = "setUserGuid")
    private List<UserDetail> setUserGuid(List<UserDetail> details){
        List<Users> users = usersRepository.findAll();
        for (UserDetail userDetail : details) {
            for (Users user:users) {
                if(userDetail.getUserId().equals(user.getUserName())){
                    userDetail.setUserGuid(user.getId());
                }
            }

        }
        return details;
    }


    /**
     * 사용자 패스워드 초기화를 한다.
     *
     * @param userId userid
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
   // @HystrixCommand(fallbackMethod = "setResetPassword")
    public Map<String, Object> setResetPassword(String userId) throws Exception {
        //TODO 패스워드 초기화 메소드가 없습니다.
        //TODO userService.resetPassword(userId);
        throw new UnsupportedOperationException("패스워드 초기화 메소드는 더 이상 쓰지 않습니다. expirePassword로...");
        /*
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
        */
    }


    /**
     * 사용자에게 운영자 권한을 부여한다.
     */
    //@HystrixCommand(fallbackMethod = "updateOperatingAuthority")
    public Map<String, Object> updateOperatingAuthority(String userId) {
        UserDetail userDetail = userDetailRepository.findByUserId(userId);
        logger.info(userDetail.toString());
        userDetail.setAdminYn(!userDetail.getAdminYn().equals("Y") ? "Y" : "N");
        userDetailRepository.save(userDetail);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 사용자를 삭제한다.
     */
   // @HystrixCommand(fallbackMethod = "deleteUserAccount")
    public Map<String, Object> deleteUserAccount(String userId) {
        userDetailRepository.deleteByUserId(userId);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

}

