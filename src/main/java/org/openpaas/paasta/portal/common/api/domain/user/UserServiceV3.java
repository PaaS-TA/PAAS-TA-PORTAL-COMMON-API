package org.openpaas.paasta.portal.common.api.domain.user;


import org.apache.commons.lang.RandomStringUtils;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.email.EmailServiceV3;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


@Service
public class UserServiceV3 {

    private final Logger logger = getLogger(this.getClass());

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    UaaConfig uaaConfig;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    private EmailServiceV3 emailServiceV3;

    @Autowired
    JinqSource jinqSource;

    @Autowired
    CommonService commonService;

    @Autowired
    UsersRepository usersRepository;

    /**
     * 사용자 상세 정보
     *
     * @param userId the user id
     * @return UserDetail user
     */
    public UserDetail getUser(String userId) {
        return  userDetailRepository.findByUserId(userId);
    }

    /**
     * 사용자 자동생성
     *
     * @param userDetail the user detail
     * @return int int
     */
    public int createUser(UserDetail userDetail) {
        int createResult = 1;
        if (createResult > 0) {
            userDetailRepository.save(userDetail);
        }
        return createResult;
    }


    /**
     * 사용자 정보 인증
     * potalDB에 사용자 정보를 등록한후 이메일을 보낸다.
     *
     * @param body the body
     * @return boolean
     * @throws IOException the io exception
     */
    public Map createRequestUser(Map body, String useLang) {
        Map map = new HashMap();
        try {
            String randomId = RandomStringUtils.randomAlphanumeric(17).toUpperCase() + RandomStringUtils.randomAlphanumeric(2).toUpperCase();
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(body.get("userid").toString());
            userDetail.setUserName(body.get("username").toString());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            userDetail.setAuthAccessTime(cal.getTime());
            userDetail.setAuthAccessCnt(0);
            userDetail.setAdminYn("N");
            userDetail.setStatus("0");
            userDetail.setRefreshToken(randomId);
            userDetail.setActive("N");
            /*
             * 여기서 에러나면 Exception으로 빠져버림
             */
            createUser(userDetail);
            map = emailServiceV3.createEmail(userDetail.getUserId(), randomId, body.get("seq").toString(), useLang);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }

    /**
     * 사용자 정보 인증
     * potalDB에 사용자 정보를 등록한후 이메일을 보낸다.
     *
     * @return boolean
     * @throws IOException the io exception
     */
    public Map  resetRequestUser(Map body, String useLang) {
        Map map = new HashMap();

        try {
            String randomId = RandomStringUtils.randomAlphanumeric(17).toUpperCase() + RandomStringUtils.randomAlphanumeric(2).toUpperCase();
            UserDetail userDetail = getUser(body.get("userid").toString());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            userDetail.setAuthAccessTime(cal.getTime());
            userDetail.setAuthAccessCnt(0);
            userDetail.setStatus("1");
            userDetail.setRefreshToken(randomId);
            /*
             * 여기서 에러나면 Exception으로 빠져버림
             */
            createUser(userDetail);
            map = emailServiceV3.resetEmail(userDetail.getUserId(), randomId, body.get("seq").toString(), useLang);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }

}



