package org.openpaas.paasta.portal.common.api.domain.user;

import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SEJI on 2018-02-20.
 */
@RestController
@Transactional
@RequestMapping(value = {"/user"})
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(org.openpaas.paasta.portal.api.controller.UserController.class);

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


}
