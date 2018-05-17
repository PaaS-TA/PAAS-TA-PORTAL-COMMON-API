package org.openpaas.paasta.portal.common.api.domain.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openpaas.paasta.portal.common.api.config.EmailConfig;
import org.openpaas.paasta.portal.common.api.domain.service.ServiceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class EmailService {


    private static final Logger logger = getLogger(ServiceService.class);

    @Autowired
    EmailConfig emailConfig;

    public Map expiredEmail(String userId) {
        logger.info("expiredEmail ::: " + userId);
        Map map = new HashMap();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("loginpass.html").getFile());
            logger.debug("expiredEmail ::: " + file.getAbsolutePath());
            Document doc = Jsoup.parse(file, "UTF-8");
            Elements elementAhref = doc.select("a[href]");
            Elements elementSpan = doc.select("span");
            if (elementAhref.size() != 0) {
                elementAhref.get(0).attr("href", emailConfig.getAuth() + "/" + emailConfig.getContextUrl() + "?userId=" + userId + "&refreshToken=");
            }
            if (elementSpan.size() != 0) {
                elementSpan.get(0).childNode(0).attr("text", userId);
            }

            if (emailConfig.sendEmail("swmoon@bluedigm.com", doc.outerHtml())) {
                map.put("result", true);
                map.put("msg", "You have successfully completed the task.");
            } else {
                map.put("result", false);
                map.put("msg", "System error.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", e.getMessage());
        }
        return map;

    }
}