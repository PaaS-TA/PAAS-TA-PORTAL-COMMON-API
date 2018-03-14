package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.jinq.jpa.JPQL;
import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.openpaas.paasta.portal.common.api.repository.portal.CatalogRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.ServicepackCatagoryRepository;
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
    PortalConfig portalConfig;

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    ServicepackCatagoryRepository servicepackCatagoryRepository;

    @Autowired
    JinqSource jinqSource;

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

        if(null != searchKeyword && !"".equals(searchKeyword)) {
            if(null != searchTypeColumn && !"".equals(searchTypeColumn)) {
                if(searchTypeColumn.equals("name")) {
                    streams = streams.where(c -> JPQL.like(c.getName(), searchTypeColumn)); //AND LOWER("name") LIKE concat('%', #{searchKeyword},'%')
                } else if(searchTypeColumn.equals("summary")) {
                    streams = streams.where(c -> JPQL.like(c.getSummary(), searchTypeColumn));      //AND LOWER(summary) LIKE concat('%', #{searchKeyword},'%')
                } else if(searchTypeColumn.equals("ALL")) {
                    streams = streams.where(c -> JPQL.like(c.getName(), searchTypeColumn) || JPQL.like(c.getSummary(), searchTypeColumn));      //AND (LOWER("name") LIKE concat('%', #{searchKeyword},'%') OR LOWER(summary) LIKE concat('%', #{searchKeyword},'%'))
                }
            }
        }

        if(null != searchTypeUseYn && !"".equals(searchTypeUseYn)) {
            if(searchTypeUseYn.equals("Y") || searchTypeUseYn.equals("N")) {
                streams = streams.where(c -> c.getUseYn() == searchTypeUseYn);      //AND use_yn = #{searchTypeUseYn}
            }
        }
        streams = streams.sortedDescendingBy(c -> c.getNo());

        List<ServicepackCategory> menuList = streams.toList();

//        streams.forEach(
//                c -> System.out.println(c.getNo() + " " + c.getName() )
//        );

        return new HashMap<String, Object>() {{
            put("item", menuList);
        }};
    }


    /**
     * 앱 개발환경 카탈로그 개수를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */
    public Map<String, Object> getBuildPackCatalogCount(Catalog param) throws Exception {

        int CatalogBuildCount =(int)catalogRepository.count();

        if (CatalogBuildCount > 0) {
//            commonService.getCustomSendError(res, HttpStatus.CONFLICT, "common.info.result.fail.duplicated");
            System.out.print("getBuildPackCatalogCountSendError");
        }
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};

    }

    /**
     * 서비스 카탈로그 개수를 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     * @throws Exception Exception(자바클래스)
     */

    public Map<String,Object> getServicePackCatalogCount(Catalog param) {
        int CatalogCount =(int)catalogRepository.count();

        if (CatalogCount > 0) {
//            commonService.getCustomSendError(res, HttpStatus.CONFLICT, "common.info.result.fail.duplicated");
            System.out.print("getServicePackCatalogSendError");
        }
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};

    }


}
