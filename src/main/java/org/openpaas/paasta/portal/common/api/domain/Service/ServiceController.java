package org.openpaas.paasta.portal.common.api.domain.service;

import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
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
@RequestMapping("/service")
public class ServiceController {

    private static final Logger logger = getLogger(ServiceController.class);

    @Autowired
    private ServiceService serviceService;


    /**
     * 서비스 이미지를 조회한다.
     *
     * @param servicepackCategory the service
     * @return the menu list
     */
    @RequestMapping(value = {"/getServiceImageUrl"}, method = RequestMethod.POST)
    public Map<String, Object> getServiceImageUrl(@RequestBody ServicepackCategory servicepackCategory) {
        Map<String, Object> resultMap = new HashMap<>();

        String serviceImageUrl = serviceService.getServiceImageUrl(servicepackCategory);
        resultMap.put("serviceImageUrl", serviceImageUrl);
        return resultMap;
    }
}
