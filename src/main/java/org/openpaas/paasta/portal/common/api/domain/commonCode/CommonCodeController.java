package org.openpaas.paasta.portal.common.api.domain.commonCode;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-06.
 */
@RestController
@RequestMapping(value = {"/commonCode"})
public class CommonCodeController {
    private static final Logger logger = getLogger(CommonCodeController.class);

    @Autowired
    private CommonCodeService commonCodeService;

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeId String(아이디)
     * @return Map(자바클래스)
     */
    @GetMapping("/commonCode/{codeId}")
    public Map<String, Object> getCommonCode(@PathVariable("codeId") String codeId) {
        return commonCodeService.getCommonCodeById(codeId);
    }

//    /**
//     * 공통코드 목록을 조회한다.
//     *
//     * @param codeDetail,codeGroup  CodeDetail,CodeGroup (모델클래스)
//     * @return Map(자바클래스)
//     */
//    @PostMapping("/commonCode")
//      public Map<String, Object> getCommonCode(@RequestBody CodeDetail codeDetail, CodeGroup codeGroup) {
//        return commonCodeService.getCommonCode(codeDetail,codeGroup);
//    }
}
