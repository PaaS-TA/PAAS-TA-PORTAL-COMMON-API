package org.openpaas.paasta.portal.common.api.domain.commonCode;


import ch.qos.logback.core.CoreConstants;
import org.aspectj.apache.bcel.classfile.Code;
import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeGroup;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeGroupRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    CodeGroupRepository codeGroupRepository;

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail CodeDetail(검색조건)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getCommonCodeDetail(CodeDetail codeDetail, String lang) {
        JinqStream<CodeDetail> streams = jinqSource.streamAllPortal(CodeDetail.class).where(c -> c.getLanguage().equals(lang));
        int no = codeDetail.getNo();
        String groudId = codeDetail.getGroupId();
        String key = codeDetail.getKey();
        if (0 != no) {
            streams = streams.where(c -> c.getNo() == no);
        }

        if (null != key && !"".equals(key) && !"null".equals(key.toLowerCase())) {
            streams = streams.where(c -> c.getKey().equals(key));
        }
        if (null != groudId && !"".equals(groudId) && !"null".equals(groudId.toLowerCase())) {
            streams = streams.where(c -> c.getGroupId().equals(groudId));
        }

        streams = streams.sortedBy(c -> c.getOrder());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>() {{
            put("no", x.getNo());
            put("key", x.getKey());
            put("value", x.getValue());
            put("summary", x.getSummary());
            put("groupId", x.getGroupId());
            put("useYn", x.getUseYn());
            put("order", x.getOrder());
            put("userId", x.getUserId());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};

    }

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail,codeGroup CodeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getCommonCodeJoinGroup(CodeDetail codeDetail, CodeGroup codeGroup) {

        //공통코드 상세 조회를 한다. ::  공통코드 상세 개수를 조회한다.
        JinqStream<CodeDetail> codeDetailJinqStream = jinqSource.streamAllPortal(CodeDetail.class);

        String key = codeDetail.getKey();
        String GroupId = codeDetail.getGroupId();

        if (null != key && !"".equals(key)) {
            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getKey().equals(key));
        }

        if (null != GroupId && !"".equals(GroupId)) {
            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getGroupId().equals(GroupId));
        }

        codeDetailJinqStream = codeDetailJinqStream.sortedBy(c -> c.getOrder());

        List<Map<String, Object>> resultList = codeDetailJinqStream.map(x -> new HashMap<String, Object>() {{
            put("key", x.getKey());
            put("orgKey", x.getKey());
            put("value", x.getValue());
            put("summary", x.getSummary());
            put("groupId", x.getGroupId());

            put("useYn", x.getUseYn());
            put("order", x.getOrder());
            put("created", x.getCreated());
            put("lastModified", x.getLastmodified());
            put("userId", x.getUserId());

            put("pageNo", x.getPageNo());
            put("pageSize", x.getPageSize());
            put("procType", x.getProcType());

        }}).collect(Collectors.toList());


        return (Map<String, Object>) resultList;
    }

    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param codeGroup CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getGroupDetail(CodeGroup codeGroup) {
        JinqStream<CodeGroup> streams = jinqSource.streamAllPortal(CodeGroup.class);
        String id = codeGroup.getId();
        String searchKeyword = codeGroup.getSearchKeyword();

        if (null != id && !"".equals(id) && !"null".equals(id.toLowerCase())) {
            streams = streams.where(c -> c.getId().equals(id));
        }
        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getId().contains(searchKeyword));
        }

        streams = streams.sortedBy(c -> c.getCreated());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>() {{
            put("id", x.getId());
            put("orgId", x.getOrgId());
            put("name", x.getName());
            put("created", x.getCreated());
            put("lastModified", x.getLastmodified());
            put("userId", x.getUserId());
            put("pageNo", x.getPageNo());
            put("pageSize", x.getPageSize());
            put("procType", x.getProcType());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};
    }

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param groupid groupid(아이디)
     * @return Map(자바클래스)
     */
    public Map<String,Object> getCodeDetail(String groupid) {
        List<CodeDetail> list = codeDetailRepository.findAllByGroupId(groupid);
        list.sort(Comparator.comparingInt(CodeDetail::getValue2));
        return new HashMap<String, Object>() {{
            put("list", list);
        }};
    }




    /**
     * 공통 코드 그룹을 등록한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetailGroup(CodeGroup codeGroup){
        String resultStr;
        String id = codeGroup.getId();

        if(codeGroupRepository.findById(id).size() == 0) {
            try {
                //throw new Exception();
                codeGroupRepository.save(codeGroup);
            } catch (Exception e){
                e.printStackTrace();
                return new HashMap<String, Object>() {{
                    put("RESULT", Constants.RESULT_STATUS_FAIL);
                }};
            }
            resultStr = Constants.RESULT_STATUS_SUCCESS;
        } else {
            resultStr = Constants.RESULT_STATUS_FAIL_DUPLICATED;
        }


        return new HashMap<String, Object>() {{
            put("RESULT", resultStr);
        }};
    }


    /**
     * 공통 코드을 등록한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetail(CodeDetail codeDetail) {
        String resultStr;
        String groupId = codeDetail.getGroupId();
        String key = codeDetail.getKey();

        if(codeDetailRepository.findByGroupIdAndKey(groupId, key).size() == 0) {
            try {
                int count = codeDetailRepository.countByGroupId(codeDetail.getGroupId());
                System.out.println(count);
                codeDetail.setOrder(count+1);

                codeDetailRepository.save(codeDetail);
            } catch (Exception e){
                e.printStackTrace();
                return new HashMap<String, Object>() {{
                    put("RESULT", Constants.RESULT_STATUS_FAIL);
                }};
            }

            resultStr = Constants.RESULT_STATUS_SUCCESS;
        } else {
            resultStr = Constants.RESULT_STATUS_FAIL_DUPLICATED;
        }


        return new HashMap<String, Object>() {{
            put("RESULT", resultStr);
        }};
    }

    /**
     * 공통 코드 그룹을 수정한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public String updateCommonGroup(String id,CodeGroup codeGroup) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        if(codeGroupRepository.findById(id) != null) {
            codeGroup.setId(codeGroup.getId());
            codeGroup.setName(codeGroup.getName());
            codeGroup.setUserId(codeGroup.getUserId());
            codeGroupRepository.save(codeGroup);
        }else {
            resultStr = Constants.RESULT_STATUS_FAIL;
        }
        return resultStr;
    }

    /**
     * 공통 코드을 수정한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> updateCommonDetail(int no, CodeDetail codeDetail) {
        String resultStr;

        CodeDetail update = codeDetailRepository.findOne(no);
        String groupId = codeDetail.getGroupId();
        String key = codeDetail.getKey();

        if (!update.getKey().equals(key) && codeDetailRepository.findByGroupIdAndKey(groupId, key).size() != 0) {
            resultStr = Constants.RESULT_STATUS_FAIL_DUPLICATED;
        } else {
            try {
                update.setKey(codeDetail.getKey());
                update.setValue(codeDetail.getValue());
                update.setSummary(codeDetail.getSummary());
                update.setUseYn(codeDetail.getUseYn());

                codeDetailRepository.save(update);
            } catch (Exception e) {
                e.printStackTrace();
                return new HashMap<String, Object>() {{
                    put("RESULT", Constants.RESULT_STATUS_FAIL);
                }};
            }

            resultStr = Constants.RESULT_STATUS_SUCCESS;
        }

        return new HashMap<String, Object>() {{
            put("RESULT", resultStr);
        }};
    }

    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param id
     * * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonGroup(String id) {
        List<CodeDetail> codeDetailList =  codeDetailRepository.findByGroupId(id);
        for (CodeDetail codeDetail: codeDetailList) {
            codeDetailRepository.delete(codeDetail.getNo());
        }

        CodeGroup codeGroup = new CodeGroup();
        codeGroup.setId(id);
        codeGroupRepository.delete(codeGroup);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 공통 코드을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonDetail(int no) {
        codeDetailRepository.delete(no);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


}
