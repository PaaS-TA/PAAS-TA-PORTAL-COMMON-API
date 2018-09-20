package org.openpaas.paasta.portal.common.api.domain.user;



import org.apache.commons.lang.RandomStringUtils;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.config.dataSource.UaaConfig;
import org.openpaas.paasta.portal.common.api.domain.common.CommonService;
import org.openpaas.paasta.portal.common.api.domain.email.EmailService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.openpaas.paasta.portal.common.api.entity.uaa.Users;
import org.openpaas.paasta.portal.common.api.repository.portal.UserDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.uaa.UsersRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-02-20.
 */
@Service
public class UserService {

    private final Logger logger = getLogger(this.getClass());

    @Autowired
    PortalConfig portalConfig;

    @Autowired
    UaaConfig uaaConfig;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    private EmailService emailService;

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
    //@HystrixCommand(commandKey = "getUser")
    public UserDetail getUser(String userId) {
        return  userDetailRepository.findByUserId(userId);
    }

    public Users getUaaUser(String username) {
        return usersRepository.findByUserName(username);
    }

    /**
     * 사용자 상세화면에서
     * 사용자 정보 수정
     *
     * @param userId     the user id
     * @param userDetail the user detail
     * @return Int updateCount
     */
    //@HystrixCommand(commandKey = "updateUser")
    public int updateUser(String userId, UserDetail userDetail) {

        int resultCnt = userDetailRepository.countByUserId(userId);
        if (resultCnt > 0) {
            userDetailRepository.save(userDetail);
        }
        return resultCnt;
    }


    /**
     * 사용자 자동생성
     *
     * @param userDetail the user detail
     * @return int int
     */
    //@HystrixCommand(commandKey = "createUser")
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
   // //@HystrixCommand(commandKey = "createRequestUser")
    public Map createRequestUser(Map body) {
        Map map = new HashMap();
        try {
            String randomId = RandomStringUtils.randomAlphanumeric(17).toUpperCase() + RandomStringUtils.randomAlphanumeric(2).toUpperCase();
            UserDetail userDetail = new UserDetail();
            userDetail.setUserId(body.get("userid").toString());
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
            map = emailService.createEmail(userDetail.getUserId(), randomId);
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
    //@HystrixCommand(commandKey = "resetRequestUser")
    public Map resetRequestUser(Map body) {
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
            map = emailService.resetEmail(userDetail.getUserId(), randomId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }


    /**
     * DB에서 사용자 삭제
     *
     * @param userId the user id
     * @return 삭제 정보
     */
    //@HystrixCommand(commandKey = "deleteUser")
    public Map deleteUser(String userId) {
        Map map = new HashMap();
        try {
            int deleteResult = userDetailRepository.deleteByUserId(userId);
            map.put("result", true);
            map.put("msg", "You have successfully completed the task.");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * DB에서 사용자 삭제 후 CF인프라에서도 사용자 삭제
     *
     * @return 삭제 정보
     */
    //@HystrixCommand(commandKey = "deleteUserInfra")
    public Map deleteUserInfra(String guid, String token) {

        logger.info("userId ::::: " + guid);
        Map map = new HashMap();
        try {
            Users user = usersRepository.findById(guid);

            Map result = commonService.procCfApiRestTemplate("/users/" + guid, HttpMethod.DELETE, null, token);
            if (result.get("result").toString().equals("true")) {
                userDetailRepository.deleteByUserId(user.getUserName());
                map.put("result", true);
                map.put("msg", "You have successfully completed the task.");
            } else {
                map = result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;


    }


    /**
     * 전체 UAA 유저의 userName과 userGuid를 가져온다.
     *
     * @return userInfo list
     */
    //@HystrixCommand(commandKey = "getRefreshTokenUser")
    public UserDetail getRefreshTokenUser(UserDetail userDetail) {
        logger.info("getRefreshTokenUser :: " + userDetail.toString());
        UserDetail data = userDetailRepository.findByUserIdAndRefreshToken(userDetail.getUserId(), userDetail.getRefreshToken());
        return data;
    }

    /**
     * 전체 UAA 유저의 userName과 userGuid를 가져온다.
     *
     * @return All Users List( GUID, userName )
     */
    //@HystrixCommand(commandKey = "getUserInfo")
    public List<Map<String, Object>> getUserInfo() {

        EntityManager portalEm = uaaConfig.uaaEntityManager().getNativeEntityManagerFactory().createEntityManager();

        CriteriaBuilder cb = portalEm.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Users> from = cq.from(Users.class);

        //SQL:Select
        cq.multiselect(from.get("userName").alias("userName"), from.get("id").alias("id"));

        TypedQuery<Tuple> tq = portalEm.createQuery(cq);
        List<Tuple> resultList = tq.getResultList();


        List<Map<String, Object>> userInfo = resultList.stream().map(x -> new HashMap<String, Object>() {{
            put("userName", x.get("userName"));
            put("userGuid", x.get("id"));
        }}).collect(Collectors.toList());

        return userInfo;
    }
}



