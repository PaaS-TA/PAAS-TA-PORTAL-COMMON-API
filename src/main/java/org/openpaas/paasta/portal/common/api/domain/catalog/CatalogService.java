package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.openpaas.paasta.portal.common.api.repository.portal.BuildpackCategoryRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.ServicepackCategoryRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.StarterCategoryRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    JinqSource jinqSource;

    /**
     * 앱 템플릿명 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getStarterNamesList(Catalog param) {
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);
        System.out.println("getBuildPackCatalogList in~~");

        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();
        String searchTypeColumn = param.getSearchTypeColumn();
        String searchTypeUseYn = param.getSearchTypeUseYn();

        System.out.println("no : " + no);
        System.out.println("searchKeyword : " + searchKeyword);
        System.out.println("searchTypeColumn : " + searchTypeColumn);
        System.out.println("searchTypeUseYn : " + searchTypeUseYn);

        if(no != 0) {
            streams = streams.where(c -> c.getNo() == no);
        }

        if(null != searchTypeUseYn && !"".equals(searchTypeUseYn)) {
            if(searchTypeUseYn.equals("Y") || searchTypeUseYn.equals("N")) {
                streams = streams.where(c -> c.getUseYn() == searchTypeUseYn);      //AND use_yn = #{searchTypeUseYn}
            }
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
        System.out.println("getBuildPackCatalogList in~~");

        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();
        String searchTypeColumn = param.getSearchTypeColumn();
        String searchTypeUseYn = param.getSearchTypeUseYn();

        System.out.println("no : " + no);
        System.out.println("searchKeyword : " + searchKeyword);
        System.out.println("searchTypeColumn : " + searchTypeColumn);
        System.out.println("searchTypeUseYn : " + searchTypeUseYn);

        if(no != 0) {
            streams = streams.where(c -> c.getNo() == no);
        }

        if(null != searchTypeUseYn && !"".equals(searchTypeUseYn)) {
            if(searchTypeUseYn.equals("Y") || searchTypeUseYn.equals("N")) {
                streams = streams.where(c -> c.getUseYn() == searchTypeUseYn);      //AND use_yn = #{searchTypeUseYn}
            }
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
        System.out.println("getServicePackCatalogList in~~");

        int no = param.getNo();
        String searchKeyword = param.getSearchKeyword();
        String searchTypeColumn = param.getSearchTypeColumn();
        String searchTypeUseYn = param.getSearchTypeUseYn();

        System.out.println("no : " + no);
        System.out.println("searchKeyword : " + searchKeyword);
        System.out.println("searchTypeColumn : " + searchTypeColumn);
        System.out.println("searchTypeUseYn : " + searchTypeUseYn);

        if(no != 0) {
            streams = streams.where(c -> c.getNo() == no);
        }

        if(null != searchTypeUseYn && !"".equals(searchTypeUseYn)) {
            if(searchTypeUseYn.equals("Y") || searchTypeUseYn.equals("N")) {
                streams = streams.where(c -> c.getUseYn() == searchTypeUseYn);      //AND use_yn = #{searchTypeUseYn}
            }
        }
        streams = streams.sortedDescendingBy(c -> c.getNo());

        List<ServicepackCategory> servicePackCatalogList = streams.toList();

        return new HashMap<String, Object>() {{
            put("list", servicePackCatalogList);
        }};
    }

    /**
     * 앱 템플릿 카탈로그 개수를 조회한다.
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getStarterCatalogCount() {

        int buildPackCnt =(int)starterCategoryRepository.count();
        System.out.println(buildPackCnt);
        return buildPackCnt;
    }


    /**
     * 앱 개발환경 카탈로그 개수를 조회한다.
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getBuildPackCatalogCount() {

        int buildPackCnt =(int)buildpackCategoryRepository.count();
        System.out.println(buildPackCnt);
        return buildPackCnt;
    }


    /**
     * 서비스 카탈로그 개수를 조회한다.
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public int getServicePackCatalogCount() {

        int servicePackCnt =(int)servicepackCategoryRepository.count();
        System.out.println(servicePackCnt);
        return servicePackCnt;
    }


}
