package org.openpaas.paasta.portal.common.api.domain.commonCode;


import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.config.LanguageConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeGroup;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeGroupRepository;
import org.postgresql.largeobject.BlobOutputStream;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        JinqStream<CodeDetail> streams = jinqSource.streamAllPortal(CodeDetail.class);

        if(lang != null) {
            streams = streams.where(c -> c.getLanguage().equals(lang));
        }

        int no = codeDetail.getNo();
//        int groupNo = codeDetail.getGroupNo();
//        logger.debug("groupNo --> " + groupNo);
        String groupId = codeDetail.getGroupId();
        String key = codeDetail.getKey();

        if (0 != no) {
            streams = streams.where(c -> c.getNo() == no);
        }

        if (null != key && !"".equals(key) && !"null".equals(key.toLowerCase())) {
            streams = streams.where(c -> c.getKey().equals(key));
        }
        if (null != groupId && !"".equals(groupId) && !"null".equals(groupId.toLowerCase())) {
//            streams = streams.where(c -> c.getGroupId().equals(groupId));
            if(codeGroupRepository.findByIdAndLanguage(groupId, lang) != null) {
                int groupNo = codeGroupRepository.findByIdAndLanguage(groupId, lang).getNo();
                streams = streams.where(c -> c.getGroupNo() == groupNo);
            }
        }

//        if (0 != groupNo) {
//            streams = streams.where(c -> c.getGroupNo() == groupNo);
//        }

        streams = streams.sortedBy(c -> c.getOrder());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>() {{
            put("no", x.getNo());
            put("key", x.getKey());
            put("value", x.getValue());
            put("summary", x.getSummary());
            put("groupNo", x.getGroupNo());
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
        String groupId = codeDetail.getGroupId();
        int groupNo = codeDetail.getGroupNo();

        if (null != key && !"".equals(key)) {
            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getKey().equals(key));
        }

        if (null != groupId && !"".equals(groupId)) {
//            int groupNo = codeGroupRepository.findByIdAndLanguage(groupId, codeDetail.getLanguage()).getNo();
            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getGroupNo() == groupNo);
        }

        codeDetailJinqStream = codeDetailJinqStream.sortedBy(c -> c.getOrder());

        List<Map<String, Object>> resultList = codeDetailJinqStream.map(x -> new HashMap<String, Object>() {{
            put("key", x.getKey());
            put("orgKey", x.getKey());
            put("value", x.getValue());
            put("summary", x.getSummary());
            put("groupNo", x.getGroupNo());

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
        String language = codeGroup.getLanguage();

        if (null != language && !"".equals(language)) {
            streams = streams.where(c -> c.getLanguage().equals(language));
        }
        if (null != id && !"".equals(id) && !"null".equals(id.toLowerCase())) {
            streams = streams.where(c -> c.getId().equals(id));
        }
        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getId().contains(searchKeyword));
        }

        streams = streams.sortedBy(c -> c.getCreated());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>() {{
            put("no", x.getNo());
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
    public Map<String,Object> getCodeDetail(String groupid, String lang) {
        int groupNo = codeGroupRepository.findByIdAndLanguage(groupid, lang).getNo();
//        List<CodeDetail> list = codeDetailRepository.findAllByGroupIdAndLanguage(groupid, lang);
        List<CodeDetail> list = codeDetailRepository.findAllByGroupNoAndLanguage(groupNo, lang);
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
        String language = codeGroup.getLanguage();

        // id가 같아도 언어가 다르면 등록 가능
        if(codeGroupRepository.findAllByIdAndLanguage(id, language).size() == 0) {
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
     * 공통 코드를 등록한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetail(CodeDetail codeDetail) {
        String resultStr;
//        int groupNo = codeDetail.getGroupNo();
        int groupNo = codeGroupRepository.findByIdAndLanguage(codeDetail.getGroupId(), codeDetail.getLanguage()).getNo();
        String key = codeDetail.getKey();

//        if(codeDetailRepository.findByGroupIdAndKey(groupId, key).size() == 0) {
        if(codeDetailRepository.findByGroupNoAndKey(groupNo, key).size() == 0) {
            try {
                int count = codeDetailRepository.countByGroupNo(groupNo);
                System.out.println(count);
                codeDetail.setOrder(count+1);
                codeDetail.setGroupNo(groupNo);

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
    public String updateCommonGroup(String id,CodeGroup codeGroup,String lang) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        if(codeGroupRepository.findByIdAndLanguage(id, lang) != null) {
            int no = codeGroupRepository.findByIdAndLanguage(id, lang).getNo();
            codeGroup.setNo(no);
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
        String result = Constants.RESULT_STATUS_SUCCESS;
//        CodeDetail update = codeDetailRepository.findOne(no);
        CodeDetail originCodeDetail = codeDetailRepository.findOne(no);
        int groupNo = codeDetail.getGroupNo();
        String groupId = codeDetail.getGroupId();
//        String key = codeDetail.getKey();
        String key = originCodeDetail.getKey();

        List<CodeDetail> codeDetailList = new ArrayList<>();

        if(codeDetail.getLanguageList().size() == 1) {
//            codeDetail.setLanguage(codeDetail.getLanguageList().get(0));
//            codeDetailList.add(codeDetail);
            originCodeDetail.setLanguage(codeDetail.getLanguageList().get(0));
	    codeDetailList.add(originCodeDetail);
        } else {
            List<CodeGroup> codeGroupList = codeGroupRepository.findById(groupId);

            for(CodeGroup cg : codeGroupList) {
                int cgNo = cg.getNo();
                codeDetailList.addAll(codeDetailRepository.findByGroupNoAndKey(cgNo, key));
            }
        }

        for(CodeDetail update : codeDetailList) {
            if (!update.getKey().equals(key) && codeDetailRepository.findByGroupNoAndKey(groupNo, key).size() != 0) {
                result = Constants.RESULT_STATUS_FAIL_DUPLICATED;
                break;
            } else {
                    update.setKey(codeDetail.getKey());
                    update.setUseYn(codeDetail.getUseYn());

                    if (no == update.getNo()) {
                        update.setValue(codeDetail.getValue());
                        update.setSummary(codeDetail.getSummary());
                    }
            }
        }

        try {
            codeDetailRepository.save(codeDetailList);
        } catch (Exception e) {
            e.printStackTrace();
            result = Constants.RESULT_STATUS_FAIL;
        }

        String finalResult = result;
        return new HashMap<String, Object>() {{
            put("RESULT", finalResult);
        }};
    }

    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param id
     * @param isCatalog
     * * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonGroup(String id, String lang, String isCatalog) {
        List<CodeGroup> codeGroupList = new ArrayList<>();
        String result = "";

        if(isCatalog.equals("no")) {
            codeGroupList.add(codeGroupRepository.findByIdAndLanguage(id, lang));
        } else {
            codeGroupList.addAll(codeGroupRepository.findById(id));
        }

        for(CodeGroup cg : codeGroupList) {
            List<CodeDetail> codeDetailList = codeDetailRepository.findByGroupNo(cg.getNo());
            codeDetailRepository.delete(codeDetailList);
        }

        try {
            codeGroupRepository.delete(codeGroupList);
            result = Constants.RESULT_STATUS_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            result = Constants.RESULT_STATUS_FAIL;
        }

        String finalResult = result;
        return new HashMap<String, Object>() {{
            put("RESULT", finalResult);
        }};
    }

    /**
     * 공통 코드을 삭제한다.
     *
     * @param no
     * @param isCatalog
     * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonDetail(int no, String isCatalog) {
        String result = "";

        if(isCatalog.equals("no")) {
            codeDetailRepository.delete(no);
            result = Constants.RESULT_STATUS_SUCCESS;
        } else {
            CodeDetail originCodeDetail = codeDetailRepository.findOne(no);
            int groupNo = originCodeDetail.getGroupNo();
            String groupId = codeGroupRepository.findOne(groupNo).getId();

            List<CodeGroup> codeGroupList = codeGroupRepository.findById(groupId);
            List<CodeDetail> codeDetailList = new ArrayList<>();

            for (CodeGroup codeGroup : codeGroupList) {
                CodeDetail cd = codeDetailRepository.findByGroupNoAndKey(codeGroup.getNo(), originCodeDetail.getKey()).get(0);
                codeDetailList.add(cd);
            }

            try {
                codeDetailRepository.delete(codeDetailList);
                result = Constants.RESULT_STATUS_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                result = Constants.RESULT_STATUS_FAIL;
            }
        }

        String finalResult = result;
        return new HashMap<String, Object>() {{
            put("RESULT", finalResult);
        }};
    }


}
