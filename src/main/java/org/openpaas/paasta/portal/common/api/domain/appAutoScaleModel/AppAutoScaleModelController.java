package org.openpaas.paasta.portal.common.api.domain.appAutoScaleModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by indra on 2018-02-20.
 */
@RestController
@RequestMapping("/app")
public class AppAutoScaleModelController {

    @Autowired
    private AppAutoScaleModelService appAutoScaleModelService;


    /**
     * getAppAutoScaleInfo(HashMap appAutoScale)
     * 앱의 자동인스턴스 증가, 감소 관련 정보를 가져온다.
     *
     * @param appAutoScale 자동인스턴스 증가, 감소 관련 모델 파라메터
     * @return {mode, list}
     */
    @ResponseBody
    @RequestMapping(value = {"/getAppAutoScaleInfo"}, method = RequestMethod.POST)
    public Map<String, Object> getAppAutoScaleInfo(@RequestBody HashMap appAutoScale, HttpServletResponse response) {
        return null;
    }
}
