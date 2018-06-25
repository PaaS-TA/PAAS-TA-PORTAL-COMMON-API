package org.openpaas.paasta.portal.common.api.domain.configInfo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.entity.portal.ConfigInfo;
import org.openpaas.paasta.portal.common.api.repository.portal.ConfigInfoRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-22.
 */
@Service
public class ConfigInfoService {

    private static final Logger logger = getLogger(ConfigInfoService.class);

    @Autowired
    ConfigInfoRepository configInfoRepository;


    /**
     * 설정 정보 값을 조회한다.
     *
     * @param name
     * @return value value
     * @throws Exception the exception
     */
    @HystrixCommand(commandKey = "getValue")
    public HashMap<String, Object> getValue(String name) {
        HashMap<String, Object> resultMap = new HashMap<>();
        if (name == null) {
            resultMap.put("list", configInfoRepository.findAll());
        } else {
            resultMap.put("list", configInfoRepository.findAllByName(name));
        }
        return resultMap;
    }

    /**
     * 설정 정보 값을 수정한다.
     *
     * @param configInfo the config info
     * @return map
     * @throws Exception the exception
     */
    @HystrixCommand(commandKey = "updateValue")
    public String updateValue(String name, ConfigInfo configInfo) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;
        if(configInfoRepository.findAllByName(name) != null)
        {
            configInfo.setName(name);
            configInfo.setUpdatedAt(new Date());
            configInfoRepository.save(configInfo);
        }else {
            resultStr = Constants.RESULT_STATUS_FAIL;
        }
        return resultStr;
    }
}
