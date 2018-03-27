package org.openpaas.paasta.portal.common.api.domain.commonCode;

import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeDetailRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-26.
 */
@Service
public class CommonCodeService {
    private final Logger logger = getLogger(this.getClass());

    @Autowired
    JinqSource jinqSource;

    @Autowired
    CodeDetailRepository codeDetailRepository;


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeId String(아이디)
     * @return Map(자바클래스)
     */

    public Map<String, Object> getCommonCodeById(String codeId) {
        JinqStream<CodeDetail> streams = jinqSource.streamAllPortal(CodeDetail.class);

        if(null != codeId) {
            streams = streams.where(c -> c.getGroupId() == codeId);
        }
        streams = streams.sortedBy(c -> c.getOrder());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>(){{
            put("key", x.getKey());
            put("value", x.getValue());
            put("summary", x.getSummary());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};

    }

//    /**
//     * 공통코드 목록을 조회한다.
//     *
//     * @param codeDetail,codeGroup  CodeDetail,CodeGroup (모델클래스)
//     * @return Map(자바클래스)
//     */
//    public Map<String, Object> getCommonCode(CodeDetail codeDetail, CodeGroup codeGroup) {
//
//        //공통코드 상세 조회를 한다. ::  공통코드 상세 개수를 조회한다.
//        JinqStream<CodeDetail> codeDetailJinqStream = jinqSource.streamAllPortal(CodeDetail.class);
//        JinqStream<CodeGroup> codeGroupJinqStream = jinqSource.streamAllPortal(CodeGroup.class);
//
//        String key = codeDetail.getKey();
//        String GroupId = codeDetail.getGroupId();
//
//        if(null != key && !"".equals(key)) {
//            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getKey().equals(key));
//        }
//
//        if(null != GroupId && !"".equals(GroupId)) {
//            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getGroupId().equals(GroupId));
//        }
//
//        codeDetailJinqStream = codeDetailJinqStream.sortedBy(c -> c.getOrder());
//
//        List<Map<String, Object>> resultList = codeDetailJinqStream.map(x -> new HashMap<String, Object>(){{
//            put("key", x.getKey());
//            put("orgKey", x.getKey());
//            put("value", x.getValue());
//            put("summary", x.getSummary());
//            put("groupId", x.getGroupId());
//
//            put("useYn", x.getUseYn());
//            put("order", x.getOrder());
//            put("created", x.getCreated());
//            put("lastModified", x.getLastmodified());
//            put("userId", x.getUserId());
//
//            put("pageNo", x.getPageNo());
//            put("pageSize", x.getPageSize());
//            put("procType", x.getProcType());
//
//        }}).collect(Collectors.toList());
//
//
//        return (Map<String, Object>) resultList;
//    }


}
