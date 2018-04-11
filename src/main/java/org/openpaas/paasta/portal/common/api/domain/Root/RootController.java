package org.openpaas.paasta.portal.common.api.domain.Root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RootController {


    @RequestMapping(value = "/", method = {RequestMethod.OPTIONS, RequestMethod.GET})
    @ResponseBody
    public String root() throws Exception {

        return "PortalCommonApi Started";
    }


    @RequestMapping(value = "/index", method = {RequestMethod.OPTIONS, RequestMethod.GET})
    @ResponseBody
    public String index() throws Exception {

        return "PortalCommonApi Started";
    }

}
