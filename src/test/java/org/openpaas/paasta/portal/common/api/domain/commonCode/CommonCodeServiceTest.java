package org.openpaas.paasta.portal.common.api.domain.commonCode;

import org.jinq.jpa.JinqJPAStreamProvider;
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
import org.openpaas.paasta.portal.common.api.config.dataSource.PortalConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeGroup;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeGroupRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommonCodeServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Value("${datasource.portal.driver-class-name}")
    String portalDriverClassName;
    @Value("${datasource.portal.url}")
    String portalUrl;
    @Value("${datasource.portal.username}")
    String portalUsername;
    @Value("${datasource.portal.password}")
    String portalPassword;
    @Value("${jpa.hibernate.ddl-auto}")
    String ddlAuto;
    @Value("${jpa.hibernate.naming.strategy}")
    String dialect;


    @InjectMocks
    CommonCodeService commonCodeService;

    @MockBean
    PortalConfig portalConfig;

    @Mock
    JinqSource jinqSource;

    @Mock
    CodeDetailRepository codeDetailRepository;

    @Mock
    CodeGroupRepository codeGroupRepository;


    private JinqJPAStreamProvider streams;

    EntityManager portalEm;

    CodeDetail codeDetail;

    CodeGroup codeGroup;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();
    }

    private void setTestData() {
        codeDetail = new CodeDetail();
        codeDetail.setCreated(new Date());
        codeDetail.setGroupId("groupid");
        codeDetail.setKey("key");
        codeDetail.setLastmodified(new Date());
        codeDetail.setNo(1);
        codeDetail.setOrder(1);
        codeDetail.setOrgId("orgId");
        codeDetail.setOrgKey("orgkey");
        codeDetail.setPageNo(1);
        codeDetail.setSummary("summary");
        codeDetail.setPageSize(1);
        codeDetail.setUseYn("Y");
        codeDetail.setValue("value");
        codeDetail.setProcType("procType");
        codeDetail.setUserId("userid");
        codeDetail.toString();

        codeGroup = new CodeGroup();
        codeGroup.setCreated(new Date());
        codeGroup.setLastmodified(new Date());
        codeGroup.setOrgId("orgId");
        codeGroup.setOrgKey("orgkey");
        codeGroup.setPageNo(1);
        codeGroup.setPageSize(1);
        codeGroup.setProcType("procType");
        codeGroup.setUserId("userid");
        codeGroup.setSearchKeyword("search");
        codeGroup.toString();


        ReflectionTestUtils.setField(portalConfig, "portalDriverClassName", portalDriverClassName);
        ReflectionTestUtils.setField(portalConfig, "portalUrl", portalUrl);
        ReflectionTestUtils.setField(portalConfig, "portalUsername", portalUsername);
        ReflectionTestUtils.setField(portalConfig, "portalPassword", portalPassword);
        ReflectionTestUtils.setField(portalConfig, "ddlAuto", ddlAuto);
        ReflectionTestUtils.setField(portalConfig, "dialect", dialect);


    }


    @Test
    public void testGetCommonCodeDetail() throws Exception {
        thrown.expect(NullPointerException.class);

        when(new JinqJPAStreamProvider(portalConfig.portalEntityManager().getNativeEntityManagerFactory())).thenReturn(streams);
        when(portalConfig.portalEntityManager().getNativeEntityManagerFactory().createEntityManager()).thenReturn(portalEm);

        JinqStream<CodeDetail> jstreams = this.streams.streamAll(portalEm, CodeDetail.class);
        when(jinqSource.streamAllPortal(CodeDetail.class)).thenReturn(jstreams);
        int no = codeDetail.getNo();
        String groudId = codeDetail.getGroupId();
        String key = codeDetail.getKey();
        when(jstreams.where(c -> c.getNo() == no)).thenReturn(jstreams);
        when(jstreams.where(c -> c.getKey().equals(key))).thenReturn(jstreams);
        when(jstreams.where(c -> c.getGroupId().equals(groudId))).thenReturn(jstreams);


        Map result = commonCodeService.getCommonCodeDetail(codeDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void testGetCommonCodeJoinGroup() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<CodeDetail> codeDetailJinqStream = jinqSource.streamAllPortal(CodeDetail.class);
        when(jinqSource.streamAllPortal(CodeDetail.class)).thenReturn(codeDetailJinqStream);
        String key = codeDetail.getKey();
        String GroupId = codeDetail.getGroupId();


        when(codeDetailJinqStream.where(c -> c.getKey().equals(key))).thenReturn(codeDetailJinqStream);
        when(codeDetailJinqStream.where(c -> c.getGroupId().equals(GroupId))).thenReturn(codeDetailJinqStream);


        Map<String, Object> result = commonCodeService.getCommonCodeJoinGroup(codeDetail, codeGroup);

        Assert.assertNotNull(result);


    }

    /**
     * 공통그룹 목록을 조회한다.
     *
     * @param codeGroup CodeGroup(아이디)
     * @return Map(자바클래스)
     */
    public Map<String, Object> testGetGroupDetail(CodeGroup codeGroup) {
        JinqStream<CodeGroup> streams = jinqSource.streamAllPortal(CodeGroup.class);
        String id = codeGroup.getId();
        String searchKeyword = codeGroup.getSearchKeyword();

        if (null != id && !"".equals(id) && !"null".equals(id.toLowerCase())) {
            streams = streams.where(c -> c.getId().equals(id));
        }
        if (null != searchKeyword && !"".equals(searchKeyword)) {
            streams = streams.where(c -> c.getName().contains(searchKeyword) || c.getId().contains(searchKeyword));
        }

        streams = streams.sortedBy(c -> c.getCreated());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>() {{
            put("id", x.getId());
            put("orgId", x.getOrgId());
            put("name", x.getName());
            put("created", x.getCreated());
            put("lastModified", x.getLastmodified());
            put("userId", x.getUserId());
            put("pageNo", x.getPageNo());
            put("pageSize", x.getPageSize());
            put("procType", x.getProcType());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};
    }

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param groupid groupid(아이디)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getCodeDetail(String groupid) {
        List<CodeDetail> list = codeDetailRepository.findAllByGroupId(groupid);
        list.sort(Comparator.comparingInt(CodeDetail::getValue2));
        return new HashMap<String, Object>() {{
            put("list", list);
        }};
    }


    /**
     * 공통 코드 그룹을 등록한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertDetailGroup(CodeGroup codeGroup) {
        codeGroupRepository.save(codeGroup);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


    /**
     * 공통 코드을 등록한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> insertDetail(CodeDetail codeDetail) {
        int count = codeDetailRepository.countByGroupId(codeDetail.getGroupId());
        System.out.println(count);
        codeDetail.setOrder(count + 1);
        codeDetailRepository.save(codeDetail);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 공통 코드 그룹을 수정한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public String updateCommonGroup(String id, CodeGroup codeGroup) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        if (codeGroupRepository.findById(id) != null) {
            codeGroup.setId(codeGroup.getId());
            codeGroup.setName(codeGroup.getName());
            codeGroup.setUserId(codeGroup.getUserId());
            codeGroupRepository.save(codeGroup);
        } else {
            resultStr = Constants.RESULT_STATUS_FAIL;
        }
        return resultStr;
    }

    /**
     * 공통 코드을 수정한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> updateCommonDetail(int no, CodeDetail codeDetail) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;
        CodeDetail update = codeDetailRepository.findOne(no);

        codeDetail.setGroupId(update.getGroupId());
        codeDetail.setCreated(update.getCreated());

        codeDetailRepository.save(codeDetail);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param id * @return Map(자바클래스)
     */
    public Map<String, Object> deleteCommonGroup(String id) {
        List<CodeDetail> codeDetailList = codeDetailRepository.findByGroupId(id);
        for (CodeDetail codeDetail : codeDetailList) {
            codeDetailRepository.delete(codeDetail.getNo());
        }

        CodeGroup codeGroup = new CodeGroup();
        codeGroup.setId(id);
        codeGroupRepository.delete(codeGroup);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 공통 코드을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String, Object> deleteCommonDetail(int no) {
        codeDetailRepository.delete(no);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


}
