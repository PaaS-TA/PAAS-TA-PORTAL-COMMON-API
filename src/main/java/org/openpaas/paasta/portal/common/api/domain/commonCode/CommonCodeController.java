package org.openpaas.paasta.portal.common.api.domain.commonCode;

import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeGroup;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-06.
 */
@RestController
@RequestMapping(value = {"/code"})
public class CommonCodeController {
    private static final Logger logger = getLogger(CommonCodeController.class);

    @Autowired
    private CommonCodeService commonCodeService;


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail CodeDetail(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping("/codeDetail")
    public Map<String, Object> getCodeDetail(@ModelAttribute CodeDetail codeDetail) {
        return commonCodeService.getCommonCodeDetail(codeDetail);
    }


    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail CodeDetail(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping("/codeDetail/{key}")
    public Map<String, Object> getCodeDetail(@PathVariable("key") String key, @ModelAttribute CodeDetail codeDetail) {
        codeDetail.setKey(key);
        return commonCodeService.getCommonCodeDetail(codeDetail);
    }


    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param codeGroup CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping("/groupDetail")
    public Map<String, Object> getGroupDetail(@ModelAttribute CodeGroup codeGroup) {

        return null;
    }


    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param codeGroup CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping("/groupDetail/{id}")
    public Map<String, Object> getGroupDetail(@PathVariable("id") String key, @ModelAttribute CodeGroup codeGroup) {

        return null;
    }


    /**
     * 공통코드 및 그룹 목록을 조회한다.
     *
     * @param codeDetail,codeGroup CodeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @GetMapping("/commonCode")
    public Map<String, Object> getCommonCodeDetail(@ModelAttribute CodeDetail codeDetail, @ModelAttribute CodeGroup codeGroup) {
        return commonCodeService.getCommonCodeJoinGroup(codeDetail, codeGroup);
    }


    /**
     * 공통 코드 그룹을 등록한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping("/groupDetail")
    public Map<String, Object> insertDetailGroup(@ModelAttribute CodeGroup codeGroup) {
        return null;
    }


    /**
     * 공통 코드을 등록한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PostMapping("/codeDetail")
    public Map<String, Object> insertDetail(@ModelAttribute CodeDetail codeDetail) {
        return null;
    }


    /**
     * 공통 코드 그룹을 수정한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping("/groupDetail/{id}")
    public Map<String, Object> updateCommonGroup(@ModelAttribute CodeGroup codeGroup) {
        return null;
    }


    /**
     * 공통 코드을 수정한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @PutMapping("/codeDetail/{key}")
    public Map<String, Object> updateCommonDetail(@ModelAttribute CodeDetail codeDetail) {
        return null;
    }


    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    @DeleteMapping("/groupDetail/{id}")
    public Map<String, Object> deleteCommonGroup(@ModelAttribute CodeGroup codeGroup) {


        // TODO: 코드 그룹을 삭제할경우 그룹에 해당하는 CodeDetail 삭제하도록 개발하세요~


        return null;
    }


    /**
     * 공통 코드을 삭제한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    @DeleteMapping("/codeDetail/{key}")
    public Map<String, Object> deleteCommonDetail(@ModelAttribute CodeDetail codeDetail) {
        return null;
    }


}
