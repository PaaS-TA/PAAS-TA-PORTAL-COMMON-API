package org.openpaas.paasta.portal.common.api.domain.common;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String CF_AUTHORIZATION_HEADER_KEY = "cf-Authorization";



    RestTemplate restTemplate;


    private String apiUrl;

    @Value("${paasta.portal.api.authorization.base64}")
    private String base64Authorization;

    @Value("${paasta.portal.api.zuulUrl.cfapi}")
    private String cfApiUrl;

    @Value("${paasta.portal.api.zuulUrl.commonapi}")
    private String commonApiUrl;

    @Value("${paasta.portal.api.zuulUrl.storageapi}")
    private String storageApiUrl;

    @Value("${paasta.portal.storageapi.type}")
    private String storageApiType;


    /**
     * REST TEMPLATE 처리 - CfApi(PortalApi)
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param obj        the obj
     * @param reqToken   the req token
     * @return map map
     */
    @HystrixCommand(fallbackMethod = "procCfApiRestTemplate")
    public Map<String, Object> procCfApiRestTemplate(String reqUrl, HttpMethod httpMethod, Object obj, String reqToken) {

        String cfRequestURL = cfApiUrl + "/v2/" ;
        restTemplate = new RestTemplate();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);

        HttpEntity<Object> reqEntity = new HttpEntity<>(obj, reqHeaders);
        LOGGER.info("cfApiUrl::" + cfRequestURL + reqUrl);
        ResponseEntity<Map> resEntity = restTemplate.exchange(cfRequestURL + reqUrl, httpMethod, reqEntity, Map.class);
        Map<String, Object> resultMap = resEntity.getBody();

        if(resultMap != null){
            LOGGER.info("procCfApiRestTemplate reqUrl :: {} || resultMap :: {}", reqUrl, resultMap.toString());
        }
        return resultMap;
    }

    /**
     * REST TEMPLATE 처리 - StorageApi
     *
     * @param reqUrl     the req url
     * @param httpMethod the http method
     * @param bodyObject        the obj
     * @param reqToken   the req token
     * @return map map
     */
    @HystrixCommand(fallbackMethod = "procStorageApiRestTemplate")
    public <T> ResponseEntity<T> procStorageApiRestTemplate(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken, Class<T> resClazz) {
        restTemplate = new RestTemplate();
        
        // create url
        String storageRequestURL = storageApiUrl + "/v2/" + storageApiType + '/';
        if (null != reqUrl && false == "".equals( reqUrl ))
            storageRequestURL += reqUrl;
        
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);
        if (null == bodyObject)
            bodyObject = new LinkedMultiValueMap<>();
        HttpEntity<Object> reqEntity = new HttpEntity<>(bodyObject, reqHeaders);
        
        ResponseEntity<T> resEntity = restTemplate.exchange(storageRequestURL, httpMethod, reqEntity, resClazz);
        LOGGER.info("procRestStorageApiTemplate reqUrl :: {} || resultEntity type :: {}", storageRequestURL, resEntity.getHeaders().getContentType());
        LOGGER.info("procRestStorageApiTemplate response Http status code :: {}", resEntity.getStatusCode());
        return resEntity;
    }
    @HystrixCommand(fallbackMethod = "procStorageApiRestTemplateText")
    public ResponseEntity<String> procStorageApiRestTemplateText(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken) {
        return procStorageApiRestTemplate( reqUrl, httpMethod, bodyObject, reqToken, String.class );
    }
    @HystrixCommand(fallbackMethod = "procStorageApiRestTemplateBinary")
    public ResponseEntity<byte[]> procStorageApiRestTemplateBinary(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken) {
        return procStorageApiRestTemplate( reqUrl, httpMethod, bodyObject, reqToken, byte[].class );
    }
}