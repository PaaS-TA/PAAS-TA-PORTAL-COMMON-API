package org.openpaas.paasta.portal.common.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
public class LanguageConfig {

    @Value("#{'${languageList}'.split(',')}")
    List<String> languageList;

    public List<String> getLanguageList() {
        return this.languageList;
    }
}
