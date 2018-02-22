package org.openpaas.paasta.portal.common.api.domain.configInfo;

import org.openpaas.paasta.portal.common.api.entity.portal.ConfigInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-22.
 */
@RestController
@RequestMapping("/configInfo")
public class ConfigInfoController {

    private static final Logger logger = getLogger(ConfigInfoController.class);

    @Autowired
    private ConfigInfoService configInfoService;


    /**
     * 설정 정보 값을 조회한다.
     *
     * @param configInfo the config info
     * @return value value
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/getValue"}, method = RequestMethod.POST)
    public Map<String, Object> getValue(@RequestBody ConfigInfo configInfo) {
        return configInfoService.getValue(configInfo);
    }

    /**
     * 설정 정보 값을 수정한다.
     *
     * @param configInfo the config info
     * @return map map
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/updateValue"}, method = RequestMethod.POST)
    public Map<String, Object> updateValue(@RequestBody ConfigInfo configInfo) {
        Map<String, Object> resultMap = new HashMap<>();

        String resultStr = configInfoService.updateValue(configInfo);
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }
}
