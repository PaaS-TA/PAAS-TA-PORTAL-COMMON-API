package org.openpaas.paasta.portal.common.api.domain.supportNotice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hrjin on 2018-02-01.
 */
@Controller
@RequestMapping("/notice")
public class SupportNoticeController {

    @Autowired
    private SupportNoticeService supportNoticeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public SupportNotice insertNotice(@RequestBody SupportNotice supportNotice){
        /*SupportNotice supportNotice = new SupportNotice();
        supportNotice.setTitle("aaa");
        supportNotice.setImportant("important");
        supportNotice.setClassification("bbb");
        supportNotice.setUseYn("Y");
        supportNotice.setContent("notice");
        supportNotice.setStartDate("20180201");
        supportNotice.setEndDate("20180310");*/
return null;
//        return supportNoticeService.insertNotice(supportNotice);
    }
}
