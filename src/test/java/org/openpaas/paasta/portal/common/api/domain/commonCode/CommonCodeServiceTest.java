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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
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
    List<CodeDetail> codeDetails;
    CodeGroup codeGroup;
    List<CodeGroup> codeGroups;

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

        codeDetails = new ArrayList<>();
        codeDetails.add(codeDetail);


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

        codeGroups = new ArrayList<>();
        codeGroups.add(codeGroup);

        ReflectionTestUtils.setField(portalConfig, "portalDriverClassName", portalDriverClassName);
        ReflectionTestUtils.setField(portalConfig, "portalUrl", portalUrl);
        ReflectionTestUtils.setField(portalConfig, "portalUsername", portalUsername);
        ReflectionTestUtils.setField(portalConfig, "portalPassword", portalPassword);
        ReflectionTestUtils.setField(portalConfig, "ddlAuto", ddlAuto);
        ReflectionTestUtils.setField(portalConfig, "dialect", dialect);


    }


    @Test
    public void testGetParameter(){
        codeDetail.getCreated();
        codeDetail.getGroupId();
        codeDetail.getKey();
        codeDetail.getLastmodified();
        codeDetail.getNo();
        codeDetail.getOrder();
        codeDetail.getOrgId();
        codeDetail.getOrgKey();
        codeDetail.getPageNo();
        codeDetail.getSummary();
        codeDetail.getPageSize();
        codeDetail.getUseYn();
        codeDetail.getValue();
        codeDetail.getProcType();
        codeDetail.getUserId();
        codeDetail.toString();



        codeGroup.getCreated();
        codeGroup.getLastmodified();
        codeGroup.getOrgId();
        codeGroup.getOrgKey();
        codeGroup.getPageNo();
        codeGroup.getPageSize();
        codeGroup.getProcType();
        codeGroup.getUserId();
        codeGroup.getSearchKeyword();
        codeGroup.toString();

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

    @Test
    public void testGetGroupDetail() throws Exception {
        thrown.expect(NullPointerException.class);

        JinqStream<CodeGroup> streams = jinqSource.streamAllPortal(CodeGroup.class);
        String id = codeGroup.getId();
        String searchKeyword = codeGroup.getSearchKeyword();
        when(streams.where(c -> c.getId().equals(id))).thenReturn(streams);
        when(streams.where(c -> c.getName().contains(searchKeyword) || c.getId().contains(searchKeyword))).thenReturn(streams);
        when(streams.sortedBy(c -> c.getCreated())).thenReturn(streams);

        Map<String, Object> result = commonCodeService.getGroupDetail(codeGroup);

        Assert.assertNotNull(result);
    }


    @Test
    public void testGetCodeDetail() throws Exception {
        when(codeDetailRepository.findAllByGroupId(anyString())).thenReturn(codeDetails);
        Map<String, Object> result = commonCodeService.getCodeDetail("groupid");
        Assert.assertNotNull(result);
    }


    @Test
    public void testInsertDetailGroup() throws Exception {
        when(codeGroupRepository.save(codeGroup)).thenReturn(codeGroup);
        Map<String, Object> result = commonCodeService.insertDetailGroup(codeGroup);
        Assert.assertNotNull(result);
    }


    @Test
    public void insertDetail() throws Exception {
        when(codeDetailRepository.countByGroupId(anyString())).thenReturn(1);
        when(codeDetailRepository.save(codeDetail)).thenReturn(codeDetail);
        Map<String, Object> result = commonCodeService.insertDetail(codeDetail);
        Assert.assertNotNull(result);
    }


    @Test
    public void testUpdateCommonGroup() throws Exception {

        when(codeGroupRepository.findById(anyString())).thenReturn(codeGroups);
        when(codeGroupRepository.save(codeGroup)).thenReturn(codeGroup);
        String result = commonCodeService.updateCommonGroup("id", codeGroup);
        Assert.assertNotNull(result);

    }

    @Test
    public void testUpdateCommonDetail() throws Exception {
        when(codeDetailRepository.findOne(anyInt())).thenReturn(codeDetail);
        when(codeDetailRepository.save(codeDetail)).thenReturn(codeDetail);
        Map result = commonCodeService.updateCommonDetail(1, codeDetail);
        Assert.assertNotNull(result);
    }


    @Test
    public void deleteCommonGroup() throws Exception {
        when(codeDetailRepository.findByGroupId(anyString())).thenReturn(codeDetails);
        doNothing().when(codeDetailRepository).delete(anyInt());
        Map result = commonCodeService.deleteCommonGroup("id");
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteCommonDetail() throws Exception {
        doNothing().when(codeDetailRepository).delete(anyInt());
        Map result = commonCodeService.deleteCommonDetail(1);
        Assert.assertNotNull(result);
    }


}
