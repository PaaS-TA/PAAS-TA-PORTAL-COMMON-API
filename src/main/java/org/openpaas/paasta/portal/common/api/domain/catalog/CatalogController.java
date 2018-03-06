package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @RequestMapping(value = {"/getServicePackCatalogList"}, method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> getServicePackCatalogList(@RequestBody Catalog param) {
        return catalogService.getServicePackCatalogList(param);
    }

    /**
     * 앱 개발환경 목록 개수를 조회한다.
     *
     * @param param Catalog(모델클래스)
         * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @RequestMapping(value = {"/getBuildPackCatalogCount"}, method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> getBuildPackCatalogCount(@RequestBody Catalog param) throws Exception {
        return catalogService.getBuildPackCatalogCount(param);
    }

    /**
     * 서비스 목록 개수를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    @RequestMapping(value = {"/getServicePackCatalogCount"}, method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> getServicePackCatalogCount(@RequestBody Catalog param) throws Exception {
        return catalogService.getServicePackCatalogCount(param);
    }




}
