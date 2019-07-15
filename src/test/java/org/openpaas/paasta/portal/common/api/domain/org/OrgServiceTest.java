package org.openpaas.paasta.portal.common.api.domain.org;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.entity.cc.OrganizationsCc;
import org.openpaas.paasta.portal.common.api.entity.portal.InviteUser;
import org.openpaas.paasta.portal.common.api.repository.portal.InviteUserRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by indra on 2018-06-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrgServiceTest {


    @InjectMocks
    OrgService orgService;

    @Mock
    InviteUserRepository inviteUserRepository;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    List<Object> getOrgsForAdminResult;
    HashMap obejct1;

    List<Object> getOrgResult;
    HashMap obejct2;

    List<Object> selectInviteInfoResult;
    HashMap obejct3;

    Map getInviteUserListResultMap;
    List<InviteUser> inviteUserList4;
    InviteUser inviteUser4;


    OrganizationsCc organizationsCc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();
    }

    private void setTestData() {
        //testGetOrgsForAdmin
        getOrgsForAdminResult = new ArrayList<Object>();
        obejct1 = new HashMap<String, Object>();
        obejct1.put("orgId", "1");
        obejct1.put("orgName", "test");
        obejct1.put("orgGuid", "f89b1ef6-7416-4d12-b492-c10fdaaff632");
        obejct1.put("created", null);
        obejct1.put("lastModified", null);
        obejct1.put("billingEnabled", false);
        obejct1.put("status", "active");
        getOrgsForAdminResult.add(obejct1);

        //testGetOrg
        getOrgResult = new ArrayList<Object>();
        obejct2 = new HashMap<String, Object>();
        obejct2.put("orgId", "1");
        obejct2.put("orgName", "test");
        obejct2.put("orgGuid", "f89b1ef6-7416-4d12-b492-c10fdaaff632");
        obejct2.put("created", null);
        obejct2.put("lastModified", null);
        obejct2.put("billingEnabled", false);
        obejct2.put("status", "active");
        getOrgsForAdminResult.add(obejct2);

        //testSelectInviteInfo
        selectInviteInfoResult = new ArrayList<Object>();
        obejct3 = new HashMap<String, Object>();
        obejct3.put("id", 1);
        obejct3.put("token", "65f1e2cf-2b3a-44c4-9d1d");
        obejct3.put("gubun", "1");
        obejct3.put("inviteId", 1);
        obejct3.put("roleName", "admin");
        obejct3.put("inviteUserId", "1");
        obejct3.put("userId", "admin");
        obejct3.put("createTime", null);
        obejct3.put("accessCnt", 3);
        obejct3.put("inviteName", "admin");
        obejct3.put("setyn", "Y");
        selectInviteInfoResult.add(obejct3);

        //testGetInviteUserList
        getInviteUserListResultMap = new HashMap<String, Object>();
        inviteUserList4 = new ArrayList<InviteUser>();
        inviteUser4 = new InviteUser();
        inviteUser4.setId(1);
        inviteUser4.setUserId("test@test.com");
        inviteUser4.setGubun("send");
        inviteUser4.setCreated(null);
        inviteUser4.setToken("UDBVZRVZJ8NB1RRWTFV");
        inviteUser4.setRole("{\"org\":[{\"om\":false,\"bm\":true,\"oa\":true}],\"space\":[{\"a14e4ffe-2cfb-4357-9000-e50572345b9c\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"190a1b87-fd61-4ac8-94b1-34bd8f5a54a2\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"c5edb50e-17d6-42e3-b393-8d5507d0527d\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"9e7d1e1d-2382-4b97-aef0-f1d2011adbf4\":[{\"sm\":false,\"sd\":false,\"sa\":false}]}]}");
        inviteUser4.setOrgGuid("b17a1072-6eec-4556-a8a5-34af7d676e3f");
        inviteUser4.setInvitename("admin");

        inviteUser4.getCreated();
        inviteUser4.getGubun();
        inviteUser4.getId();
        inviteUser4.getInviteName();
        inviteUser4.getOrgGuid();
        inviteUser4.getToken();
        inviteUser4.getRole();
        inviteUser4.getUserId();


        inviteUser4.toString();

        inviteUserList4.add(inviteUser4);

        organizationsCc = new OrganizationsCc();
        organizationsCc.setApplicationCount(1);
        organizationsCc.setBillingEnabled(true);
        organizationsCc.setCreatedAt(new Date());
        organizationsCc.setDefaultIsolationSegmentGuid("isolation");
        organizationsCc.setGuid("guid");
        organizationsCc.setId(1);
        organizationsCc.setName("name");
        organizationsCc.setSpaceCount(1);
        organizationsCc.setOrganizationCount(1);
        organizationsCc.setStatus("status");
        organizationsCc.setUpdatedAt(new Date());
        organizationsCc.setQuotaDefinitionId("guid");
        organizationsCc.setUserCount(1);


        organizationsCc.getApplicationCount();
        organizationsCc.getBillingEnabled();
        organizationsCc.getCreatedAt();
        organizationsCc.getDefaultIsolationSegmentGuid();
        organizationsCc.getGuid();
        organizationsCc.getId();
        organizationsCc.getName();
        organizationsCc.getOrganizationCount();
        organizationsCc.getQuotaDefinitionId();
        organizationsCc.getSpaceCount();
        organizationsCc.getStatus();
        organizationsCc.getUpdatedAt();
        organizationsCc.getUserCount();


        organizationsCc.toString();


        getInviteUserListResultMap.put("result", inviteUserList4);
    }

    @Test
    public void testGetOrgsForAdmin() throws Exception {
        thrown.expect(NullPointerException.class);

        List<Object> result = orgService.getOrgsForAdmin();
        Assert.assertEquals(getOrgsForAdminResult, result);
    }

    @Test
    public void testGetOrg() throws Exception {
        thrown.expect(NullPointerException.class);

        List<Object> result = orgService.getOrg("f89b1ef6-7416-4d12-b492-c10fdaaff632");
        Assert.assertEquals(getOrgsForAdminResult, result);
    }

    @Test
    public void testSelectInviteInfo() throws Exception {
        thrown.expect(NullPointerException.class);

        List result = orgService.selectInviteInfo("");
        Assert.assertEquals(selectInviteInfoResult, result);
    }

    @Test
    public void testGetInviteUserList() throws Exception {
        when(inviteUserRepository.findByInvitenameAndGubun(anyString(),anyString())).thenReturn(inviteUserList4);

        Map<?, ?> result = orgService.getInviteUserList("admin");
        Assert.assertEquals(getInviteUserListResultMap, result);
    }

    @Test
    public void testDeleteInvateUser() throws Exception {

        InviteUser inviteUser = new InviteUser();
        inviteUser.setId(1);
        inviteUser.setUserId("userid");
        inviteUser.setOrgGuid("orgguid");
        inviteUser.setRole("role");
        inviteUser.setToken("token");
        inviteUser.setInvitename("invitename");
        inviteUser.setGubun("gubun");
        inviteUser.setCreated(new Date());

        when(inviteUserRepository.findFirstByUserIdAndOrgGuid(anyString(), anyString())).thenReturn(inviteUser);
        doNothing().when(inviteUserRepository).delete(inviteUser);

        boolean result = orgService.deleteInvateUser("b17a1072-6eec-4556-a8a5-34af7d676e3f", "test@test.com");

        Assert.assertEquals(true, result);
    }
}
