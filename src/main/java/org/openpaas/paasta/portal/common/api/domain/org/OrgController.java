package org.openpaas.paasta.portal.common.api.domain.org;

import org.apache.commons.collections.map.HashedMap;
import org.openpaas.paasta.portal.common.api.domain.user.UserService;
import org.openpaas.paasta.portal.common.api.entity.portal.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SEJI on 2018-03-07.
 */
@RestController
@Transactional
public class OrgController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    private OrgService orgService;

    @Autowired
    private UserService userService;

    /**
     * 관리자권한으로 조직 목록을 조회한다.
     *
     * @return the orgs for admin
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/org/getOrgsForAdmin"}, method = RequestMethod.POST)
    public Map<String, Object> getOrgsForAdmin() throws Exception {
        LOGGER.info("getOrgsForAdmin ::");
        List<Object> orgList = orgService.getOrgsForAdmin();

        return new HashMap<String, Object>(){{put("orgList", orgList);}};
    }

    /**
     * 조직에 초대된 사용자의 가입여부를 조회한다.
     *
     * @param request the request
     * @return map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/invitations/userInfo"})
    public Map<String, Object> inviteUserInfo(@RequestBody HashMap request) throws Exception {

        String code = (null == request.get("code")) ? "" : request.get("code").toString();
        LOGGER.info("code : "+ code+" : request : " +request.toString());
        Map<String, Object> result = new HashedMap();

        List<Map> list = orgService.selectInviteInfo(code);

        if(list.size() > 0) {
            String inviteId = (String) list.get(0).getOrDefault("inviteUserId", "");
            UserDetail userDetail = userService.getUser(inviteId);
            result.put("userId", inviteId);
            result.put("userDetail", userDetail);
        }
        result.put("listSize", list.size());

        return  result;
    }

}
