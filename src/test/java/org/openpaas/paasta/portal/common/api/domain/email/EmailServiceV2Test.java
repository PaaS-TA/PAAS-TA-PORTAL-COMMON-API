package org.openpaas.paasta.portal.common.api.domain.email;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.paasta.portal.common.api.config.EmailConfig;
import org.openpaas.paasta.portal.common.api.entity.portal.InviteUser;
import org.openpaas.paasta.portal.common.api.repository.portal.InviteUserRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.mockito.Mockito.when;

/**
 * Created by indra on 2018-06-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailServiceV2Test {

    @Mock
    EmailConfig emailConfig;

    @Mock
    InviteUserRepository inviteUserRepository;

    @InjectMocks
    EmailService emailService;


    String charset;

    Map resetEmailResultMap;

    Map createEmailResultMap;

    Map param3;

    Map inviteAcceptResultMap;
    Map param4;

    Map inviteAcceptUpdateResultMap;
    Map param5;

    Map inviteOrgEmailSendResultMap;


    InviteUser inviteUser;
    List<InviteUser> inviteUsers;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setTestData();
    }

    private void setTestData() {

        ReflectionTestUtils.setField(emailConfig, "charset", "UTF-8");
        ReflectionTestUtils.setField(emailConfig, "auth", "auth");
        ReflectionTestUtils.setField(emailConfig, "inviteUrl", "invite");


        //testResetEmail
        resetEmailResultMap = new HashMap();
        resetEmailResultMap.put("result", true);
        resetEmailResultMap.put("msg", "You have successfully completed the task.");

        //testCreateEmail
        createEmailResultMap = new HashMap();
        createEmailResultMap.put("result", true);
        createEmailResultMap.put("msg", "You have successfully completed the task.");

        //testInviteOrgEmail
        param3 = new HashMap();
        param3.put("userEmail", "test@test.com");
        param3.put("orgId", "b17a1072-6eec-4556-a8a5-34af7d676e3f");
        param3.put("userRole", "{\"org\":[{\"om\":false,\"bm\":true,\"oa\":true}],\"space\":[{\"a14e4ffe-2cfb-4357-9000-e50572345b9c\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"190a1b87-fd61-4ac8-94b1-34bd8f5a54a2\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"c5edb50e-17d6-42e3-b393-8d5507d0527d\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"9e7d1e1d-2382-4b97-aef0-f1d2011adbf4\":[{\"sm\":false,\"sd\":false,\"sa\":false}]}]}");
        param3.put("invitename", "admin");
        param3.put("orgName", "test");

        //testInviteAccept
        inviteAcceptResultMap = new HashMap();
        param4 = new HashMap();
        param4.put("token", "65f1e2cf-2b3a-44c4-9d1d");
        inviteAcceptResultMap.put("id", 1);
        inviteAcceptResultMap.put("role", "{\"org\":[{\"om\":false,\"bm\":true,\"oa\":true}],\"space\":[{\"a14e4ffe-2cfb-4357-9000-e50572345b9c\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"190a1b87-fd61-4ac8-94b1-34bd8f5a54a2\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"c5edb50e-17d6-42e3-b393-8d5507d0527d\":[{\"sm\":false,\"sd\":true,\"sa\":true}],\"9e7d1e1d-2382-4b97-aef0-f1d2011adbf4\":[{\"sm\":false,\"sd\":false,\"sa\":false}]}]}");
        inviteAcceptResultMap.put("orgGuid", "b17a1072-6eec-4556-a8a5-34af7d676e3f");
        inviteAcceptResultMap.put("userId", "admin");
        inviteAcceptResultMap.put("result", true);

        //testInviteAcceptUpdate
        inviteAcceptUpdateResultMap = new HashMap();
        param5 = new HashMap();
        param5.put("id", "1");
        param5.put("gubun", "send");
        inviteAcceptUpdateResultMap.put("result", true);

        //testInviteOrgEmailSend
        inviteOrgEmailSendResultMap = new HashMap();
        inviteOrgEmailSendResultMap.put("result", true);
        inviteOrgEmailSendResultMap.put("msg", "You have successfully completed the task.");

        inviteUsers = new ArrayList<>();
        inviteUser = new InviteUser();

        inviteUser.setUserId("userid");
        inviteUser.setToken("token");
        inviteUser.setRole("role");
        inviteUser.setOrgGuid("guid");
        inviteUser.setInvitename("invitename");
        inviteUser.setGubun("gubun");
        inviteUser.setCreated(new Date());


        inviteUsers.add(inviteUser);


    }

    @Test
    public void testResetEmail() throws Exception {
        when(emailConfig.getCharset()).thenReturn("UTF-8");
        when(emailConfig.sendEmail(Matchers.any(), Matchers.any())).thenReturn(true);
        Map result = emailService.resetEmail("userId", "refreshToken");
        Assert.assertEquals(resetEmailResultMap, result);
    }

    @Test
    public void testCreateEmail() throws Exception {
        when(emailConfig.getCharset()).thenReturn("UTF-8");
        when(emailConfig.sendEmail(Matchers.any(), Matchers.any())).thenReturn(true);
        Map result = emailService.createEmail("userId", "refreshToken");
        Assert.assertEquals(createEmailResultMap, result);
    }

    @Test
    public void testInviteOrgEmail() throws Exception {
        when(emailConfig.getCharset()).thenReturn("UTF-8");
        when(emailConfig.sendEmail(Matchers.any(), Matchers.any())).thenReturn(true);
        Boolean result = emailService.inviteOrgEmail(param3);
        Assert.assertEquals(Boolean.TRUE, result);
    }



    @Test
    public void testResetEmail_false() throws Exception {
        when(emailConfig.getCharset()).thenReturn("UTF-8");
        when(emailConfig.sendEmail(Matchers.any(), Matchers.any())).thenReturn(false);
        Map result = emailService.resetEmail("userId", "refreshToken");
        Assert.assertNotNull(result);
    }

    @Test
    public void testCreateEmail_false() throws Exception {
        when(emailConfig.getCharset()).thenReturn("UTF-8");
        when(emailConfig.sendEmail(Matchers.any(), Matchers.any())).thenReturn(false);
        Map result = emailService.createEmail("userId", "refreshToken");
        Assert.assertNotNull(result);
    }

    @Test
    public void testInviteOrgEmail_false() throws Exception {
        when(emailConfig.getCharset()).thenReturn("UTF-8");
        when(emailConfig.sendEmail(Matchers.any(), Matchers.any())).thenReturn(false);
        Boolean result = emailService.inviteOrgEmail(param3);
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testInviteAccept() throws Exception {
        when(inviteUserRepository.findByTokenAndGubunNot(Matchers.any(), Matchers.any())).thenReturn(inviteUsers);

        Map result = emailService.inviteAccept(param4);
        Assert.assertNotNull(result);
    }

    @Test
    public void testInviteAcceptUpdate() throws Exception {
        Map result = emailService.inviteAcceptUpdate(param5);
        Assert.assertNotNull(result);
    }

    @Test
    public void testInviteOrgEmailSend() throws Exception {
        when(emailConfig.getCharset()).thenReturn("UTF-8");
        when(emailConfig.getAuth()).thenReturn("auth");
        when(emailConfig.getInviteUrl()).thenReturn("invite");

        when(emailConfig.sendEmail(Matchers.any(), Matchers.any())).thenReturn(true);
        Map result = emailService.inviteOrgEmailSend("1", "test", "UDBVZRVZJ8NB1RRWTFV");
        Assert.assertEquals(inviteOrgEmailSendResultMap, result);
    }


}
