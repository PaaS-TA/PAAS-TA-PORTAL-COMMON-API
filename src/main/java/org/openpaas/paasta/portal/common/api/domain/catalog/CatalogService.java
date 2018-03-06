package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.Catalog;
import org.openpaas.paasta.portal.common.api.repository.portal.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SEJI on 2018-03-06.
 */
@Service
public class CatalogService {

    @Autowired
    JinqSource jinqSource;

    @Autowired
    CatalogRepository catalogRepository;


    /**
     * 서비스 카탈로그 목록을 조회한다.
     *
     * @param param Catalog(모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getServicePackCatalogList(Catalog param) {
        return new HashMap<String, Object>() {{
//            put("list", catalogMapper.getServicePackCatalogList(param));
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
