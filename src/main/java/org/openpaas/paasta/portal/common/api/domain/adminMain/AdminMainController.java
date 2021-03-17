package org.openpaas.paasta.portal.common.api.domain.adminMain;

import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsCc;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by indra on 2018-02-12.
 */
@RestController
public class AdminMainController {

    private static final Logger logger = getLogger(AdminMainController.class);
    private final String V2_URL = "/v2";

    @Autowired
    private AdminMainService adminMainService;


    /**
     * 전체 조직 수, 영역 수, APPLICATION 수, 사용자 수 목록을 조회한다.
     *
     //     * @param reqParam OrganizationsCc
     * @return Map(자바클래스)
     */
    @GetMapping(value = {V2_URL + "/statistics"})
    public Map<String, Object> getTotalCountList() {
        return adminMainService.getTotalCountList();
    }

    /**
     * 전체 조직 통계 목록을 조회한다.
     *
     //     * @param reqParam OrganizationsCc
     * @return Map(자바클래스)
     */
    @GetMapping(value = {V2_URL + "/statistics/organizations"})
    public Map<String, Object> getTotalOrganizationList() {
        return adminMainService.getTotalOrganizationList();
    }

    /**
     * 해당 조직에 대한 공간들의 통계 목록들을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @GetMapping(value = {V2_URL + "/statistics/organizations/{organizationId}/spaces"})
    public Map<String, Object> getTotalSpaceList(@PathVariable String organizationId) {
        logger.info("getTotalSpaceList");
        return adminMainService.getTotalSpaceList(organizationId);
    }
}
