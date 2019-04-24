package org.openpaas.paasta.portal.common.api.domain.root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class RootController {

    //////////////////////////////////////////////////////////////////////
    //////   * CLOUD FOUNDRY CLIENT API VERSION 2                   //////
    //////   Document : http://apidocs.cloudfoundry.org             //////
    //////////////////////////////////////////////////////////////////////
    @Value("${datasource.uaa.driver-class-name}")
    String uaaDriverClassName;
    @Value("${datasource.uaa.url}")
    String uaaUrl;
    @Value("${datasource.uaa.username}")
    String uaaUsername;

    @Value("${datasource.portal.driver-class-name}")
    String portalDriverClassName;
    @Value("${datasource.portal.url}")
    String portalUrl;
    @Value("${datasource.portal.username}")
    String portalUsername;


    @Value("${datasource.cc.driver-class-name}")
    String ccDriverClassName;
    @Value("${datasource.cc.url}")
    String ccUrl;
    @Value("${datasource.cc.username}")
    String ccUsername;

    @RequestMapping(value = {"/", "/info", "/index"}, method = {RequestMethod.GET})
    @ResponseBody
    public Map index() throws Exception {

        Map map = new HashMap();

        Map uaa = new HashMap();
        uaa.put("driver-class-name", uaaDriverClassName);
        uaa.put("url", uaaUrl);
        uaa.put("username", uaaUsername);

        Map portal = new HashMap();
        portal.put("driver-class-name", portalDriverClassName);
        portal.put("url", portalUrl);
        portal.put("username", portalUsername);


        Map cc = new HashMap();
        cc.put("driver-class-name", ccDriverClassName);
        cc.put("url", ccUrl);
        cc.put("username", ccUsername);

        map.put("uaa", uaa);
        map.put("portal", portal);
        map.put("cc", cc);

        map.put("name", "PaaS-TA Common API");
        return map;
    }

}
