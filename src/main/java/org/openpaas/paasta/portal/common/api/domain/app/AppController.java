package org.openpaas.paasta.portal.common.api.domain.app;

import org.openpaas.paasta.portal.common.api.entity.model.app.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by indra on 2018-02-06.
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    /**
     * 앱 이미지를 조회한다.
     *
     * @param reqApp the app
     * @return app image url
     */
    @RequestMapping(value = "/getAppImageUrl", method = RequestMethod.POST)
    public Map<String, Object> getAppImageUrl(@RequestBody App reqApp) {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("appImageUrl", appService.getAppImageUrl(reqApp.getGuid()));

        return resultMap;
    }
}
