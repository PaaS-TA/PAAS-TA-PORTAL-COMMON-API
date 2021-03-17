package org.openpaas.paasta.portal.common.api.config;


import org.openpaas.paasta.portal.common.api.domain.email.EmailController;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class EmailConfig {


    private static final Logger logger = getLogger(EmailController.class);

    @Value("${mail.smtp.host}")
    String host;

    @Value("${mail.smtp.port}")
    int port;

    @Value("${mail.smtp.username}")
    String username;

    @Value("${mail.smtp.password}")
    String password;

    @Value("${mail.smtp.useremail}")
    String useremail;

    @Value("${mail.smtp.properties.auth}")
    String auth;

    @Value("${mail.smtp.properties.starttls.enable}")
    String starttls_enable;

    @Value("${mail.smtp.properties.starttls.required}")
    String starttls_required;

    @Value("${mail.smtp.properties.maximumTotalQps}")
    String maximumTotalQps;

    @Value("${mail.smtp.properties.subject}")
    String subject;

    @Value("${mail.smtp.properties.authUrl}")
    String authUrl;

    @Value("${mail.smtp.properties.createUrl}")
    String createUrl;

    @Value("${mail.smtp.properties.expiredUrl}")
    String expiredUrl;

    @Value("${mail.smtp.properties.charset}")
    String charset;

    @Value("${mail.smtp.properties.inviteUrl}")
    String inviteUrl;


    public boolean sendEmail(String to, String contents) {
        Boolean bRtn = false;
        try {
            //ByteArrayOutputStream os = new ByteArrayOutputStream();
            // 인증
            Authenticator auth = auth();

            // 메일 세션
            Session session = Session.getInstance(properties(), auth);
            session.setDebug(true);

            logger.info("MimeMessage create :::: ");
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/html; charset=" + charset);
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(contents, "text/html")));
            msg.setSentDate(new Date());
            msg.setSubject(MimeUtility.encodeText(subject));
            msg.setContent(contents, "text/html; charset=" + charset);
            msg.setFrom(new InternetAddress(to, username));
            msg.setReplyTo(InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            logger.info("Sending :::: " + to);
            Transport.send(msg);


            bRtn = true;
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
            logger.info("ERROR " + e.getMessage());
            bRtn = false;
        }
        return bRtn;

    }

    @Bean
    public MailProperties mailProperties() {

        /*
        MailProperties mailProperties = new MailProperties() {{
            setHost(host);
            setPort(port);
            setUsername(useremail);
            setPassword(password);
            setDefaultEncoding( Charset.forName(charset) );
        }};
        */
        MailProperties mailProperties = new MailProperties();
        mailProperties.setHost(host);
        mailProperties.setPort(port);
        mailProperties.setUsername(useremail);
        mailProperties.setPassword(password);
        mailProperties.setDefaultEncoding(Charset.forName(charset));

        return mailProperties;
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties().getHost());
        if (mailProperties().getPort() != null) {
            mailSender.setPort(mailProperties().getPort());
        }
        mailSender.setUsername(mailProperties().getUsername());
        mailSender.setPassword(mailProperties().getPassword());
        return mailSender;
    }

    @Bean
    public Properties properties() {
        MailProperties mailProperties = mailProperties();
        Properties props = new Properties() {{
            // SSL 사용하는 경우
            put("mail.smtp.host", mailProperties.getHost()); //SMTP Host
            put("mail.smtp.socketFactory.port", mailProperties.getPort()); //SSL Port
            put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
            put("mail.smtp.port", mailProperties.getPort());
            put("mail.smtp.auth", auth); //Enabling SMTP Authentication
            put("mail.smtp.maximumTotalQps", mailProperties);
            put("mail.smtp.subject", subject);
            put("mail.smtp.username", username);
            put("mail.smtp.userEmail", useremail);
            put("mail.smtp.charset", mailProperties.getDefaultEncoding());
//            put("mail.debug", "true");
        }};
        return props;
    }

    // 인증
    @Bean
    public Authenticator auth() {
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                MailProperties mailProperties = mailProperties();
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }

        };
        return auth;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getStarttls_enable() {
        return starttls_enable;
    }

    public void setStarttls_enable(String starttls_enable) {
        this.starttls_enable = starttls_enable;
    }

    public String getStarttls_required() {
        return starttls_required;
    }

    public void setStarttls_required(String starttls_required) {
        this.starttls_required = starttls_required;
    }

    public String getMaximumTotalQps() {
        return maximumTotalQps;
    }

    public void setMaximumTotalQps(String maximumTotalQps) {
        this.maximumTotalQps = maximumTotalQps;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getCreateUrl() {
        return createUrl;
    }

    public void setCreateUrl(String createUrl) {
        this.createUrl = createUrl;
    }

    public String getExpiredUrl() {
        return expiredUrl;
    }

    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCharset () { return charset; }

    public void setCharset ( String charset ) { this.charset = charset; }

    public String getInviteUrl () { return inviteUrl; }

    public void setInviteUrl ( String inviteUrl ) { this.inviteUrl = inviteUrl; }
}
