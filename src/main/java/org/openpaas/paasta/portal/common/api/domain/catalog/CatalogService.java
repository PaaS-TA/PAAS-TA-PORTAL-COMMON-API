package org.openpaas.paasta.portal.common.api.domain.catalog;


import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.cc.CatalogCc;
import org.openpaas.paasta.portal.common.api.entity.portal.*;
import org.openpaas.paasta.portal.common.api.repository.cc.CatalogCcRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.*;
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
    StarterServicepackRelationRepository starterServicePackRelationRepository;

    @Autowired
    StarterBuildPackRelationRepository starterBuildPackRelationRepository;

    @Autowired
    CatalogHistoryRepository catalogHistoryRepository;

    @Autowired
    public JinqSource jinqSource;

    @Autowired
    CatalogCcRepository catalogCcRepository;

    @Autowired
    CodeDetailRepository codeDetailRepository;

    /**
     * 앱 템플릿 카탈로그 조회
     *
     * @param no Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterCatalog(int no) {
        logger.info("" + no);
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
    public Map<String, Object> getStarterNamesList(StarterCategory param, String lang) {
        logger.info("getStarterNamesList :: " + param.toString());
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class).where(c -> c.getLanguage().equals(lang));

        //int no = param.getNo();
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
     * 앱 템플릿 목록을 조회한다.
     *
     * @param use String
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterList(String use, String lang) {
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class).where(c -> c.getLanguage().equals(lang));;

        if (null != use && !"".equals(use)) {
            streams = streams.where(c -> c.getUseYn().equals(use));
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
    public Map<String, Object> getBuildPackCatalogList(BuildpackCategory param, String lang, String isRequiredList) {
        logger.info("getBuildPackCatalogList :: " + param.toString());
	
	    JinqStream<BuildpackCategory> streams = jinqSource.streamAllPortal(BuildpackCategory.class);

        if(lang != null && !"".equals(lang) && isRequiredList == "no") {
            streams = streams.where(c -> c.getLanguage().equals(lang));
        }

        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();
        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword));
        }

        if (no != 0) {  // 상세조회 시
            BuildpackCategory bc = buildpackCategoryRepository.findOne(no);
            String classificationCode = codeDetailRepository.findOne(bc.getCodeDetailNo()).getKey();

            if (isRequiredList.equals("yes")) {  // 유저페이지에서 요청하는 경우
                streams = streams.where(c -> c.getClassificationCode().equals(classificationCode));
            } else {    // 관리자페이지에서 요청하는 경우
                streams = streams.where(c -> c.getNo() == no);
            }
        }

        streams = streams.sortedDescendingBy(c -> c.getNo());
        List<BuildpackCategory> buildpackCategoryList = streams.toList();
        return new HashMap<String, Object>() {{
            put("list", buildpackCategoryList);
        }};
    }

    /**
     * 앱 개발환경 카탈로그 목록을 조회한다.
     *
     * @param use String
     * @return Map(자바클래스)
     */
    public Map<String, Object> getBuildPackList(String use, String lang) {
        JinqStream<BuildpackCategory> streams = jinqSource.streamAllPortal(BuildpackCategory.class).where(c -> c.getLanguage().equals(lang));
        if (null != use && !"".equals(use)) {
            streams = streams.where(c -> c.getUseYn().equals(use));
        }

        streams = streams.sortedDescendingBy(c -> c.getNo());
        List<BuildpackCategory> starterCategoryList = streams.toList();
        return new HashMap<String, Object>() {{
            put("list", starterCategoryList);
        }};
    }

    public Map<String, Object> getPacks(String searchKeyword) {

        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);
        JinqStream<BuildpackCategory> streams2 = jinqSource.streamAllPortal(BuildpackCategory.class);
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
     * @param use String
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackList(String use, String lang) {
        JinqStream<ServicepackCategory> streams = jinqSource.streamAllPortal(ServicepackCategory.class).where(c -> c.getLanguage().equals(lang));
        logger.info(use);
        if (null != use && !"".equals(use)) {
            streams = streams.where(c -> c.getUseYn().equals(use));
        }

        streams = streams.sortedDescendingBy(c -> c.getNo());
        List<ServicepackCategory> starterCategoryList = streams.toList();
        return new HashMap<String, Object>() {{
            put("list", starterCategoryList);
        }};
    }


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalogList(ServicepackCategory param, String lang, String isRequiredList) {
        logger.info("getServicePackCatalogList :: " + param.toString());

	    JinqStream<ServicepackCategory> streams = jinqSource.streamAllPortal(ServicepackCategory.class);

	    if(lang != null && !"".equals(lang) && isRequiredList == "no") {
            streams = streams.where(c -> c.getLanguage().equals(lang));
        }

        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();
        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword));
        }

        logger.info("서비스 카탈로그 번호 확인 --> " + no);
        if (no != 0) {
            ServicepackCategory sc = servicepackCategoryRepository.findOne(no);
            logger.info("서비스팩 카테고리 확인 --> " + sc.toString());
            String classificationCode = codeDetailRepository.findOne(sc.getCodeDetailNo()).getKey();
            logger.info("분류 코드 확인 --> " + classificationCode);

            logger.info("getServicePackCatalogLsit -> isRequiredList :: " + isRequiredList);

            if(isRequiredList.equals("yes")) {
                logger.info("목록 반환");
                streams = streams.where(c -> c.getClassificationCode().equals(classificationCode));
            } else {
                logger.info("단순 객체 반환");
                streams = streams.where(c -> c.getNo() == no);
            }
        }

        streams = streams.sortedDescendingBy(c -> c.getNo());
        List<ServicepackCategory> servicePackCatalogList = streams.toList();

        for(ServicepackCategory sc : servicePackCatalogList) {
            logger.info("서비스팩 카탈로그 리스트 확인 --> " + sc.toString());
        }
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
    public int getStarterCatalogCount(StarterCategory param, String lang) {
        logger.info("getStarterCatalogCount :: " + param.toString());
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class).where(c -> c.getLanguage().equals(lang));

        int startPackCnt = 0;
        String name = param.getName();

        if (null != name && !"".equals(name)) {
            streams = streams.where(c -> c.getName().equals(name));
            streams = streams.sortedDescendingBy(c -> c.getNo());
            List<StarterCategory> starterCategoryList = streams.toList();
            startPackCnt = starterCategoryList.size();
        }

        int codeDetailNo = param.getCodeDetailNo();


        return startPackCnt;
    }

    /**
     * 앱 개발환경 카탈로그 개수를 조회한다.
     *
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getBuildPackCatalogCount(BuildpackCategory param, String lang) {
        logger.info("getBuildPackCatalogCount :: " + param.toString());
        JinqStream<BuildpackCategory> streams = jinqSource.streamAllPortal(BuildpackCategory.class).where(c -> c.getLanguage().equals(lang));

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
    public int getServicePackCatalogCount(ServicepackCategory param, String lang) {
        logger.info("getServicePackCatalogCount :: " + param.toString());
        JinqStream<ServicepackCategory> streams = jinqSource.streamAllPortal(ServicepackCategory.class).where(c -> c.getLanguage().equals(lang));

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
    public Map<String, Object> insertStarterCatalog(StarterCategory param) {
        logger.info("insertStarterCatalog :: " + param.toString());
        starterCategoryRepository.save(param);
        logger.info("insertStarterCatalog :: NO2 " + param.getNo());
        try {
            //스타터서비스 릴레이션 저장
            for (int servicepackCategoryNo : param.getServicePackCategoryNoList()) {
                StarterServicepackRelation ssr = new StarterServicepackRelation();
                ssr.setStarterCatalogNo(param.getNo());
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
            sbr.setStarterCatalogNo(param.getNo());

            starterBuildPackRelationRepository.save(sbr);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        param.setParameter(jsonCheck(param.getParameter()));
        param.setAppBindParameter(jsonCheck(param.getAppBindParameter()));
        servicepackCategoryRepository.save(param);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


    private String jsonCheck(String str) {
        String original = str;
        str = str.replace("{", "").replace("}", "");
        String[] params1 = str.split(",");

        if (params1.length == 0) {
            return "";
        } else {
            if (str.indexOf(":") <= 0) {
                return "";
            } else {
                return original;
            }
        }
    }

    /**
     * 앱 템플릿 카탈로그를 수정한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateStarterCatalog(StarterCategory param) {
        logger.info("updateStarterCatalog :: " + param.toString());
        //Upate Starter Catalog
        starterCategoryRepository.save(param);

        try {
            //기존 스타터서비스 릴레이션 삭제
            logger.info("updateStarterCatalog before start service relation remove");
            List<StarterServicepackRelation> ssrList = starterServicePackRelationRepository.findByStarterCatalogNo(param.getNo());
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
            List<StarterBuildpackRelation> sbrList = starterBuildPackRelationRepository.findByStarterCatalogNo(param.getNo());
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
                ssrInsert.setStarterCatalogNo(param.getNo());
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
        //BuildpackCategory buildpackCategory = buildpackCategoryRepository.save(param);
        logger.info("앱 개발환경 카탈로그 확인 --> " + param.toString());
        buildpackCategoryRepository.save(param);

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
        int no = param.getNo();
        ServicepackCategory update = servicepackCategoryRepository.findOne(no);
        param.setCreated(update.getCreated());
        param.setLastmodified(new Date());
        param.setParameter(jsonCheck(param.getParameter()));
        logger.info("updateServicePackCatalog2 :: " + param.toString());

        //ServicepackCategory servicepackCategory = servicepackCategoryRepository.save(param);
        servicepackCategoryRepository.save(param);

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
     */
    public Map<String, Object> getHistory(String userid, String lang) {
        List<CatalogHistory> catalogHistories = catalogHistoryRepository.findAllByUserIdOrderByLastmodifiedDesc(userid);
        List<Object> resultHistory = new ArrayList<>();

        try {
            final int starterpacknum = catalogHistories.stream().filter(a -> a.getCatalogType().equals("starter")).findFirst().get().getCatalogNo();
            final StarterCategory starterCategory = starterCategoryRepository.findByNoAndLanguage(starterpacknum, lang);
            if (starterCategory != null) {
                resultHistory.add(starterCategory);
            }
        } catch (Exception e) {
            logger.info("starterpacknum none");
        }

        try {
            final int buildpacknum = catalogHistories.stream().filter(a -> a.getCatalogType().equals("buildPack")).findFirst().get().getCatalogNo();
            final BuildpackCategory buildpackCategory = buildpackCategoryRepository.findByNoAndLanguage(buildpacknum, lang);
            if (buildpackCategory != null) {
                resultHistory.add(buildpackCategory);
            }
        } catch (Exception e) {
            logger.info("buildpacknum none");
        }

        try {
            final int servicepacknum = catalogHistories.stream().filter(a -> a.getCatalogType().equals("servicePack")).findFirst().get().getCatalogNo();
            final ServicepackCategory servicepackCategory = servicepackCategoryRepository.findByNoAndLanguage(servicepacknum, lang);
            if (servicepackCategory != null) {
                resultHistory.add(servicepackCategory);
            }
        } catch (Exception e) {
            logger.info("servicepacknum none");
        }

        return new HashMap<String, Object>() {{
            put("list", resultHistory);
        }};
    }


    /**
     * 릴레이션에 속한 목록을 가져온다.
     */
    public Map<String, Object> getStarterRelation(int no, String isRequiredList) {
        int codeDetailNo = starterCategoryRepository.findByNo(no).getCodeDetailNo();
        String classificationCode = codeDetailRepository.findOne(codeDetailNo).getKey();
        List<StarterCategory> starterCategoryList = starterCategoryRepository.findAllByClassificationCode(classificationCode);

        List<Integer> starterCatalogNoList = new ArrayList<>();
        for(StarterCategory sc : starterCategoryList) {
            starterCatalogNoList.add(sc.getNo());
        }

        Map<Integer, List<ServicepackCategory>> servicepackCategoryMap = new HashMap<>();
        Map<Integer, BuildpackCategory> buildpackCategoryMap = new HashMap<>();

        for(Integer starterCatalogNo : starterCatalogNoList) {
            //servicepack 카테고리
            List<ServicepackCategory> servicepackCategories = new ArrayList<>();
            List<Integer> integerlist = starterServicePackRelationRepository.getServicePackCategoryNoList(starterCatalogNo);
            for (Integer number : integerlist) {
                ServicepackCategory servicepackCategory = servicepackCategoryRepository.findByNo(number);
                if (servicepackCategory != null) {
                    servicepackCategories.add(servicepackCategory);
                }
            }
            servicepackCategoryMap.put(starterCatalogNo, servicepackCategories);

            // builpack 카테고리
            StarterBuildpackRelation sbr = starterBuildPackRelationRepository.findFirstByStarterCatalogNo(starterCatalogNo);
            BuildpackCategory buildpackCategory = buildpackCategoryRepository.findByNo(sbr.getBuildpackCategoryNo());
            buildpackCategoryMap.put(starterCatalogNo, buildpackCategory);
        }

        return new HashMap<String, Object>() {{
            put("Starter", starterCategoryList);
            put("Servicepack", servicepackCategoryMap);
            put("Buildpack", buildpackCategoryMap);
        }};
    }

    public Map<String, Object> insertHistroy(CatalogHistory catalog) {
        catalogHistoryRepository.save(catalog);
        return new HashMap<String, Object>() {{
            put("Result", catalog);
        }};
    }

    public List<CatalogCc> getListRoutes() {
        return catalogCcRepository.findAll();
    }

    /**
     * 앱 샘플 존재 여부를 체크합니다.
     */
    public Map<String, Object> getAppSample(int no) {
        BuildpackCategory buildpackCategory = buildpackCategoryRepository.findOne(no);

        if (buildpackCategory == null) {
            return new HashMap<String, Object>() {{
                put("RESULT", Constants.RESULT_STATUS_FAIL);
            }};
        }
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


    /**
     * 앱 생성시 라우트 중복을 체크합니다.
     */
    public Map<String, Object> checkRoute(String host) {
        CatalogCc cc = catalogCcRepository.findByHost(host);
        if (cc != null) {
            return new HashMap<String, Object>() {{
                put("RESULT", Constants.RESULT_STATUS_FAIL);
            }};
        }
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }
}
