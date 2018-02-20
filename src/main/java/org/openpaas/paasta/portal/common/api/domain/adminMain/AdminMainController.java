package org.openpaas.paasta.portal.common.api.domain.adminMain;

import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsCc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by indra on 2018-02-12.
 */
@RestController
@RequestMapping("/adminMain")
public class AdminMainController {

    @Autowired
    private AdminMainService adminMainService;


    /**
     * 전체 조직 수, 영역 수, APPLICATION 수, 사용자 수 목록을 조회한다.
     *
     * @param reqParam OrganizationsCc
     * @return Map(자바클래스)
     */
    @RequestMapping(value = "/getTotalCountList", method = RequestMethod.POST)
    public Map<String, Object> getTotalCountList(@RequestBody OrganizationsCc reqParam) {
        return adminMainService.getTotalCountList();
    }

    /**
     * 전체 조직 통계 목록을 조회한다.
     *
     * @param reqParam OrganizationsCc
     * @return Map(자바클래스)
     */
    @RequestMapping(value = "/getTotalOrganizationList", method = RequestMethod.POST)
    public Map<String, Object> getTotalOrganizationList(@RequestBody Map<String, Object> reqParam) {
        return adminMainService.getTotalOrganizationList(reqParam);
    }

    /**
     * 전체 공간 통계 목록을 조회한다.
     *
     * @param reqParam AdminMain(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = "/getTotalSpaceList", method = RequestMethod.POST)
    public Map<String, Object> getTotalSpaceList(@RequestBody Map<String, Object> reqParam) {
        return adminMainService.getTotalSpaceList(reqParam);
    }
}
