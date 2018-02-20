package org.openpaas.paasta.portal.common.api.domain.appAutoScaleModal;

import org.openpaas.paasta.portal.common.api.entity.portal.AutoScalingConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-20.
 */
@RestController
@RequestMapping("/app")
public class AppAutoScaleModalController {

    private static final Logger logger = getLogger(AppAutoScaleModalController.class);

    @Autowired
    private AppAutoScaleModalService appAutoScaleModalService;


    /**
     * Gets app auto scale info.
     *
     * @param appAutoScale the app auto scale
     * @param response     the response
     * @return the app auto scale info
     */
    @ResponseBody
    @RequestMapping(value = {"/getAppAutoScaleInfo"}, method = RequestMethod.POST)
    public Map<String, Object> getAppAutoScaleInfo(@RequestBody HashMap appAutoScale, HttpServletResponse response) {
        return appAutoScaleModalService.getAppAutoScaleInfo(appAutoScale);
    }

    /**
     * Gets app auto scale list.
     *
     * @param appAutoScale the app auto scale
     * @param response     the response
     * @return the app auto scale list
     */
    @ResponseBody
    @RequestMapping(value = {"/getAppAutoScaleList"}, method = RequestMethod.POST)
    public Map<String, Object> getAppAutoScaleList(@RequestBody HashMap appAutoScale, HttpServletResponse response) {
        return appAutoScaleModalService.getAppAutoScaleList(appAutoScale);
    }

    /**
     * Insert app auto scale map.
     *
     * @param autoScalingConfig the app auto scale
     * @param response     the response
     * @return the map
     */
    @RequestMapping(value = {"/insertAppAutoScale"}, method = RequestMethod.POST)
    public Map<String, Object> insertAppAutoScale(@RequestBody AutoScalingConfig autoScalingConfig, HttpServletResponse response) {
        String resultStr = appAutoScaleModalService.insertAppAutoScale(autoScalingConfig);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }

    /**
     * Update app auto scale map.
     *
     * @param autoScalingConfig the app auto scale
     * @param response     the response
     * @return the map
     */
    @RequestMapping(value = {"/updateAppAutoScale"}, method = RequestMethod.POST)
    public Map<String, Object> updateAppAutoScale(@RequestBody AutoScalingConfig autoScalingConfig, HttpServletResponse response) {
        String resultStr = appAutoScaleModalService.updateAppAutoScale(autoScalingConfig);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }

    /**
     * Delete app auto scale map.
     *
     * @param autoScalingConfig the app auto scale
     * @param response     the response
     * @return the map
     */
    @RequestMapping(value = {"/deleteAppAutoScale"}, method = RequestMethod.POST)
    public Map<String, Object> deleteAppAutoScale(@RequestBody AutoScalingConfig autoScalingConfig, HttpServletResponse response) {
        String resultStr = appAutoScaleModalService.deleteAppAutoScale(autoScalingConfig);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("RESULT", resultStr);
        return resultMap;
    }
}
