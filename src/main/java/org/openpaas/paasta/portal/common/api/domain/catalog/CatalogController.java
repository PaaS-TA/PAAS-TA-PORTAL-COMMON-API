package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
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

public class CatalogController {
    private static final Logger logger = getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    private final String V2_URL = "/v2";


    /**
     * 앱 템플릿 카탈로그를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/oneStarterCatalog")
    public Map<String, Object> getOneStarterCatalog(@ModelAttribute Catalog param) {
        return catalogService.getOneStarterCatalog(param);
    }


    /**
     * 앱 템플릿 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/starterpacks")
    public Map<String, Object> getStarterNames(@ModelAttribute Catalog param) {
        return catalogService.getStarterNamesList(param);
    }

    /**
     * 앱 템플릿 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/starterpacks/{no}")
    public Map<String, Object> getStarterNames(@PathVariable("no") int no, @ModelAttribute Catalog param) {
        param.setNo(no);
        return catalogService.getStarterNamesList(param);
    }


    /**
     * 앱 개발환경 카탈로그 목록을 조회한다.
     */
    @GetMapping(V2_URL + "/developpacks")
    public Map<String, Object> getBuildPackCatalogList(@ModelAttribute Catalog param) {
        return catalogService.getBuildPackCatalogList(param);
    }

    /**
     * 앱 개발환경 카탈로그 목록을 조회한다.
     */
    @GetMapping(V2_URL + "/developpacks/{no}")
    public Map<String, Object> getBuildPackCatalogList(@PathVariable("no") int no, @ModelAttribute Catalog param) {
        param.setNo(no);
        return catalogService.getBuildPackCatalogList(param);
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/servicepacks")
    public Map<String, Object> getServicePackCatalogList(@ModelAttribute Catalog param) {
        return catalogService.getServicePackCatalogList(param);
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/servicepacks/{no}")
    public Map<String, Object> getServicePackCatalogList(@PathVariable("no") int no, @ModelAttribute Catalog param) {
        param.setNo(no);
        return catalogService.getServicePackCatalogList(param);
    }

    /**
     * 앱 템플릿 카탈로그 개수를 조회한다.
     */
    @GetMapping(V2_URL + "/starterpacks/count")
    public Map<String, Object> getStarterCatalogCount(@ModelAttribute Catalog param) throws Exception {
        int starterCatalogCount = catalogService.getStarterCatalogCount(param);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("startpackcount", starterCatalogCount);

        return resultMap;
    }


    /**
     * 앱 개발환경 목록 개수를 조회한다.
     */
    @GetMapping(V2_URL + "/developpacks/count")
    public Map<String, Object> getBuildPackCatalogCount(Catalog param) throws Exception {
        int buildPackCnt = catalogService.getBuildPackCatalogCount(param);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("buildpackcount", buildPackCnt);

        return resultMap;
    }


    /**
     * 서비스 목록 개수를 조회한다.
     */
    @GetMapping(V2_URL + "/servicepacks/count")
    public Map<String, Object> getServicePackCatalogCount(@ModelAttribute Catalog param) throws Exception {
        int servicePackCnt = catalogService.getServicePackCatalogCount(param);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("servicepackcount", servicePackCnt);

        return resultMap;
    }


    /**
     * 스타터 개발환경 카탈로그를 생성한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/starterpacks")
    public Map<String, Object> insertStarterPacks(@RequestBody StarterCategory param) {
        return catalogService.insertStarterCatalog(param);
    }


    /**
     * 앱 개발환경 카탈로그를 생성한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/developpacks")
    public Map<String, Object> insertBuildPackCatalog(@RequestBody BuildpackCategory param) {
        return catalogService.insertBuildPackCatalog(param);
    }


    /**
     * 서비스 카탈로그를 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/servicepacks")
    public Map<String, Object> insertServicePackCatalog(@RequestBody ServicepackCategory param) {
        return catalogService.insertServicePackCatalog(param);
    }


    /**
     * 서비스 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(value = {V2_URL + "/starterpack/{no}"})
    public Map<String, Object> updatesStarterPackCatalog(@PathVariable int no, @RequestBody StarterCategory param) {
        param.setNo(no);
        return catalogService.updateStarterCatalog(param);
    }


    /**
     * 앱 개발환경 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(value = {V2_URL + "/developpacks/{no}"})
    public Map<String, Object> updateBuildPackCatalog(@PathVariable int no, @RequestBody BuildpackCategory param) {
        param.setNo(no);
        return catalogService.updateBuildPackCatalog(param);
    }


    /**
     * 서비스 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping(value = {V2_URL + "/servicepacks/{no}"})
    public Map<String, Object> updateServicePackCatalog(@PathVariable int no, @RequestBody ServicepackCategory param) {
        param.setNo(no);
        return catalogService.updateServicePackCatalog(param);
    }

    /**
     * 앱 템플릿 카탈로그를 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/starterpacks/{no}"})
    public Map<String, Object> deleteStarterCatalog(@PathVariable int no) {
        return catalogService.deleteStarterCatalog(no);
    }


    /**
     * 앱 개발환경 카탈로그를 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/developpacks/{no}"})
    public Map<String, Object> deleteBuildPackCatalog(@PathVariable int no) {
        return catalogService.deleteBuildPackCatalog(no);
    }

    /**
     * 서비스 카탈로그를 삭제한다.
     *
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/servicepacks/{no}"})
    public Map<String, Object> deleteServicePackCatalog(@PathVariable int no) {
        return catalogService.deleteServicePackCatalog(no);
    }

}
