package org.openpaas.paasta.portal.common.api.domain.webIdeUser;

import org.openpaas.paasta.portal.common.api.entity.portal.WebIdeUser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-21.
 */
@RestController
@RequestMapping("/webIdeUser")
public class WebIdeUserController {

    private static final Logger logger = getLogger(WebIdeUserController.class);

    @Autowired
    private WebIdeUserService webIdeUserService;


    /**
     * WEB IDE 사용자 정보를 조회한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/getUser"}, method = RequestMethod.POST)
    public WebIdeUser getUser(@RequestBody WebIdeUser webIdeUser, HttpServletResponse response) {
        WebIdeUser rstWebIdeUser = new WebIdeUser();

        rstWebIdeUser = webIdeUserService.getUser(webIdeUser);
        if(rstWebIdeUser == null) {
            return webIdeUser;
        }
        return rstWebIdeUser;
    }

    /**
     * WEB IDE 사용자 리스트를 조회한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/getList"}, method = RequestMethod.POST)
    public HashMap<String, Object> getList(@RequestBody WebIdeUser webIdeUser, HttpServletResponse response) {
        return webIdeUserService.getList(webIdeUser);
    }

    /**
     * WEB IDE 사용자를 저장한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/insertUser"}, method = RequestMethod.POST)
    public Map<String, Object> insertUser(@RequestBody WebIdeUser webIdeUser, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();

        String resultStr = webIdeUserService.insertUser(webIdeUser);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }

    /**
     * WEB IDE 사용자를 수정한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/updateUser"}, method = RequestMethod.POST)
    public Map<String, Object> updateUser(@RequestBody WebIdeUser webIdeUser, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();

        String resultStr = webIdeUserService.updateUser(webIdeUser);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }

    /**
     * WEB IDE 사용자를 삭제한다.
     *
     * @param webIdeUser the webIdeUser
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/deleteUser"}, method = RequestMethod.POST)
    public Map<String, Object> deleteUser(@RequestBody WebIdeUser webIdeUser, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();

        String resultStr = webIdeUserService.deleteUser(webIdeUser);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }

}
