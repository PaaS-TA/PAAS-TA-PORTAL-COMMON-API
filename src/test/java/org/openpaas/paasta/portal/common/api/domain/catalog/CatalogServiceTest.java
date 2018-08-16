package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.entity.cc.CatalogCc;
import org.openpaas.paasta.portal.common.api.entity.portal.BuildpackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.CatalogHistory;
import org.openpaas.paasta.portal.common.api.entity.portal.ServicepackCategory;
import org.openpaas.paasta.portal.common.api.entity.portal.StarterCategory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * Created by indra on 2018-06-27.
 */
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogServiceTest {

    @Mock
    CatalogService catalogService;

    Map getStarterCatalogResultMap;
    StarterCategory starterCategory1;

    Map getStarterNamesListResultMap;
    StarterCategory starterCategoryParam2;
    StarterCategory starterCategory2;
    List<StarterCategory> starterCategoryList2;

    Map getBuildPackCatalogListResultMap;
    BuildpackCategory buildpackCategoryParam3;
    BuildpackCategory buildpackCategory3;
    List<BuildpackCategory> buildpackCategoryList3;

    Map getPacksResultMap;
    StarterCategory starterCategory4;
    BuildpackCategory buildpackCategory4;
    List<StarterCategory> starterCategoryList4;
    List<BuildpackCategory> buildpackCategoryList4;

    Map getServicePackCatalogListResultMap;
    ServicepackCategory servicepackCategoryParam5;
    ServicepackCategory servicepackCategory5;
    List<ServicepackCategory> servicepackCategoryList5;

    int getStarterCatalogCountResult;
    StarterCategory starterCategoryParam6;

    int getBuildPackCatalogCountResult;
    BuildpackCategory buildpackCategoryParam7;

    int getServicePackCatalogCountResult;
    ServicepackCategory servicepackCategoryParam8;

    Map insertStarterCatalogResultMap;
    StarterCategory starterCategoryParam9;

    Map insertBuildPackCatalogResultMap;
    BuildpackCategory buildpackCategoryParam10;

    Map insertServicePackCatalogResultMap;
    ServicepackCategory servicepackCategoryParam11;

    Map updateStarterCatalogResultMap;
    StarterCategory starterCategoryParam12;

    Map updateBuildPackCatalogResultMap;
    BuildpackCategory buildpackCategoryParam13;

    Map updateServicePackCatalogResultMap;
    ServicepackCategory servicepackCategoryParam14;

    Map deleteStarterCatalogResultMap;

    Map deleteBuildPackCatalogResultMap;

    Map deleteServicePackCatalogResultMap;

    Map getHistoryResultMap;
    List<Object> resultHistoryList18;
    StarterCategory starterCategory18;
    BuildpackCategory buildpackCategory18;
    ServicepackCategory servicepackCategory18;

    Map getStarterRelationResultMap;
    StarterCategory starterCategory19;
    List<ServicepackCategory> servicepackCategoryList19;
    ServicepackCategory servicepackCategory19;
    BuildpackCategory buildpackCategory19;

    Map insertHistroyResultMap;
    CatalogHistory catalogHistoryParam20;

    List<CatalogCc> catalogCcList20;
    CatalogCc catalogCc20;

    Map checkRouteResultMap;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();
    }

    private void setTestData() {
        //testGetStarterCatalog
        List<Integer> ssrIntList = new ArrayList<>();

        getStarterCatalogResultMap = new HashedMap();
        starterCategory1 = new StarterCategory();
        starterCategory1.setNo(1);
        starterCategory1.setName("Java + Mysql");
        starterCategory1.setClassification("starter_main");
        starterCategory1.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory1.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory1.setThumbImgName("test.png");
        starterCategory1.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory1.setUseYn("Y");
        starterCategory1.setUserId("admin");
        starterCategory1.setCreated(null);
        starterCategory1.setLastmodified(null);
        starterCategory1.setTagsParam("{\"paasta\":\"colors6\"}");
        ssrIntList.add(1);
        starterCategory1.setServicePackCategoryNoList(ssrIntList);
        starterCategory1.setBuildPackCategoryNo(1);
        getStarterCatalogResultMap.put("info", starterCategory1);

        //testGetStarterNamesList
        starterCategoryParam2 = new StarterCategory();
        starterCategoryParam2.setNo(1);
        starterCategoryParam2.setSearchKeyword("test");

        getStarterNamesListResultMap = new HashedMap();
        starterCategoryList2 = new ArrayList<StarterCategory>();
        starterCategory2 = new StarterCategory();
        starterCategory2.setNo(1);
        starterCategory2.setName("Java + Mysql");
        starterCategory2.setClassification("starter_main");
        starterCategory2.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory2.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory2.setThumbImgName("test.png");
        starterCategory2.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory2.setUseYn("Y");
        starterCategory2.setUserId("admin");
        starterCategory2.setCreated(null);
        starterCategory2.setLastmodified(null);
        starterCategory2.setTagsParam("{\"paasta\":\"colors6\"}");
        starterCategoryList2.add(starterCategory2);
        getStarterNamesListResultMap.put("list", starterCategoryList2);

        //testGetBuildPackCatalogList
        buildpackCategoryParam3 = new BuildpackCategory();
        buildpackCategoryParam3.setNo(1);
        buildpackCategoryParam3.setSearchKeyword("test");

        getBuildPackCatalogListResultMap = new HashMap();
        buildpackCategoryList3 = new ArrayList<BuildpackCategory>();
        buildpackCategory3 = new BuildpackCategory();
        buildpackCategory3.setNo(1);
        buildpackCategory3.setName("Java8 온라인 앱 개발환경");
        buildpackCategory3.setClassification("buildpack_system");
        buildpackCategory3.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategory3.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategory3.setBuildPackName("java_buildpack");
        buildpackCategory3.setThumbImgName("java.jpg");
        buildpackCategory3.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategory3.setUseYn("Y");
        buildpackCategory3.setAppSampleFileName("sample-spring.war");
        buildpackCategory3.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategory3.setAppSampleFileSize("9478983");
        buildpackCategory3.setUserId("admin");
        buildpackCategory3.setCreated(null);
        buildpackCategory3.setLastmodified(null);
        buildpackCategory3.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategory3.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        buildpackCategoryList3.add(buildpackCategory3);
        getBuildPackCatalogListResultMap.put("list", buildpackCategoryList3);

        //testGetPacks
        getPacksResultMap = new HashMap();
        starterCategoryList4 = new ArrayList<StarterCategory>();
        starterCategory4 = new StarterCategory();
        starterCategory4.setNo(1);
        starterCategory4.setName("Java + Mysql");
        starterCategory4.setClassification("starter_main");
        starterCategory4.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory4.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory4.setThumbImgName("test.png");
        starterCategory4.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory4.setUseYn("Y");
        starterCategory4.setUserId("admin");
        starterCategory4.setCreated(null);
        starterCategory4.setLastmodified(null);
        starterCategory4.setTagsParam("{\"paasta\":\"colors6\"}");
        starterCategoryList4.add(starterCategory4);

        buildpackCategoryList4 = new ArrayList<BuildpackCategory>();
        buildpackCategory4 = new BuildpackCategory();
        buildpackCategory4.setNo(1);
        buildpackCategory4.setName("Java8 온라인 앱 개발환경");
        buildpackCategory4.setClassification("buildpack_system");
        buildpackCategory4.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategory4.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategory4.setBuildPackName("java_buildpack");
        buildpackCategory4.setThumbImgName("java.jpg");
        buildpackCategory4.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategory4.setUseYn("Y");
        buildpackCategory4.setAppSampleFileName("sample-spring.war");
        buildpackCategory4.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategory4.setAppSampleFileSize("9478983");
        buildpackCategory4.setUserId("admin");
        buildpackCategory4.setCreated(null);
        buildpackCategory4.setLastmodified(null);
        buildpackCategory4.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategory4.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        buildpackCategoryList4.add(buildpackCategory4);

        getPacksResultMap.put("TemplateList", starterCategoryList4);
        getPacksResultMap.put("BuildPackList", buildpackCategoryList4);

        //testGetServicePackCatalogList
        getServicePackCatalogListResultMap = new HashMap();
        servicepackCategoryParam5 = new ServicepackCategory();
        servicepackCategoryParam5.setNo(1);
        servicepackCategoryParam5.setSearchKeyword("test");

        servicepackCategoryList5 = new ArrayList<ServicepackCategory>();
        servicepackCategory5 = new ServicepackCategory();
        servicepackCategory5.setNo(1);
        servicepackCategory5.setName("Redis 서비스");
        servicepackCategory5.setClassification("service_nosql");
        servicepackCategory5.setSummary("Redis NoSQL 및 In memory 서비스");
        servicepackCategory5.setDescription("<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n");
        servicepackCategory5.setServicePackName("redis");
        servicepackCategory5.setThumbImgName("redis.jpg");
        servicepackCategory5.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn");
        servicepackCategory5.setUseYn("Y");
        servicepackCategory5.setUserId("admin");
        servicepackCategory5.setCreated(null);
        servicepackCategory5.setLastmodified(null);
        servicepackCategory5.setParameter("{\"test\":\"text\"}");
        servicepackCategory5.setAppBindParameter("{\"sdfsdfsdfsdf\":\"text\"}");
        servicepackCategory5.setDashboardUseYn("N");
        servicepackCategory5.setAppBindYn("Y");
        servicepackCategory5.setDocFileUrl("https://redis.io/");
        servicepackCategory5.setTagsParam("{\"paasta\":\"colors6\",\"pay\":\"colors2\"}");
        servicepackCategoryList5.add(servicepackCategory5);
        getServicePackCatalogListResultMap.put("list", servicepackCategoryList5);

        //testGetStarterCatalogCount
        getStarterCatalogCountResult = 1;
        starterCategoryParam6 = new StarterCategory();
        starterCategoryParam6.setName("test");

        //testGetBuildPackCatalogCount
        getBuildPackCatalogCountResult = 1;
        buildpackCategoryParam7 = new BuildpackCategory();
        buildpackCategoryParam7.setName("test");

        //testGetServicePackCatalogCount
        getServicePackCatalogCountResult = 1;
        servicepackCategoryParam8 = new ServicepackCategory();
        servicepackCategoryParam8.setName("test");

        //testInsertStarterCatalog
        insertStarterCatalogResultMap = new HashMap();
        starterCategoryParam9 = new StarterCategory();
        starterCategoryParam9.setNo(1);
        starterCategoryParam9.setName("Java + Mysql");
        starterCategoryParam9.setClassification("starter_main");
        starterCategoryParam9.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategoryParam9.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategoryParam9.setThumbImgName("test.png");
        starterCategoryParam9.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategoryParam9.setUseYn("Y");
        starterCategoryParam9.setUserId("admin");
        starterCategoryParam9.setCreated(null);
        starterCategoryParam9.setLastmodified(null);
        starterCategoryParam9.setTagsParam("{\"paasta\":\"colors6\"}");
        insertStarterCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testInsertBuildPackCatalog
        insertBuildPackCatalogResultMap = new HashMap();
        buildpackCategoryParam10 = new BuildpackCategory();
        buildpackCategoryParam10.setNo(1);
        buildpackCategoryParam10.setName("Java8 온라인 앱 개발환경");
        buildpackCategoryParam10.setClassification("buildpack_system");
        buildpackCategoryParam10.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategoryParam10.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategoryParam10.setBuildPackName("java_buildpack");
        buildpackCategoryParam10.setThumbImgName("java.jpg");
        buildpackCategoryParam10.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategoryParam10.setUseYn("Y");
        buildpackCategoryParam10.setAppSampleFileName("sample-spring.war");
        buildpackCategoryParam10.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategoryParam10.setAppSampleFileSize("9478983");
        buildpackCategoryParam10.setUserId("admin");
        buildpackCategoryParam10.setCreated(null);
        buildpackCategoryParam10.setLastmodified(null);
        buildpackCategoryParam10.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategoryParam10.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        insertBuildPackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testInsertServicePackCatalog
        insertServicePackCatalogResultMap = new HashMap();
        servicepackCategoryParam11 = new ServicepackCategory();
        servicepackCategoryParam11.setNo(1);
        servicepackCategoryParam11.setName("Redis 서비스");
        servicepackCategoryParam11.setClassification("service_nosql");
        servicepackCategoryParam11.setSummary("Redis NoSQL 및 In memory 서비스");
        servicepackCategoryParam11.setDescription("<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n");
        servicepackCategoryParam11.setServicePackName("redis");
        servicepackCategoryParam11.setThumbImgName("redis.jpg");
        servicepackCategoryParam11.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn");
        servicepackCategoryParam11.setUseYn("Y");
        servicepackCategoryParam11.setUserId("admin");
        servicepackCategoryParam11.setCreated(null);
        servicepackCategoryParam11.setLastmodified(null);
        servicepackCategoryParam11.setParameter("{\"test\":\"text\"}");
        servicepackCategoryParam11.setAppBindParameter("{\"sdfsdfsdfsdf\":\"text\"}");
        servicepackCategoryParam11.setDashboardUseYn("N");
        servicepackCategoryParam11.setAppBindYn("Y");
        servicepackCategoryParam11.setDocFileUrl("https://redis.io/");
        servicepackCategoryParam11.setTagsParam("{\"paasta\":\"colors6\",\"pay\":\"colors2\"}");
        insertServicePackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testUpdateStarterCatalog
        updateStarterCatalogResultMap = new HashMap();
        starterCategoryParam12 = new StarterCategory();
        starterCategoryParam12.setNo(1);
        starterCategoryParam12.setName("Java + Mysql");
        starterCategoryParam12.setClassification("starter_main");
        starterCategoryParam12.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategoryParam12.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategoryParam12.setThumbImgName("test.png");
        starterCategoryParam12.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategoryParam12.setUseYn("Y");
        starterCategoryParam12.setUserId("admin");
        starterCategoryParam12.setCreated(null);
        starterCategoryParam12.setLastmodified(null);
        starterCategoryParam12.setTagsParam("{\"paasta\":\"colors6\"}");
        updateStarterCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testUpdateBuildPackCatalog
        updateBuildPackCatalogResultMap = new HashMap();
        buildpackCategoryParam13 = new BuildpackCategory();
        buildpackCategoryParam13.setNo(1);
        buildpackCategoryParam13.setName("Java8 온라인 앱 개발환경");
        buildpackCategoryParam13.setClassification("buildpack_system");
        buildpackCategoryParam13.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategoryParam13.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategoryParam13.setBuildPackName("java_buildpack");
        buildpackCategoryParam13.setThumbImgName("java.jpg");
        buildpackCategoryParam13.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategoryParam13.setUseYn("Y");
        buildpackCategoryParam13.setAppSampleFileName("sample-spring.war");
        buildpackCategoryParam13.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategoryParam13.setAppSampleFileSize("9478983");
        buildpackCategoryParam13.setUserId("admin");
        buildpackCategoryParam13.setCreated(null);
        buildpackCategoryParam13.setLastmodified(null);
        buildpackCategoryParam13.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategoryParam13.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        updateBuildPackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testUpdateServicePackCatalog
        updateServicePackCatalogResultMap = new HashMap();
        servicepackCategoryParam14 = new ServicepackCategory();
        servicepackCategoryParam14.setNo(1);
        servicepackCategoryParam14.setName("Redis 서비스");
        servicepackCategoryParam14.setClassification("service_nosql");
        servicepackCategoryParam14.setSummary("Redis NoSQL 및 In memory 서비스");
        servicepackCategoryParam14.setDescription("<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n");
        servicepackCategoryParam14.setServicePackName("redis");
        servicepackCategoryParam14.setThumbImgName("redis.jpg");
        servicepackCategoryParam14.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn");
        servicepackCategoryParam14.setUseYn("Y");
        servicepackCategoryParam14.setUserId("admin");
        servicepackCategoryParam14.setCreated(null);
        servicepackCategoryParam14.setLastmodified(null);
        servicepackCategoryParam14.setParameter("{\"test\":\"text\"}");
        servicepackCategoryParam14.setAppBindParameter("{\"sdfsdfsdfsdf\":\"text\"}");
        servicepackCategoryParam14.setDashboardUseYn("N");
        servicepackCategoryParam14.setAppBindYn("Y");
        servicepackCategoryParam14.setDocFileUrl("https://redis.io/");
        servicepackCategoryParam14.setTagsParam("{\"paasta\":\"colors6\",\"pay\":\"colors2\"}");
        updateServicePackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testDeleteStarterCatalog
        deleteStarterCatalogResultMap = new HashMap();
        deleteStarterCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testDeleteBuildPackCatalog
        deleteBuildPackCatalogResultMap = new HashMap();
        deleteBuildPackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testDeleteServicePackCatalog
        deleteServicePackCatalogResultMap = new HashMap();
        deleteServicePackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        //testGetHistory
        getHistoryResultMap = new HashMap();
        resultHistoryList18 = new ArrayList<Object>();
        starterCategory18 = new StarterCategory();
        starterCategory18.setNo(1);
        starterCategory18.setName("Java + Mysql");
        starterCategory18.setClassification("starter_main");
        starterCategory18.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory18.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory18.setThumbImgName("test.png");
        starterCategory18.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory18.setUseYn("Y");
        starterCategory18.setUserId("admin");
        starterCategory18.setCreated(null);
        starterCategory18.setLastmodified(null);
        starterCategory18.setTagsParam("{\"paasta\":\"colors6\"}");
        resultHistoryList18.add(starterCategory18);
        buildpackCategory18 = new BuildpackCategory();
        buildpackCategory18.setNo(1);
        buildpackCategory18.setName("Java8 온라인 앱 개발환경");
        buildpackCategory18.setClassification("buildpack_system");
        buildpackCategory18.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategory18.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategory18.setBuildPackName("java_buildpack");
        buildpackCategory18.setThumbImgName("java.jpg");
        buildpackCategory18.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategory18.setUseYn("Y");
        buildpackCategory18.setAppSampleFileName("sample-spring.war");
        buildpackCategory18.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategory18.setAppSampleFileSize("9478983");
        buildpackCategory18.setUserId("admin");
        buildpackCategory18.setCreated(null);
        buildpackCategory18.setLastmodified(null);
        buildpackCategory18.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategory18.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        resultHistoryList18.add(buildpackCategory18);
        servicepackCategory18 = new ServicepackCategory();
        servicepackCategory18.setNo(1);
        servicepackCategory18.setName("Redis 서비스");
        servicepackCategory18.setClassification("service_nosql");
        servicepackCategory18.setSummary("Redis NoSQL 및 In memory 서비스");
        servicepackCategory18.setDescription("<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n");
        servicepackCategory18.setServicePackName("redis");
        servicepackCategory18.setThumbImgName("redis.jpg");
        servicepackCategory18.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn");
        servicepackCategory18.setUseYn("Y");
        servicepackCategory18.setUserId("admin");
        servicepackCategory18.setCreated(null);
        servicepackCategory18.setLastmodified(null);
        servicepackCategory18.setParameter("{\"test\":\"text\"}");
        servicepackCategory18.setAppBindParameter("{\"sdfsdfsdfsdf\":\"text\"}");
        servicepackCategory18.setDashboardUseYn("N");
        servicepackCategory18.setAppBindYn("Y");
        servicepackCategory18.setDocFileUrl("https://redis.io/");
        servicepackCategory18.setTagsParam("{\"paasta\":\"colors6\",\"pay\":\"colors2\"}");
        resultHistoryList18.add(servicepackCategory18);
        getHistoryResultMap.put("list", resultHistoryList18);

        //testGetStarterRelation
        getStarterRelationResultMap = new HashMap();
        starterCategory19 = new StarterCategory();
        starterCategory19.setNo(1);
        starterCategory19.setName("Java + Mysql");
        starterCategory19.setClassification("starter_main");
        starterCategory19.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory19.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory19.setThumbImgName("test.png");
        starterCategory19.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory19.setUseYn("Y");
        starterCategory19.setUserId("admin");
        starterCategory19.setCreated(null);
        starterCategory19.setLastmodified(null);
        starterCategory19.setTagsParam("{\"paasta\":\"colors6\"}");
        getStarterRelationResultMap.put("Starter", starterCategory19);
        servicepackCategoryList19 = new ArrayList<ServicepackCategory>();
        servicepackCategory19 = new ServicepackCategory();
        servicepackCategory19.setNo(1);
        servicepackCategory19.setName("Redis 서비스");
        servicepackCategory19.setClassification("service_nosql");
        servicepackCategory19.setSummary("Redis NoSQL 및 In memory 서비스");
        servicepackCategory19.setDescription("<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n");
        servicepackCategory19.setServicePackName("redis");
        servicepackCategory19.setThumbImgName("redis.jpg");
        servicepackCategory19.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn");
        servicepackCategory19.setUseYn("Y");
        servicepackCategory19.setUserId("admin");
        servicepackCategory19.setCreated(null);
        servicepackCategory19.setLastmodified(null);
        servicepackCategory19.setParameter("{\"test\":\"text\"}");
        servicepackCategory19.setAppBindParameter("{\"sdfsdfsdfsdf\":\"text\"}");
        servicepackCategory19.setDashboardUseYn("N");
        servicepackCategory19.setAppBindYn("Y");
        servicepackCategory19.setDocFileUrl("https://redis.io/");
        servicepackCategory19.setTagsParam("{\"paasta\":\"colors6\",\"pay\":\"colors2\"}");
        servicepackCategoryList19.add(servicepackCategory19);
        getStarterRelationResultMap.put("Servicepack", servicepackCategoryList19);
        buildpackCategory19 = new BuildpackCategory();
        buildpackCategory19.setNo(1);
        buildpackCategory19.setName("Java8 온라인 앱 개발환경");
        buildpackCategory19.setClassification("buildpack_system");
        buildpackCategory19.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategory19.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategory19.setBuildPackName("java_buildpack");
        buildpackCategory19.setThumbImgName("java.jpg");
        buildpackCategory19.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategory19.setUseYn("Y");
        buildpackCategory19.setAppSampleFileName("sample-spring.war");
        buildpackCategory19.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategory19.setAppSampleFileSize("9478983");
        buildpackCategory19.setUserId("admin");
        buildpackCategory19.setCreated(null);
        buildpackCategory19.setLastmodified(null);
        buildpackCategory19.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategory19.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        getStarterRelationResultMap.put("Buildpack", buildpackCategory19);

        //testInsertHistroy
        insertHistroyResultMap = new HashMap();
        catalogHistoryParam20 = new CatalogHistory();
        catalogHistoryParam20.setNo(1);
        catalogHistoryParam20.setCatalogNo(1);
        catalogHistoryParam20.setCatalogType("servicePack");
        catalogHistoryParam20.setUserId("admin");
        catalogHistoryParam20.setCreated(null);
        catalogHistoryParam20.setLastmodified(null);

        insertHistroyResultMap.put("Result", catalogHistoryParam20);

        //testGetListRoutes
        catalogCcList20 = new ArrayList<CatalogCc>();
        catalogCc20 = new CatalogCc();
        catalogCc20.setId("1");
        catalogCc20.setGuid("65f1e2cf-2b3a-44c4-9d1d-64766c747cda");
        catalogCc20.setCreatedAt(null);
        catalogCc20.setUpdatedAt(null);
        catalogCc20.setHost("portal-api");
        catalogCc20.setDomainid(1);
        catalogCc20.setSpaceid(1);
        catalogCc20.setPath("");
        catalogCc20.setPort(0);
        catalogCcList20.add(catalogCc20);

        //testCheckRoute
        checkRouteResultMap = new HashMap();
        checkRouteResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);
    }

    @Test
    public void testGetStarterCatalog() throws Exception {
        when(catalogService.getStarterCatalog(1)).thenReturn(getStarterCatalogResultMap);

        Map<String, Object> result = catalogService.getStarterCatalog(1);
        Assert.assertEquals(getStarterCatalogResultMap, result);
    }

    @Test
    public void testGetStarterNamesList() throws Exception {
        when(catalogService.getStarterNamesList(starterCategoryParam2)).thenReturn(getStarterNamesListResultMap);

        Map<String, Object> result = catalogService.getStarterNamesList(starterCategoryParam2);
        Assert.assertEquals(getStarterNamesListResultMap, result);
    }

    @Test
    public void testGetBuildPackCatalogList() throws Exception {
        when(catalogService.getBuildPackCatalogList(buildpackCategoryParam3)).thenReturn(getBuildPackCatalogListResultMap);

        Map<String, Object> result = catalogService.getBuildPackCatalogList(buildpackCategoryParam3);
        Assert.assertEquals(getBuildPackCatalogListResultMap, result);
    }

    @Test
    public void testGetPacks() throws Exception {
        when(catalogService.getPacks("test")).thenReturn(getPacksResultMap);

        Map<String, Object> result = catalogService.getPacks("test");
        Assert.assertEquals(getPacksResultMap, result);
    }

    @Test
    public void testGetServicePackCatalogList() throws Exception {
        when(catalogService.getServicePackCatalogList(servicepackCategoryParam5)).thenReturn(getServicePackCatalogListResultMap);

        Map<String, Object> result = catalogService.getServicePackCatalogList(servicepackCategoryParam5);
        Assert.assertEquals(getServicePackCatalogListResultMap, result);
    }

    @Test
    public void testGetStarterCatalogCount() throws Exception {
        when(catalogService.getStarterCatalogCount(starterCategoryParam6)).thenReturn(getStarterCatalogCountResult);

        int result = catalogService.getStarterCatalogCount(starterCategoryParam6);
        Assert.assertEquals(getStarterCatalogCountResult, result);
    }

    @Test
    public void testGetBuildPackCatalogCount() throws Exception {
        when(catalogService.getBuildPackCatalogCount(buildpackCategoryParam7)).thenReturn(getBuildPackCatalogCountResult);

        int result = catalogService.getBuildPackCatalogCount(buildpackCategoryParam7);
        Assert.assertEquals(getBuildPackCatalogCountResult, result);
    }

    @Test
    public void testGetServicePackCatalogCount() throws Exception {
        when(catalogService.getServicePackCatalogCount(servicepackCategoryParam8)).thenReturn(getServicePackCatalogCountResult);

        int result = catalogService.getServicePackCatalogCount(servicepackCategoryParam8);
        Assert.assertEquals(getServicePackCatalogCountResult, result);
    }

    @Test
    public void testInsertStarterCatalog() throws Exception {
        when(catalogService.insertStarterCatalog(starterCategoryParam9)).thenReturn(insertStarterCatalogResultMap);

        Map<String, Object> result = catalogService.insertStarterCatalog(starterCategoryParam9);
        Assert.assertEquals(insertStarterCatalogResultMap, result);
    }

    @Test
    public void testInsertBuildPackCatalog() throws Exception {
        when(catalogService.insertBuildPackCatalog(buildpackCategoryParam10)).thenReturn(insertBuildPackCatalogResultMap);

        Map<String, Object> result = catalogService.insertBuildPackCatalog(buildpackCategoryParam10);
        Assert.assertEquals(insertBuildPackCatalogResultMap, result);
    }

    @Test
    public void testInsertServicePackCatalog() throws Exception {
        when(catalogService.insertServicePackCatalog(servicepackCategoryParam11)).thenReturn(insertServicePackCatalogResultMap);

        Map<String, Object> result = catalogService.insertServicePackCatalog(servicepackCategoryParam11);
        Assert.assertEquals(insertServicePackCatalogResultMap, result);
    }

    @Test
    public void testUpdateStarterCatalog() throws Exception {
        when(catalogService.updateStarterCatalog(starterCategoryParam12)).thenReturn(updateStarterCatalogResultMap);

        Map<String, Object> result = catalogService.updateStarterCatalog(starterCategoryParam12);
        Assert.assertEquals(updateStarterCatalogResultMap, result);
    }

    @Test
    public void testUpdateBuildPackCatalog() throws Exception {
        when(catalogService.updateBuildPackCatalog(buildpackCategoryParam13)).thenReturn(updateBuildPackCatalogResultMap);

        Map<String, Object> result = catalogService.updateBuildPackCatalog(buildpackCategoryParam13);
        Assert.assertEquals(updateBuildPackCatalogResultMap, result);
    }

    @Test
    public void testUpdateServicePackCatalog() throws Exception {
        when(catalogService.updateServicePackCatalog(servicepackCategoryParam14)).thenReturn(updateServicePackCatalogResultMap);

        Map<String, Object> result = catalogService.updateServicePackCatalog(servicepackCategoryParam14);
        Assert.assertEquals(updateServicePackCatalogResultMap, result);
    }

    @Test
    public void testDeleteStarterCatalog() throws Exception {
        when(catalogService.deleteStarterCatalog(1)).thenReturn(deleteStarterCatalogResultMap);

        Map<String, Object> result = catalogService.deleteStarterCatalog(1);
        Assert.assertEquals(deleteStarterCatalogResultMap, result);
    }

    @Test
    public void testDeleteBuildPackCatalog() throws Exception {
        when(catalogService.deleteBuildPackCatalog(1)).thenReturn(deleteBuildPackCatalogResultMap);

        Map<String, Object> result = catalogService.deleteBuildPackCatalog(1);
        Assert.assertEquals(deleteBuildPackCatalogResultMap, result);
    }

    @Test
    public void testDeleteServicePackCatalog() throws Exception {
        when(catalogService.deleteServicePackCatalog(1)).thenReturn(deleteServicePackCatalogResultMap);

        Map<String, Object> result = catalogService.deleteServicePackCatalog(1);
        Assert.assertEquals(deleteServicePackCatalogResultMap, result);
    }

    @Test
    public void testGetHistory() throws Exception {
        when(catalogService.getHistory("userid")).thenReturn(getHistoryResultMap);

        Map<String, Object> result = catalogService.getHistory("userid");
        Assert.assertEquals(getHistoryResultMap, result);
    }

    @Test
    public void testGetStarterRelation() throws Exception {
        when(catalogService.getStarterRelation(1)).thenReturn(getStarterRelationResultMap);

        Map<String, Object> result = catalogService.getStarterRelation(1);
        Assert.assertEquals(getStarterRelationResultMap, result);
    }

    @Test
    public void testInsertHistroy() throws Exception {
        when(catalogService.insertHistroy(catalogHistoryParam20)).thenReturn(insertHistroyResultMap);

        Map<String, Object> result = catalogService.insertHistroy(catalogHistoryParam20);
        Assert.assertEquals(insertHistroyResultMap, result);
    }

    @Test
    public void testGetListRoutes() throws Exception {
        when(catalogService.getListRoutes()).thenReturn(catalogCcList20);

        List<CatalogCc> result = catalogService.getListRoutes();
        Assert.assertEquals(catalogCcList20, result);
    }

    @Test
    public void testCheckRoute() throws Exception {
        when(catalogService.checkRoute("host")).thenReturn(checkRouteResultMap);

        Map<String, Object> result = catalogService.checkRoute("host");
        Assert.assertEquals(checkRouteResultMap, result);
    }
}
