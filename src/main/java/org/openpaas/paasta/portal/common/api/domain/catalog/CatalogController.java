package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-06.
 */
@RestController
@RequestMapping(value = {"/catalog"})
public class CatalogController {
    private static final Logger logger = getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    /**
     * 앱 템플릿 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/starternameCatalogs")
    public Map<String, Object> getStarterNames(@ModelAttribute Catalog param) {
        return catalogService.getStarterNamesList(param);
    }


    /**
     * 앱 개발환경 카탈로그 목록을 조회한다.
     */
    @GetMapping("/buildpackCatalogs")
    public Map<String, Object> getBuildPackCatalogList(@ModelAttribute Catalog param) {
        return catalogService.getBuildPackCatalogList(param);
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/servicepackCatalogs")
    public Map<String, Object> getServicePackCatalogList(@ModelAttribute Catalog param) {
        return catalogService.getServicePackCatalogList(param);
    }


    /**
     * 앱 템플릿 카탈로그 개수를 조회한다.
     */
    @GetMapping("/starterCatalogs/count")
    public Map<String, Object> getStarterCatalogCount() throws Exception {

        int buildPackCnt = catalogService.getStarterCatalogCount();

        logger.info("getStarterCatalogCount : " + buildPackCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getStarterCatalogCount", buildPackCnt);

        return resultMap;
    }


    /**
     * 앱 개발환경 목록 개수를 조회한다.
     */
    @GetMapping("/buildpackCatalogs/count")
    public Map<String, Object> getBuildPackCatalogCount() throws Exception {

        int buildPackCnt = catalogService.getBuildPackCatalogCount();

        logger.info("getBuildPackCatalogCount : " + buildPackCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getBuildPackCatalogCount", buildPackCnt);

        return resultMap;
    }


    /**
     * 서비스 목록 개수를 조회한다.
     */
    @GetMapping("/servicepackCatalogs/count")
    public Map<String, Object> getServicePackCatalogCount() throws Exception {

        int servicePackCnt = catalogService.getServicePackCatalogCount();

        logger.info("getServicePackCatalogCount : " + servicePackCnt);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getServicePackCatalogCount", servicePackCnt);

        return resultMap;
    }




}
