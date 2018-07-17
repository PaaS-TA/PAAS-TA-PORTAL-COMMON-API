package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.openpaas.paasta.portal.common.api.entity.cc.CatalogCc;
import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.CatalogHistory;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SEJI on 2018-03-06.
 */
@RestController

public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    private final String V2_URL = "/v2";

    /**
     * [앱 템플릿] 카탈로그 목록을 조회한다.
     *
     * @param param Catalog model
     * @return Map
     */
    @GetMapping(V2_URL + "/starterpacks")
    public Map<String, Object> getStarterNames(@ModelAttribute StarterCategory param) {
        return catalogService.getStarterNamesList(param);
    }

    /**
     * [앱 템플릿] 카탈로그를 조회한다.
     *
     * @param catalogNo int Catalog No
     * @return Map
     */
    @GetMapping(V2_URL + "/starterpacks/{catalogNo}")
    public Map<String, Object> getStarterCatalog(@PathVariable int catalogNo) {
        return catalogService.getStarterCatalog(catalogNo);
    }

    /**
     * [앱 개발환경] 카탈로그 목록을 조회한다.
     *
     * @param param Catalog model
     * @return Map
     */
    @GetMapping(V2_URL + "/developpacks")
    public Map<String, Object> getBuildPackCatalogList(@ModelAttribute BuildpackCategory param) {
        return catalogService.getBuildPackCatalogList(param);
    }

    /**
     * [앱 개발환경] 카탈로그를 조회한다.
     *
     * @param catalogNo int Catalog No
     * @param param Catalog model
     * @return Map
     */
    @GetMapping(V2_URL + "/developpacks/{catalogNo}")
    public Map<String, Object> getBuildPackCatalogList(@PathVariable int catalogNo, @ModelAttribute BuildpackCategory param) {
        param.setNo(catalogNo);
        return catalogService.getBuildPackCatalogList(param);
    }

    @GetMapping(V2_URL + "/packs")
    public Map<String, Object> getPacks(@RequestParam String searchKeyword) {
        return catalogService.getPacks(searchKeyword);
    }

    /**
     * [앱 서비스] 카탈로그 목록을 조회한다.
     *
     * @param param Catalog
     * @return Map
     */
    @GetMapping(V2_URL + "/servicepacks")
    public Map<String, Object> getServicePackCatalogList(@ModelAttribute ServicepackCategory param) {
        return catalogService.getServicePackCatalogList(param);
    }

    /**
     * [앱 서비스] 카탈로그를 조회한다.
     *
     * @param catalogNo int Catalog No
     * @param param Catalog
     * @return Map
     */
    @GetMapping(V2_URL + "/servicepacks/{catalogNo}")
    public Map<String, Object> getServicePackCatalogList(@PathVariable int catalogNo, @ModelAttribute ServicepackCategory param) {
        param.setNo(catalogNo);
        return catalogService.getServicePackCatalogList(param);
    }

    /**
     * [앱 템플릿] 해당 이름의 카탈로그 목록 개수를 조회한다.
     *
     * @param param Catalog model
     * @return Map
     */
    @GetMapping(V2_URL + "/starterpacks/count")
    public Map<String, Object> getStarterCatalogCount(@ModelAttribute StarterCategory param) {
        int starterCatalogCount = catalogService.getStarterCatalogCount(param);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("startpackcount", starterCatalogCount);

        return resultMap;
    }

    /**
     * [앱 개발환경] 해당 이름의 카탈로그 목록 개수를 조회한다.
     *
     * @param param Catalog model
     * @return Map
     */
    @GetMapping(V2_URL + "/developpacks/count")
    public Map<String, Object> getBuildPackCatalogCount(@ModelAttribute BuildpackCategory param) {
        int buildPackCnt = catalogService.getBuildPackCatalogCount(param);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("buildpackcount", buildPackCnt);

        return resultMap;
    }

    /**
     * [앱 서비스] 해당 이름의 카탈로그 목록 개수를 조회한다.
     * @param param Catalog model
     * @return Map
     */
    @GetMapping(V2_URL + "/servicepacks/count")
    public Map<String, Object> getServicePackCatalogCount(@ModelAttribute ServicepackCategory param) {
        int servicePackCnt = catalogService.getServicePackCatalogCount(param);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("servicepackcount", servicePackCnt);

        return resultMap;
    }

    /**
     * [앱 템플릿] 카탈로그를 생성한다.
     *
     * @param param Catalog model
     * @return Map
     */
    @PostMapping(V2_URL + "/starterpacks")
    public Map<String, Object> insertStarterPacks(@RequestBody StarterCategory param) {
        return catalogService.insertStarterCatalog(param);
    }

    /**
     * [앱 개발환경] 카탈로그를 생성한다.
     *
     * @param param BuildpackCategory model
     * @return Map
     */
    @PostMapping(V2_URL + "/developpacks")
    public Map<String, Object> insertBuildPackCatalog(@RequestBody BuildpackCategory param) {
        return catalogService.insertBuildPackCatalog(param);
    }

    /**
     * [앱 서비스] 카탈로그를 생성한다.
     *
     * @param param ServicepackCategory model
     * @return Map
     */
    @PostMapping(V2_URL + "/servicepacks")
    public Map<String, Object> insertServicePackCatalog(@RequestBody ServicepackCategory param) {
        return catalogService.insertServicePackCatalog(param);
    }

    /**
     * [앱 템플릿] 카탈로그를 수정한다.
     *
     * @param catalogNo int Catalog No
     * @param param Catalog model
     * @return Map
     */
    @PutMapping(value = {V2_URL + "/starterpacks/{catalogNo}"})
    public Map<String, Object> updatesStarterPackCatalog(@PathVariable int catalogNo, @RequestBody StarterCategory param) {

        param.setNo(catalogNo);
        return catalogService.updateStarterCatalog(param);
    }

    /**
     * [앱 개발환경] 카탈로그를 수정한다.
     *
     * @param catalogNo int Catalog No
     * @param param BuildpackCategory model
     * @return Map
     */
    @PutMapping(value = {V2_URL + "/developpacks/{catalogNo}"})
    public Map<String, Object> updateBuildPackCatalog(@PathVariable int catalogNo, @RequestBody BuildpackCategory param) {
        param.setNo(catalogNo);
        return catalogService.updateBuildPackCatalog(param);
    }

    /**
     * [앱 서비스] 카탈로그를 수정한다.
     *
     * @param catalogNo int Catalog No
     * @param param ServicepackCategory model
     * @return Map
     */
    @PutMapping(value = {V2_URL + "/servicepacks/{catalogNo}"})
    public Map<String, Object> updateServicePackCatalog(@PathVariable int catalogNo, @RequestBody ServicepackCategory param) {
        param.setNo(catalogNo);
        return catalogService.updateServicePackCatalog(param);
    }

    /**
     * [앱 템플릿] 카탈로그를 삭제한다.
     *
     * @param catalogNo int Catalog No
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/starterpacks/{catalogNo}"})
    public Map<String, Object> deleteStarterCatalog(@PathVariable int catalogNo) {
        return catalogService.deleteStarterCatalog(catalogNo);
    }

    /**
     * [앱 개발환경] 카탈로그를 삭제한다.
     *
     * @param catalogNo int Catalog No
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/developpacks/{catalogNo}"})
    public Map<String, Object> deleteBuildPackCatalog(@PathVariable int catalogNo) {
        return catalogService.deleteBuildPackCatalog(catalogNo);
    }

    /**
     * [앱 서비스] 카탈로그를 삭제한다.
     *
     * @param catalogNo int Catalog No
     * @return Map(자바클래스)
     */
    @DeleteMapping(value = {V2_URL + "/servicepacks/{catalogNo}"})
    public Map<String, Object> deleteServicePackCatalog(@PathVariable int catalogNo) {
        return catalogService.deleteServicePackCatalog(catalogNo);
    }

    // To-Be 삭제시 아래 체크 사항 추가할 것 2018.05.16 CYS
    // Delete Check : delete buildPack Catalog
    // Delete Check : delete servicePack Catalog
    /**
     * [앱 개발환경]를 삭제시, 해당 카탈로그를 사용중인 [앱 템플릿] 개수를 조회한다.
     *
     * @param catalogNo int Catalog No
     * @return Map
     */
    @GetMapping(V2_URL + "/developpacks/relationcount/{catalogNo}")
    public Map<String, Object> getBuildPackCatalogRelCount(@PathVariable int catalogNo) {
        int buildPackRelCnt = 0; // temp
//                catalogService.getBuildPackCatalogRelCount(param);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("buildpackRelcount", buildPackRelCnt);
        return resultMap;

    }

    /**
     * [앱 서비스]를 삭제시, 해당 카탈로그를 사용중인 [앱 템플릿] 개수를 조회한다.
     *
     * @param catalogNo int Catalog No
     * @return Map
     */
    @GetMapping(V2_URL + "/servicepacks/relationcount/{catalogNo}")
    public Map<String, Object> getServicePackCatalogRelCount(@PathVariable int catalogNo) {
        int servicePackRelCnt = 0; // temp
//                catalogService.getServicePackCatalogRelCount(param);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("servicepackRelcount", servicePackRelCnt);
        return resultMap;
    }


    /**
     * 최신 이용 내역을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/{userid}/history")
    public Map<String, Object> getHistory(@PathVariable String userid) {
        return catalogService.getHistory(userid);
    }

    /**
     * 스타터팩에 속한 빌드팩, 서비스팩을 조회한다.
     *
     * @return Map(자바클래스)
     */
    @GetMapping(V2_URL + "/packrelation/{no}")
    public Map<String,Object> getStarterRelation(@PathVariable int no){
        return catalogService.getStarterRelation(no);
    }

    /**
     * 실행한 환경을 저장한다.
     *
     * @return Map(자바클래스)
     */
    @PostMapping(V2_URL + "/history")
    public Map<String,Object> insertHistroy(@RequestBody CatalogHistory catalog){
        return catalogService.insertHistroy(catalog);
    }

    @GetMapping(V2_URL + "/routes")
    public List<CatalogCc> getListRoutes(){
        return catalogService.getListRoutes();
    }

    @GetMapping(V2_URL + "/routes/{hostname}")
    public Map<String,Object> checkRoute(@PathVariable String hostname){
        return catalogService.checkRoute(hostname);
    }
}
