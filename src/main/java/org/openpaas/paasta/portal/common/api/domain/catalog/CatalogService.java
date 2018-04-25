package org.openpaas.paasta.portal.common.api.domain.catalog;

import com.netflix.discovery.converters.Auto;
import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.*;
import org.openpaas.paasta.portal.common.api.model.Catalog;
import org.openpaas.paasta.portal.common.api.repository.portal.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

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
    StarterServicepackRelationRepository starterServicePackRelationRepository;

    @Autowired
    StarterBuildPackRelationRepository starterBuildPackRelationRepository;

    @Autowired
    CatalogHistoryRepository catalogHistoryRepository;

    @Autowired
    JinqSource jinqSource;

    /**
     * 앱 템플릿 카탈로그 조회
     *
     * @param no Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterCatalog(int no) {


        StarterCategory starterCategory = starterCategoryRepository.findOne(no);

        try {
            //기존 스타터 서비스 릴레이션 가져오기
            List<Integer> ssrIntList = new ArrayList<>();
            List<StarterServicepackRelation> ssrList = starterServicePackRelationRepository.findByStarterCatalogNo(no);
            for (StarterServicepackRelation ssr : ssrList) {
                ssrIntList.add(ssr.getServicepackCategoryNo());
            }

            starterCategory.setServicePackCategoryNoList(ssrIntList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            /*
             * 기존 스타터 빌드  릴레이션 가져오기
             * -> List로 왜 뽑냐면...Buildpack relation 하나만 들어간다는 전제가 걸려야함
             * -> JPA상에서 에러를 배출...Unique 값이 아닌데 하나만 나오게 했다고 그래서 List로 뽑음
             */


            List<StarterBuildpackRelation> sbr = starterBuildPackRelationRepository.findByStarterCatalogNo(no);
            starterCategory.setBuildPackCategoryNo(sbr.get(0).getBuildpackCategoryNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, Object>() {{
            put("info", starterCategory);
        }};
    }

    /**
     * 앱 템플릿명 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterNamesList(Catalog param) {
        logger.info("getStarterNamesList :: " + param.toString());
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
        logger.info("getBuildPackCatalogList :: " + param.toString());
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

    public Map<String, Object> getPacks(String searchKeyword) {

        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);
        logger.info("list : ", streams);
        JinqStream<BuildpackCategory> streams2 = jinqSource.streamAllPortal(BuildpackCategory.class);
        logger.info("list : ", streams2);
        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword));
            streams2 = streams2.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword));
        }
        streams = streams.sortedDescendingBy(c -> c.getNo());
        streams2 = streams2.sortedDescendingBy(c -> c.getNo());
        List<StarterCategory> starterCategoryList = streams.toList();
        List<BuildpackCategory> buildpackCategoryList = streams2.toList();
        return new HashMap<String, Object>() {{
            put("TemplateList", starterCategoryList);
            put("BuildPackList", buildpackCategoryList);
        }};
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public HashMap<String, Object> getServicePackCatalogList(Catalog param) {
        logger.info("getServicePackCatalogList :: " + param.toString());
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
    public int getStarterCatalogCount(Catalog param) {
        logger.info("getStarterCatalogCount :: " + param.toString());
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);

        int startPackCnt = 0;
        String name = param.getName();

        if (null != name && !"".equals(name)) {
            streams = streams.where(c -> c.getName().equals(name));
            streams = streams.sortedDescendingBy(c -> c.getNo());
            List<StarterCategory> starterCategoryList = streams.toList();
            startPackCnt = starterCategoryList.size();
        }
        return startPackCnt;
    }

    /**
     * 앱 개발환경 카탈로그 개수를 조회한다.
     *
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getBuildPackCatalogCount(Catalog param) {
        logger.info("getBuildPackCatalogCount :: " + param.toString());
        JinqStream<BuildpackCategory> streams = jinqSource.streamAllPortal(BuildpackCategory.class);

        int buildPackCnt = 0;
        String name = param.getName();

        if (null != name && !"".equals(name)) {
            streams = streams.where(c -> c.getName().equals(name));
            streams = streams.sortedDescendingBy(c -> c.getNo());
            List<BuildpackCategory> buildpackCategoryList = streams.toList();
            buildPackCnt = buildpackCategoryList.size();
        }


        return buildPackCnt;
    }

    /**
     * 서비스 카탈로그 개수를 조회한다.
     *
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getServicePackCatalogCount(Catalog param) {
        logger.info("getServicePackCatalogCount :: " + param.toString());
        JinqStream<ServicepackCategory> streams = jinqSource.streamAllPortal(ServicepackCategory.class);

        int servicePackCnt = 0;
        String name = param.getName();

        if (null != name && !"".equals(name)) {
            streams = streams.where(c -> c.getName().equals(name));
            streams = streams.sortedDescendingBy(c -> c.getNo());
            List<ServicepackCategory> servicepackCategoryList = streams.toList();
            servicePackCnt = servicepackCategoryList.size();
        }

        return servicePackCnt;
    }

    /**
     * 앱 템플릿 카탈로그를 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertStarterCatalog(Catalog param) {
        logger.info("insertStarterCatalog :: " + param.toString());
        StarterCategory starterCategory = convertStarterCategory(param);
        starterCategoryRepository.save(starterCategory);
        logger.info("insertStarterCatalog :: NO2 " + starterCategory.getNo());
        try {
            //스타터서비스 릴레이션 저장
            for (int servicepackCategoryNo : param.getServicePackCategoryNoList()) {
                StarterServicepackRelation ssr = new StarterServicepackRelation();
                ssr.setStarterCatalogNo(starterCategory.getNo());
                ssr.setServicepackCategoryNo(servicepackCategoryNo);
                starterServicePackRelationRepository.save(ssr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //스타터 빌드팩 릴레이션 저장
            StarterBuildpackRelation sbr = new StarterBuildpackRelation();
            sbr.setBuildpackCategoryNo(param.getBuildPackCategoryNo());
            sbr.setStarterCatalogNo(starterCategory.getNo());

            starterBuildPackRelationRepository.save(sbr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    private StarterCategory convertStarterCategory(Catalog param) {
        StarterCategory starterCategory = new StarterCategory();
        starterCategory.setNo(param.getNo());
        starterCategory.setName(param.getName());
        starterCategory.setClassification(param.getClassification());
        starterCategory.setSummary(param.getSummary());
        starterCategory.setDescription(param.getDescription());
        starterCategory.setThumbIimgName(param.getThumbImgName());
        starterCategory.setThumbImgPath(param.getThumbImgPath());
        starterCategory.setUseYn(param.getUseYn());
        starterCategory.setUserId(param.getUserId());
        starterCategory.setCreated(new Date());
        return starterCategory;
    }


    /**
     * 앱 개발환경 카탈로그를 저장한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertBuildPackCatalog(BuildpackCategory param) {
        logger.info("insertBuildPackCatalog :: " + param.toString());
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
        logger.info("insertServicePackCatalog :: " + param.toString());
        if (param.getApp_bind_parameter() != null) {
            param.setAppBindParameter(param.getApp_bind_parameter());
        }
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
    public Map<String, Object> updateStarterCatalog(Catalog param) {
        logger.info("updateStarterCatalog :: " + param.toString());
        StarterCategory starterCategory = convertStarterCategory(param);
        //Upate Starter Catalog
        starterCategoryRepository.save(starterCategory);

        try {
            //기존 스타터서비스 릴레이션 삭제
            logger.info("updateStarterCatalog before start service relation remove");
            List<StarterServicepackRelation> ssrList = starterServicePackRelationRepository.findByStarterCatalogNo(starterCategory.getNo());
            for (StarterServicepackRelation ssr : ssrList) {
                logger.info("ssrList " + ssr.toString());
                starterServicePackRelationRepository.delete(ssr.getNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            logger.info("updateStarterCatalog before start build relation remove");
            //기존 스타터서비스 릴레이션 삭제
            List<StarterBuildpackRelation> sbrList = starterBuildPackRelationRepository.findByStarterCatalogNo(starterCategory.getNo());
            for (StarterBuildpackRelation sbr : sbrList) {
                logger.info("sbrList " + sbr.toString());
                starterBuildPackRelationRepository.delete(sbr.getNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            logger.info("updateStarterCatalog start service relation insert");
            //스타터서비스 릴레이션 저장

            for (int servicepackCategoryNo : param.getServicePackCategoryNoList()) {
                StarterServicepackRelation ssrInsert = new StarterServicepackRelation();
                ssrInsert.setStarterCatalogNo(starterCategory.getNo());
                ssrInsert.setServicepackCategoryNo(servicepackCategoryNo);
                logger.info(ssrInsert.toString());
                starterServicePackRelationRepository.save(ssrInsert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            logger.info("updateStarterCatalog start build relation insert");
            //스타터 빌드팩 릴레이션 저장
            StarterBuildpackRelation sbrInsert = new StarterBuildpackRelation();
            sbrInsert.setBuildpackCategoryNo(param.getBuildPackCategoryNo());
            sbrInsert.setStarterCatalogNo(param.getNo());

            starterBuildPackRelationRepository.save(sbrInsert);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public Map<String, Object> updateBuildPackCatalog(BuildpackCategory param) {
        logger.info("updateBuildPackCatalog :: " + param.toString());

        BuildpackCategory update = buildpackCategoryRepository.findOne(param.getNo());
        param.setCreated(update.getCreated());
        param.setLastmodified(new Date());
        BuildpackCategory buildpackCategory = buildpackCategoryRepository.save(param);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 서비스 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateServicePackCatalog(ServicepackCategory param) {
        logger.info("updateServicePackCatalog :: " + param.toString());

        if (param.getApp_bind_parameter() != null) {
            param.setAppBindParameter(param.getApp_bind_parameter());
        }

        ServicepackCategory update = servicepackCategoryRepository.findOne(param.getNo());
        param.setCreated(update.getCreated());
        param.setLastmodified(new Date());
        ServicepackCategory servicepackCategory = servicepackCategoryRepository.save(param);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 앱 템플릿 카탈로그를 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteStarterCatalog(int no) {
        logger.info("deleteStarterCatalog :: " + no);

        try {
            //기존 스타터서비스 릴레이션 삭제
            logger.info("updateStarterCatalog before start service relation remove");
            List<StarterServicepackRelation> ssrList = starterServicePackRelationRepository.findByStarterCatalogNo(no);
            for (StarterServicepackRelation ssr : ssrList) {
                logger.info("ssrList " + ssr.toString());
                starterServicePackRelationRepository.delete(ssr.getNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            logger.info("updateStarterCatalog before start build relation remove");
            //기존 스타터서비스 릴레이션 삭제
            List<StarterBuildpackRelation> sbrList = starterBuildPackRelationRepository.findByStarterCatalogNo(no);
            for (StarterBuildpackRelation sbr : sbrList) {
                logger.info("sbrList " + sbr.toString());
                starterBuildPackRelationRepository.delete(sbr.getNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
        logger.info("deleteBuildPackCatalog :: " + no);
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
        logger.info("deleteServicePackCatalog :: " + no);
        servicepackCategoryRepository.delete(no);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 최신항목을 가져온다.
     *
     */
    public Map<String,Object> getHistoty() {
        List<Object> resultHistory = new ArrayList<>();
        JinqStream<CatalogHistory> streams = jinqSource.streamAllPortal(CatalogHistory.class);
        JinqStream<Integer> integerJinqStream = streams.where(c -> c.getUserId().contains("swmoon")).select(c -> c.getCatalogNo()).distinct();
        List<Integer> catalogHistoryList = integerJinqStream.toList();
        int size = catalogHistoryList.size();
        int length = size < 4 ? catalogHistoryList.size() : 4;
        for(int i = 1 ; i<= catalogHistoryList.size(); i++)
        {
            int index = catalogHistoryList.get(size-i);
            String  type = streams.where(c -> c.getCatalogNo()==index).select(c-> c.getCatalogType()).toList().get(0);
            if(type.equals("servicePack")) {
                if(servicepackCategoryRepository.findByNo(index) !=null)
                    resultHistory.add(servicepackCategoryRepository.findByNo(index));
            }
            else if(type.equals("buildPack")){
                if(buildpackCategoryRepository.findByNo(index) != null)
                resultHistory.add(buildpackCategoryRepository.findByNo(index));
            }
            else if(type.equals("starter")){
                if(starterCategoryRepository.findByNo(index) != null)
                    resultHistory.add(starterCategoryRepository.findByNo(index));
            }
        }
        resultHistory.add(starterCategoryRepository.findByNo(9609));
        return new HashMap<String, Object>() {{
            put("list", resultHistory);
        }};
    }
}
/*
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
 */