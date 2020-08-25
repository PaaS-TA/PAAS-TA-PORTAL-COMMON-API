package org.openpaas.paasta.portal.common.api.domain.catalog;

import org.apache.commons.collections.map.HashedMap;
import org.jinq.orm.stream.JinqStream;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.cc.CatalogCc;
import org.openpaas.paasta.portal.common.api.entity.portal.*;
import org.openpaas.paasta.portal.common.api.repository.cc.CatalogCcRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by indra on 2018-06-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogServiceTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    CatalogHistoryRepository catalogHistoryRepository;
    @Mock
    CatalogCcRepository catalogCcRepository;

    @Mock
    StarterCategoryRepository starterCategoryRepository;

    @Mock
    StarterServicepackRelationRepository starterServicePackRelationRepository;

    @Mock
    StarterBuildPackRelationRepository starterBuildPackRelationRepository;

    @Mock
    BuildpackCategoryRepository buildpackCategoryRepository;

    @Mock
    ServicepackCategoryRepository servicepackCategoryRepository;

    @MockBean
    JinqSource jinqSource;

    @InjectMocks
    CatalogService catalogService;



    StarterCategory starterCategory;
    List<StarterCategory> starterCategories;


    BuildpackCategory buildpackCategory;
    List<BuildpackCategory> buildpackCategories;


    CatalogHistory catalogHistory;
    List<CatalogHistory> catalogHistories;


    List<CatalogCc> catalogCcs;
    CatalogCc catalogCc;


    List<ServicepackCategory> servicepackCategories;
    ServicepackCategory servicepackCategory;


    List<StarterServicepackRelation> starterServicepackRelations;
    StarterServicepackRelation starterServicepackRelation;
    List<StarterBuildpackRelation> starterBuildpackRelations;
    StarterBuildpackRelation starterBuildpackRelation;

    Map updateStarterCatalogResultMap;
    Map getStarterNamesListResultMap;
    Map getStarterCatalogResultMap;
    Map insertStarterCatalogResultMap;
    Map getPacksResultMap;
    Map updateBuildPackCatalogResultMap;
    Map insertBuildPackCatalogResultMap;
    Map getBuildPackCatalogListResultMap;
    Map insertHistroyResultMap;
    Map histroyResultMap;
    Map deleteStarterCatalogResultMap;
    Map deleteBuildPackCatalogResultMap;
    Map deleteServicePackCatalogResultMap;
    Map getStarterRelationResultMap;
    Map getHistoryResultMap;
    Map getServicePackCatalogListResultMap;
    Map checkRouteResultMap_success;
    Map checkRouteResultMap_fail;
    Map updateServicePackCatalogResultMap;
    Map insertServicePackCatalogResultMap;

    @Before
    public void setUp() {
        this.setTestData();
        MockitoAnnotations.initMocks(this);
    }

    private void setTestData() {
        //testGetStarterCatalog
        List<Integer> ssrIntList = new ArrayList<>();


        starterCategory = new StarterCategory();
        starterCategory.setNo(1);
        starterCategory.setName("Java + Mysql");
        starterCategory.setClassification("starter_main");
        starterCategory.setSummary("Java Tomcat 환경의 MysqlDB  앱 템플릿");
        starterCategory.setDescription("자바8 Tomcat 앱 개발 환경과  Mysql DB  서비스로 애플리케이션을 개발합니다.");
        starterCategory.setThumbImgName("test.png");
        starterCategory.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/b71f3291c6334e65ae53dc9cede16384-1529890134819-Z2V0SW1hZ2UucG5n");
        starterCategory.setUseYn("Y");
        starterCategory.setUserId("admin");
        starterCategory.setCreated(null);
        starterCategory.setLastmodified(null);
        starterCategory.setTagsParam("{\"paasta\":\"colors6\"}");
        starterCategory.setClassificationValue("ClassificationValue");
        starterCategory.setClassificationSummary("ClassificationSummary");
        starterCategory.setSearchKeyword("SearchKeyword");
        ssrIntList.add(1);
        starterCategory.setServicePackCategoryNoList(ssrIntList);
        starterCategory.setBuildPackCategoryNo(1);


        buildpackCategories = new ArrayList<BuildpackCategory>();
        buildpackCategory = new BuildpackCategory();
        buildpackCategory.setNo(1);
        buildpackCategory.setName("Java8 온라인 앱 개발환경");
        buildpackCategory.setClassification("buildpack_system");
        buildpackCategory.setSummary("Java8 온라인 앱 개발환경");
        buildpackCategory.setDescription("Java8 온라인 빌드팩은 실행환경 구성시 자바8및 Tomcat을 다운받아서 구성한다.");
        buildpackCategory.setBuildPackName("java_buildpack");
        buildpackCategory.setThumbImgName("java.jpg");
        buildpackCategory.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/0754aeedee904f18b51d1aeb6ee95a3a-1527590631751-amF2YS5qcGc%3D");
        buildpackCategory.setUseYn("Y");
        buildpackCategory.setAppSampleFileName("sample-spring.war");
        buildpackCategory.setAppSampleFilePath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a51d769521f944d7a60d4355c00f6e40-1527590633495-c2FtcGxlLXNwcmluZy53YXI%3D");
        buildpackCategory.setAppSampleFileSize("9478983");
        buildpackCategory.setUserId("admin");
        buildpackCategory.setCreated(null);
        buildpackCategory.setLastmodified(null);
        buildpackCategory.setDocFileUrl("https://www.java.com/ko/download/faq/java8.xml");
        buildpackCategory.setTagsParam("{\"community\":\"colors7\",\"free\":\"colors1\"}");
        buildpackCategory.setClassificationValue("classificationValue");
        buildpackCategory.setClassificationSummary("classificationSummary");
        buildpackCategory.setSearchKeyword("searchkey");
        buildpackCategories.add(buildpackCategory);


        //testInsertHistroy
        catalogHistory = new CatalogHistory();
        catalogHistory.setNo(1);
        catalogHistory.setCatalogNo(1);
        catalogHistory.setCatalogType("servicePack");
        catalogHistory.setUserId("admin");
        catalogHistory.setCreated(null);
        catalogHistory.setLastmodified(null);


        //testGetListRoutes
        catalogCcs = new ArrayList<CatalogCc>();
        catalogCc = new CatalogCc();
        catalogCc.setId("1");
        catalogCc.setGuid("65f1e2cf-2b3a-44c4-9d1d-64766c747cda");
        catalogCc.setCreatedAt(null);
        catalogCc.setUpdatedAt(null);
        catalogCc.setHost("portal-api");
        catalogCc.setDomainid(1);
        catalogCc.setSpaceid(1);
        catalogCc.setPath("");
        catalogCc.setPort(0);
        catalogCcs.add(catalogCc);


        catalogHistories = new ArrayList<>();
        catalogHistory = new CatalogHistory();
        catalogHistory.setNo(1);
        catalogHistory.setCatalogNo(1);
        catalogHistory.setCatalogType("servicePack");
        catalogHistory.setUserId("admin");
        catalogHistory.setCreated(new Date());
        catalogHistory.setLastmodified(new Date());
        catalogHistory.setSearchKeyword("SearchKeywor");
        catalogHistory.setSearchTypeColumn("SearchTypeColumn");
        catalogHistory.setSearchTypeUseYn("SearchTypeUseYn");
        catalogHistory.setStarterCatalogNo(1);
        catalogHistories.add(catalogHistory);


        servicepackCategories = new ArrayList<ServicepackCategory>();
        servicepackCategory = new ServicepackCategory();
        servicepackCategory.setNo(1);
        servicepackCategory.setName("Redis 서비스");
        servicepackCategory.setClassification("service_nosql");
        servicepackCategory.setSummary("Redis NoSQL 및 In memory 서비스");
        servicepackCategory.setDescription("<p>Redis는 메모리 기반의 Key/Value Store 로써 NoSQL DBMS 및 In memory 솔루션으로 분리된다.</p>\n");
        servicepackCategory.setServicePackName("redis");
        servicepackCategory.setThumbImgName("redis.jpg");
        servicepackCategory.setThumbImgPath("http://115.68.46.218:10008/v1/KEY_b8a1fd0cba6640688712e5f2f838baeb/portal-container/a34c2adaa7904d38ba0e770c90d85d42-1527673993744-cmVkaXM_Pz8uanBn");
        servicepackCategory.setUseYn("Y");
        servicepackCategory.setUserId("admin");
        servicepackCategory.setCreated(null);
        servicepackCategory.setLastmodified(null);
        servicepackCategory.setParameter("{\"test\":\"text\"}");
        servicepackCategory.setAppBindParameter("{\"sdfsdfsdfsdf\":\"text\"}");
        servicepackCategory.setDashboardUseYn("N");
        servicepackCategory.setAppBindYn("Y");
        servicepackCategory.setDocFileUrl("https://redis.io/");
        servicepackCategory.setTagsParam("{\"paasta\":\"colors6\",\"pay\":\"colors2\"}");
        servicepackCategory.setClassificationValue("ClassificationValue");
        servicepackCategory.setClassificationSummary("ClassificationSummary");
        servicepackCategory.setSearchKeyword("SearchKeyword");
        servicepackCategory.setOnDemandYn("Y");

        servicepackCategories.add(servicepackCategory);


        starterServicepackRelations = new ArrayList<>();
        starterServicepackRelation = new StarterServicepackRelation();
        starterServicepackRelation.setNo(1);
        starterServicepackRelation.setServicepackCategoryNo(1);
        starterServicepackRelation.setStarterCatalogNo(1);
        starterServicepackRelations.add(starterServicepackRelation);


        starterBuildpackRelations = new ArrayList<>();
        starterBuildpackRelation = new StarterBuildpackRelation();

        starterBuildpackRelation.setBuildpackCategoryNo(1);
        starterBuildpackRelation.setNo(1);
        starterBuildpackRelation.setStarterCatalogNo(1);

        starterBuildpackRelations.add(starterBuildpackRelation);


        updateBuildPackCatalogResultMap = new HashMap();
        updateBuildPackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        insertBuildPackCatalogResultMap = new HashMap();
        insertBuildPackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        getBuildPackCatalogListResultMap = new HashMap();
        getBuildPackCatalogListResultMap.put("list", buildpackCategories);

        histroyResultMap = new HashMap();
        histroyResultMap.put("Result", catalogHistory);

        insertHistroyResultMap = new HashMap();
        insertHistroyResultMap.put("Result", catalogHistory);

        insertStarterCatalogResultMap = new HashMap();
        insertStarterCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        updateStarterCatalogResultMap = new HashMap();
        updateStarterCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        getStarterNamesListResultMap = new HashedMap();
        getStarterNamesListResultMap.put("list", starterCategories);

        getStarterCatalogResultMap = new HashedMap();
        getStarterCatalogResultMap.put("info", starterCategory);

        getPacksResultMap = new HashMap();
        getPacksResultMap.put("TemplateList", starterCategories);
        getPacksResultMap.put("BuildPackList", buildpackCategories);

        checkRouteResultMap_fail = new HashMap();
        checkRouteResultMap_fail.put("RESULT", Constants.RESULT_STATUS_FAIL);

        checkRouteResultMap_success = new HashMap();
        checkRouteResultMap_success.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        deleteStarterCatalogResultMap = new HashMap();
        deleteStarterCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        deleteStarterCatalogResultMap = new HashMap();
        deleteStarterCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        deleteBuildPackCatalogResultMap = new HashMap();
        deleteBuildPackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        deleteServicePackCatalogResultMap = new HashMap();
        deleteServicePackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        updateBuildPackCatalogResultMap = new HashMap();
        updateBuildPackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        insertServicePackCatalogResultMap = new HashMap();
        insertServicePackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        updateServicePackCatalogResultMap = new HashMap();
        updateServicePackCatalogResultMap.put("RESULT", Constants.RESULT_STATUS_SUCCESS);

        getServicePackCatalogListResultMap = new HashMap();
        getServicePackCatalogListResultMap.put("list", servicepackCategories);


    }

    @Test
    public void testGetParameter(){

        starterCategory.getNo();
        starterCategory.getName();
        starterCategory.getClassification();
        starterCategory.getSummary();
        starterCategory.getDescription();
        starterCategory.getThumbImgName();
        starterCategory.getThumbImgPath();
        starterCategory.getUseYn();
        starterCategory.getUserId();
        starterCategory.getCreated();
        starterCategory.getLastmodified();
        starterCategory.getTagsParam();

        starterCategory.getServicePackCategoryNoList();
        starterCategory.getBuildPackCategoryNo();
        starterCategory.getClassificationValue();
        starterCategory.getClassificationSummary();
        starterCategory.getSearchKeyword();
        starterCategory.toString();


        buildpackCategory.getNo();
        buildpackCategory.getName();
        buildpackCategory.getClassification();
        buildpackCategory.getSummary();
        buildpackCategory.getDescription();
        buildpackCategory.getBuildPackName();
        buildpackCategory.getThumbImgName();
        buildpackCategory.getThumbImgPath();
        buildpackCategory.getUseYn();
        buildpackCategory.getAppSampleFileName();
        buildpackCategory.getAppSampleFilePath();
        buildpackCategory.getAppSampleFileSize();
        buildpackCategory.getUserId();
        buildpackCategory.getCreated();
        buildpackCategory.getLastmodified();
        buildpackCategory.getDocFileUrl();
        buildpackCategory.getTagsParam();
        buildpackCategory.getClassificationValue();
        buildpackCategory.getClassificationSummary();
        buildpackCategory.getSearchKeyword();
        buildpackCategory.toString();


        catalogCc.getId();
        catalogCc.getGuid();
        catalogCc.getCreatedAt();
        catalogCc.getUpdatedAt();
        catalogCc.getHost();
        catalogCc.getDomainid();
        catalogCc.getSpaceid();
        catalogCc.getPath();
        catalogCc.getPort();
        catalogCc.toString();

        catalogHistory.getNo();
        catalogHistory.getCatalogNo();
        catalogHistory.getCatalogType();
        catalogHistory.getUserId();
        catalogHistory.getCreated();
        catalogHistory.getLastmodified();
        catalogHistory.getLastmodified();
        catalogHistory.getSearchKeyword();
        catalogHistory.getSearchTypeColumn();
        catalogHistory.getSearchTypeUseYn();
        catalogHistory.getStarterCatalogNo();
        catalogHistory.toString();



        servicepackCategory.getNo();
        servicepackCategory.getName();
        servicepackCategory.getClassification();
        servicepackCategory.getSummary();
        servicepackCategory.getDescription();
        servicepackCategory.getServicePackName();
        servicepackCategory.getThumbImgName();
        servicepackCategory.getThumbImgPath();
        servicepackCategory.getUseYn();
        servicepackCategory.getUserId();
        servicepackCategory.getCreated();
        servicepackCategory.getLastmodified();
        servicepackCategory.getParameter();
        servicepackCategory.getAppBindParameter();
        servicepackCategory.getDashboardUseYn();
        servicepackCategory.getAppBindYn();
        servicepackCategory.getDocFileUrl();
        servicepackCategory.getTagsParam();
        servicepackCategory.getClassificationValue();
        servicepackCategory.getClassificationSummary();
        servicepackCategory.getOnDemandYn();
        servicepackCategory.getSearchKeyword();
        servicepackCategory.toString();

        starterServicepackRelation.getNo();
        starterServicepackRelation.getServicepackCategoryNo();
        starterServicepackRelation.getStarterCatalogNo();
        starterServicepackRelation.toString();


        starterBuildpackRelation.getBuildpackCategoryNo();
        starterBuildpackRelation.getNo();
        starterBuildpackRelation.getStarterCatalogNo();
        starterBuildpackRelation.toString();

    }


    @Test
    public void testGetStarterCatalog() throws Exception {

        when(starterCategoryRepository.findOne(anyInt())).thenReturn(starterCategory);
        when(starterServicePackRelationRepository.findByStarterCatalogNo(anyInt())).thenReturn(starterServicepackRelations);

        Map<String, Object> result = catalogService.getStarterCatalog(1);
        Assert.assertEquals(getStarterCatalogResultMap, result);
    }

    @Test
    public void testGetStarterNamesList() throws Exception {
        thrown.expect(NullPointerException.class);
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);
        String searchKeyword = "searchKeyword";
        when(jinqSource.streamAllPortal(StarterCategory.class)).thenReturn(streams);
        when(streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword)));

        when(streams.sortedDescendingBy(c -> c.getNo())).thenReturn(streams);

        when(streams.toList()).thenReturn(starterCategories);

        Map<String, Object> result = catalogService.getStarterNamesList(starterCategory);
        Assert.assertEquals(getStarterNamesListResultMap, result);
    }

    @Test
    public void testGetBuildPackCatalogList() throws Exception {
        thrown.expect(NullPointerException.class);
        JinqStream<BuildpackCategory> streams = jinqSource.streamAllPortal(BuildpackCategory.class);
        String searchKeyword = "searchKeyword";
        when(jinqSource.streamAllPortal(BuildpackCategory.class)).thenReturn(streams);
        when(streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword)));

        int no = 1;
        when(streams.where(c -> c.getNo() == no)).thenReturn(streams);

        when(streams.sortedDescendingBy(c -> c.getNo())).thenReturn(streams);

        when(streams.toList()).thenReturn(buildpackCategories);

        Map<String, Object> result = catalogService.getBuildPackCatalogList(buildpackCategory);
        Assert.assertEquals(getBuildPackCatalogListResultMap, result);
    }

    @Test
    public void testGetPacks() throws Exception {
        thrown.expect(NullPointerException.class);
        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);
        JinqStream<BuildpackCategory> streams2 = jinqSource.streamAllPortal(BuildpackCategory.class);

        String searchKeyword = "searchKeyword";
        when(jinqSource.streamAllPortal(StarterCategory.class)).thenReturn(streams);
        when(streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword)));

        when(jinqSource.streamAllPortal(BuildpackCategory.class)).thenReturn(streams2);
        when(streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword)));

        when(streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword))).thenReturn(streams);
        when(streams2.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword))).thenReturn(streams2);

        when(streams.sortedDescendingBy(c -> c.getNo())).thenReturn(streams);
        when(streams2.sortedDescendingBy(c -> c.getNo())).thenReturn(streams2);
        when(streams.toList()).thenReturn(starterCategories);
        when(streams2.toList()).thenReturn(buildpackCategories);


        Map<String, Object> result = catalogService.getPacks("test");
        Assert.assertEquals(getPacksResultMap, result);
    }

    @Test
    public void testGetServicePackCatalogList() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<ServicepackCategory> streams = jinqSource.streamAllPortal(ServicepackCategory.class);
        String searchKeyword = "searchKeyword";
        when(jinqSource.streamAllPortal(ServicepackCategory.class)).thenReturn(streams);
        when(streams.where(c -> c.getName().contains(searchKeyword) || c.getDescription().contains(searchKeyword) || c.getSummary().contains(searchKeyword)));

        int no = 1;
        when(streams = streams.where(c -> c.getNo() == no)).thenReturn(streams);

        when(streams.sortedDescendingBy(c -> c.getNo())).thenReturn(streams);

        when(streams.toList()).thenReturn(servicepackCategories);

        Map<String, Object> result = catalogService.getServicePackCatalogList(servicepackCategory);
        Assert.assertEquals(getServicePackCatalogListResultMap, result);
    }

    @Test
    public void testGetStarterCatalogCount() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<StarterCategory> streams = jinqSource.streamAllPortal(StarterCategory.class);
        when(jinqSource.streamAllPortal(StarterCategory.class)).thenReturn(streams);

        String name = "name";
        when(streams.where(c -> c.getName().equals(name))).thenReturn(streams);
        when(streams.toList()).thenReturn(starterCategories);

        int result = catalogService.getStarterCatalogCount(starterCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testGetBuildPackCatalogCount() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<BuildpackCategory> streams = jinqSource.streamAllPortal(BuildpackCategory.class);
        when(jinqSource.streamAllPortal(BuildpackCategory.class)).thenReturn(streams);

        String name = "name";

        when(streams.where(c -> c.getName().equals(name))).thenReturn(streams);
        when(streams.sortedDescendingBy(c -> c.getNo())).thenReturn(streams);
        when(streams.toList()).thenReturn(buildpackCategories);


        int result = catalogService.getBuildPackCatalogCount(buildpackCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testGetServicePackCatalogCount() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<ServicepackCategory> streams = jinqSource.streamAllPortal(ServicepackCategory.class);
        when(jinqSource.streamAllPortal(ServicepackCategory.class)).thenReturn(streams);

        String name = "name";

        when(streams.where(c -> c.getName().equals(name))).thenReturn(streams);
        when(streams.sortedDescendingBy(c -> c.getNo())).thenReturn(streams);
        when(streams.toList()).thenReturn(servicepackCategories);


        int result = catalogService.getServicePackCatalogCount(servicepackCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testInsertStarterCatalog() throws Exception {

        when(starterCategoryRepository.save(starterCategory)).thenReturn(starterCategory);
        when(starterServicePackRelationRepository.save(starterServicepackRelation)).thenReturn(starterServicepackRelation);
        when(starterBuildPackRelationRepository.save(starterBuildpackRelation)).thenReturn(starterBuildpackRelation);

        Map<String, Object> result = catalogService.insertStarterCatalog(starterCategory);
        Assert.assertEquals(insertStarterCatalogResultMap, result);
    }

    @Test
    public void testInsertBuildPackCatalog() throws Exception {
        when(buildpackCategoryRepository.save(buildpackCategory)).thenReturn(buildpackCategory);

        Map<String, Object> result = catalogService.insertBuildPackCatalog(buildpackCategory);
        Assert.assertEquals(insertBuildPackCatalogResultMap, result);
    }

    @Test
    public void testInsertServicePackCatalog() throws Exception {
        when(servicepackCategoryRepository.save(servicepackCategory)).thenReturn(servicepackCategory);

        Map<String, Object> result = catalogService.insertServicePackCatalog(servicepackCategory);
        Assert.assertEquals(insertServicePackCatalogResultMap, result);
    }

    @Test
    public void testUpdateStarterCatalog() throws Exception {

        int no = 0;

        when(starterCategoryRepository.save(starterCategory)).thenReturn(starterCategory);
        doNothing().when(starterServicePackRelationRepository).delete(anyInt());
        doNothing().when(starterServicePackRelationRepository).delete(anyInt());
        doNothing().when(starterBuildPackRelationRepository).delete(anyInt());
        when(starterServicePackRelationRepository.save(starterServicepackRelation)).thenReturn(starterServicepackRelation);
        when(starterBuildPackRelationRepository.save(starterBuildpackRelation)).thenReturn(starterBuildpackRelation);


        Map<String, Object> result = catalogService.updateStarterCatalog(starterCategory);
        Assert.assertEquals(updateStarterCatalogResultMap, result);
    }

    @Test
    public void testUpdateBuildPackCatalog() throws Exception {

        when(buildpackCategoryRepository.findOne(buildpackCategory.getNo())).thenReturn(buildpackCategory);
        when(buildpackCategoryRepository.save(buildpackCategory)).thenReturn(buildpackCategory);


        Map<String, Object> result = catalogService.updateBuildPackCatalog(buildpackCategory);
        Assert.assertEquals(updateBuildPackCatalogResultMap, result);
    }

    @Test
    public void testUpdateServicePackCatalog() throws Exception {
        when(servicepackCategoryRepository.findOne(anyInt())).thenReturn(servicepackCategory);
        when(servicepackCategoryRepository.save(servicepackCategory)).thenReturn(servicepackCategory);


        Map<String, Object> result = catalogService.updateServicePackCatalog(servicepackCategory);
        Assert.assertEquals(updateServicePackCatalogResultMap, result);
    }

    @Test
    public void testDeleteStarterCatalog() throws Exception {


        //기존 스타터서비스 릴레이션 삭제
        when(starterServicePackRelationRepository.findByStarterCatalogNo(anyInt())).thenReturn(starterServicepackRelations);
        doNothing().when(starterServicePackRelationRepository).delete(starterBuildpackRelation.getNo());
        when(starterBuildPackRelationRepository.findByStarterCatalogNo(starterBuildpackRelation.getNo())).thenReturn(starterBuildpackRelations);
        doNothing().when(starterBuildPackRelationRepository).delete(starterBuildpackRelation.getNo());

        doNothing().when(starterCategoryRepository).delete(starterCategory.getNo());


        Map<String, Object> result = catalogService.deleteStarterCatalog(1);
        Assert.assertEquals(deleteStarterCatalogResultMap, result);
    }

    @Test
    public void testDeleteBuildPackCatalog() throws Exception {
        doNothing().when(buildpackCategoryRepository).delete(1);

        Map<String, Object> result = catalogService.deleteBuildPackCatalog(1);
        Assert.assertEquals(deleteBuildPackCatalogResultMap, result);
    }

    @Test
    public void testDeleteServicePackCatalog() throws Exception {
        doNothing().when(servicepackCategoryRepository).delete(1);

        Map<String, Object> result = catalogService.deleteServicePackCatalog(1);
        Assert.assertEquals(deleteServicePackCatalogResultMap, result);
    }

    @Test
    public void testGetHistory() throws Exception {
        when(catalogHistoryRepository.findAllByUserIdOrderByLastmodifiedDesc("userid")).thenReturn(catalogHistories);
        when(starterCategoryRepository.findByNo(1)).thenReturn(starterCategory);
        when(buildpackCategoryRepository.findByNo(1)).thenReturn(buildpackCategory);
        when(servicepackCategoryRepository.findByNo(1)).thenReturn(servicepackCategory);

        Map<String, Object> result = catalogService.getHistory("userid");

        List<Object> resultHistory = new ArrayList<>();
        resultHistory.add(servicepackCategory);

        getHistoryResultMap = new HashMap();
        getHistoryResultMap.put("list",resultHistory);

        Assert.assertEquals(getHistoryResultMap, result);
    }

    @Test
    public void testGetStarterRelation() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<StarterServicepackRelation> streams = jinqSource.streamAllPortal(StarterServicepackRelation.class);
        when(starterCategoryRepository.findByNo(anyInt())).thenReturn(starterCategory);
        when(servicepackCategoryRepository.findByNo(anyInt())).thenReturn(servicepackCategory);

        int no = 1;

        when(streams.where(c -> c.getStarterCatalogNo() == no));
        List<Integer> ints = new ArrayList<>();
        when(streams.select(c -> c.getServicepackCategoryNo()).distinct().toList()).thenReturn(ints);


        when(starterBuildPackRelationRepository.findFirstByStarterCatalogNo(anyInt())).thenReturn(starterBuildpackRelation);
        when(buildpackCategoryRepository.findByNo(anyInt())).thenReturn(buildpackCategory);

        Map<String, Object> result = catalogService.getStarterRelation(1);
        Assert.assertEquals(getStarterRelationResultMap, result);
    }

    @Test
    public void testInsertHistroy() throws Exception {
        when(catalogHistoryRepository.save(catalogHistory)).thenReturn(catalogHistory);

        Map<String, Object> result = catalogService.insertHistroy(catalogHistory);
        Assert.assertEquals(insertHistroyResultMap, result);
    }

    @Test
    public void testGetListRoutes() throws Exception {
        when(catalogCcRepository.findAll()).thenReturn(catalogCcs);

        List<CatalogCc> result = catalogService.getListRoutes();
        Assert.assertEquals(catalogCcs, result);

    }

    @Test
    public void testCheckRoute() throws Exception {
        when(catalogCcRepository.findByHost(anyString())).thenReturn(catalogCc);
        Map<String, Object> result = catalogService.checkRoute("host");
        Assert.assertEquals(checkRouteResultMap_fail, result);
    }

    @Test
    public void testCheckRoute_fail() throws Exception {
        when(catalogCcRepository.findByHost(anyString())).thenReturn(null);
        Map<String, Object> result = catalogService.checkRoute("host");
        Assert.assertEquals(checkRouteResultMap_success, result);
    }
}
