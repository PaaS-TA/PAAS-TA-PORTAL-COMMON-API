package org.openpaas.paasta.portal.common.api.domain.common;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.properties")
public class CommonServiceTest {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String CF_AUTHORIZATION_HEADER_KEY = "cf-Authorization";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    CommonService commonService;

    @Mock
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

    Map theReturnMap;

    @Mock
    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();

    }

    private void setTestData() {

        ReflectionTestUtils.setField(commonService, "base64Authorization", base64Authorization);
        ReflectionTestUtils.setField(commonService, "cfApiUrl", cfApiUrl);
        ReflectionTestUtils.setField(commonService, "commonApiUrl", commonApiUrl);
        ReflectionTestUtils.setField(commonService, "storageApiUrl", storageApiUrl);
        ReflectionTestUtils.setField(commonService, "storageApiType", storageApiType);


//
//        restTemplate = new RestTemplate();
//        mockServer = MockRestServiceServer.createServer(restTemplate);

        theReturnMap = new HashMap();
        theReturnMap.put("result", true);

    }

    @Test
    public void testProcCfApiRestTemplate() throws Exception {
//        mockServer.expect(ExpectedCount.manyTimes(), requestTo(UriComponentsBuilder.fromHttpUrl(cfApiUrl).build().toUri())).andExpect(method(HttpMethod.GET)).andRespond(withSuccess());
//
//        ResponseEntity<Map> resEntity = new ResponseEntity<Map>(HttpStatus.OK);
//        HttpHeaders reqHeaders = new HttpHeaders();
//        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
//        HttpEntity<Object> reqEntity = new HttpEntity<>(null, reqHeaders);
//        Mockito.when(restTemplate.exchange(cfApiUrl, HttpMethod.GET, reqEntity, Map.class)).thenReturn(resEntity);
//        Mockito.when(resEntity.getBody()).thenReturn(theReturnMap);
//
//        Map<String, Object> result = commonService.procCfApiRestTemplate("/info", HttpMethod.GET, null, "");
//
//        Assert.assertNotNull(result);
    }


//    public <T> ResponseEntity<T> procStorageApiRestTemplate(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken, Class<T> resClazz) {
//        restTemplate = new RestTemplate();
//
//        // create url
//        String storageRequestURL = storageApiUrl + "/v2/" + storageApiType + '/';
//        if (null != reqUrl && false == "".equals(reqUrl)) storageRequestURL += reqUrl;
//
//        HttpHeaders reqHeaders = new HttpHeaders();
//        reqHeaders.add(AUTHORIZATION_HEADER_KEY, base64Authorization);
//        if (null != reqToken && !"".equals(reqToken)) reqHeaders.add(CF_AUTHORIZATION_HEADER_KEY, reqToken);
//        if (null == bodyObject) bodyObject = new LinkedMultiValueMap<>();
//        HttpEntity<Object> reqEntity = new HttpEntity<>(bodyObject, reqHeaders);
//
//        ResponseEntity<T> resEntity = restTemplate.exchange(storageRequestURL, httpMethod, reqEntity, resClazz);
//        LOGGER.info("procRestStorageApiTemplate reqUrl :: {} || resultEntity type :: {}", storageRequestURL, resEntity.getHeaders().getContentType());
//        LOGGER.info("procRestStorageApiTemplate response Http status code :: {}", resEntity.getStatusCode());
//        return resEntity;
//    }
//
//    public ResponseEntity<String> procStorageApiRestTemplateText(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken) {
//        return procStorageApiRestTemplate(reqUrl, httpMethod, bodyObject, reqToken, String.class);
//    }
//
//    public ResponseEntity<byte[]> procStorageApiRestTemplateBinary(String reqUrl, HttpMethod httpMethod, Object bodyObject, String reqToken) {
//        return procStorageApiRestTemplate(reqUrl, httpMethod, bodyObject, reqToken, byte[].class);
//    }


}
