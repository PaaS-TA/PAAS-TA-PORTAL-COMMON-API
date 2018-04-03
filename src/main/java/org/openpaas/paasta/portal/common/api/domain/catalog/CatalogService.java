package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterServicepackRelation;
import org.openpaas.paasta.portal.common.api.repository.portal.BuildpackCategoryRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.ServicepackCategoryRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.StarterCategoryRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.StarterServicepackRelationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-06.
 */
@Service
public class CatalogService {

    private final Logger logger = getLogger(this.getClass());

    @Autowired
    StarterCategoryRepository starterCategoryRepository;

    @Autowired
    BuildpackCategoryRepository buildpackCategoryRepository;

    @Autowired
    ServicepackCategoryRepository servicepackCategoryRepository;

    @Autowired
    StarterServicepackRelationRepository starterServicepackRelationRepository;

    @Autowired
    JinqSource jinqSource;

    /**
     * 앱 템플릿 카탈로그 조회
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getOneStarterCatalog(Catalog param) {

        JinqStream<StarterServicepackRelation> starterServicepackRelationStream = jinqSource.streamAllPortal(StarterServicepackRelation.class);
        JinqStream<StarterCategory> starterCategoryStream = jinqSource.streamAllPortal(StarterCategory.class);

        int no = param.getNo();

        starterServicepackRelationStream = starterServicepackRelationStream.where(c -> c.getServicepackCategoryNo() == no);
        starterServicepackRelationStream = starterServicepackRelationStream.sortedBy(c -> c.getServicepackCategoryNo());
        List<StarterServicepackRelation> starterServicepackRelations = starterServicepackRelationStream.toList();

        starterCategoryStream = starterCategoryStream.where(c -> c.getNo() == no);
//       StarterCategory starterCategory = starterCategoryStream.findFirst().get();
        Optional<StarterCategory> starterCategoryRelations = starterCategoryStream.findFirst();

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> resultMap2 = new HashMap<>();

        resultMap.put("servicePackCategoryNoList", starterServicepackRelations);
//        resultMap.put("StarterCategoryNo", starterCategory.getNo());
        resultMap.put("StarterCategoryNo", starterCategoryRelations);


        resultMap2.put("info", resultMap);
        resultMap2.put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        return resultMap2;
    }

    /**
     * 앱 템플릿명 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterNamesList(Catalog param) {
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);

        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();

        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword));
        }

        streams = streams.sortedDescendingBy(c -> c.getNo());
        List<StarterCategory> starterCategoryList = streams.toList();

        return new HashMap<String, Object>() {{
            put("list", starterCategoryList);
        }};

    }

    /**
     * 앱 개발환경 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getBuildPackCatalogList(Catalog param) {

        JinqStream<BuildpackCategory> streams = jinqSource.streamAllPortal(BuildpackCategory.class);

        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();

        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword));
        }

        if (no != 0) {
            streams = streams.where(c -> c.getNo() == no);
        }

        streams = streams.sortedDescendingBy(c -> c.getNo());
        List<BuildpackCategory> buildpackCategoryList = streams.toList();

        return new HashMap<String, Object>() {{
            put("list", buildpackCategoryList);
        }};
    }

    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public HashMap<String, Object> getServicePackCatalogList(Catalog param) {
        JinqStream<ServicepackCategory> streams = jinqSource.streamAllPortal(ServicepackCategory.class);
        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();
        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword));
        }

        if (no != 0) {
            streams = streams.where(c -> c.getNo() == no);
        }

        streams = streams.sortedDescendingBy(c -> c.getNo());
        List<ServicepackCategory> servicePackCatalogList = streams.toList();

        return new HashMap<String, Object>() {{
            put("list", servicePackCatalogList);
        }};
    }

    /**
     * 앱 템플릿 카탈로그 개수를 조회한다.
     *
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getStarterCatalogCount() {

        int buildPackCnt = (int) starterCategoryRepository.count();
        System.out.println(buildPackCnt);
        return buildPackCnt;
    }

    /**
     * 앱 개발환경 카탈로그 개수를 조회한다.
     *
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getBuildPackCatalogCount() {

        int buildPackCnt = (int) buildpackCategoryRepository.count();
        System.out.println(buildPackCnt);
        return buildPackCnt;
    }

    /**
     * 서비스 카탈로그 개수를 조회한다.
     *
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getServicePackCatalogCount() {

        int servicePackCnt = (int) servicepackCategoryRepository.count();
        return servicePackCnt;
    }

    /**
     * 앱 템플릿 카탈로그를 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertStarterCatalog(StarterCategory param) {
        logger.info("insertStarterCatalog");
        starterCategoryRepository.save(param);

//        for (int i = 0; i < param.getServicePackCategoryNoList().size(); i++) {
//            catalogMapper.insertSelectedServicePackList(param.getServicePackCategoryNoList().get(i));
//        }
//
//        resultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);
//
//        return resultMap;
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


    /**
     * 앱 개발환경 카탈로그를 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertBuildPackCatalog(BuildpackCategory param) {
        logger.info("insertBuildPackCatalog");
        buildpackCategoryRepository.save(param);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 서비스 카탈로그를 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertServicePackCatalog(ServicepackCategory param) {
        logger.info("insertServicePackCatalog");
        servicepackCategoryRepository.save(param);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 앱 개발환경 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public StarterCategory updateStarterCatalog(StarterCategory param) {
        logger.info("updateStarterCatalog");
        StarterCategory update = starterCategoryRepository.findOne(param.getNo());
        param.setCreated(update.getCreated());
        param.setLastmodified(new Date());
        StarterCategory starterCategory = starterCategoryRepository.save(param);
        return starterCategory;
    }

    /**
     * 앱 개발환경 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public BuildpackCategory updateBuildPackCatalog(BuildpackCategory param) {
        logger.info("updateBuildPackCatalog");
        BuildpackCategory update = buildpackCategoryRepository.findOne(param.getNo());
        param.setCreated(update.getCreated());
        param.setLastmodified(new Date());
        BuildpackCategory buildpackCategory = buildpackCategoryRepository.save(param);
        return buildpackCategory;
    }

    /**
     * 서비스 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public ServicepackCategory updateServicePackCatalog(ServicepackCategory param) {
        logger.info("updateBuildPackCatalog");
        ServicepackCategory update = servicepackCategoryRepository.findOne(param.getNo());
        param.setCreated(update.getCreated());
        param.setLastmodified(new Date());
        ServicepackCategory buildpackCategory = servicepackCategoryRepository.save(param);
        return buildpackCategory;
    }

    /**
     * 앱 템플릿 카탈로그를 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteStarterCatalog(int no) {
        starterCategoryRepository.delete(no);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 앱 개발환경 카탈로그를 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteBuildPackCatalog(int no) {
        buildpackCategoryRepository.delete(no);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 서비스 카탈로그를 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteServicePackCatalog(int no) {
        servicepackCategoryRepository.delete(no);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

}
