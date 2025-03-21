package net.tutorial.utils;


import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.net.URL;
import java.util.*;

@Slf4j
public class EmailSender {
    private static final String TAG = "EmailSender";
    private static Session session;
    private static String user;

    private MimeMessage msg;
    private String text;
    private String html;
    private List<MimeBodyPart> attachments = new ArrayList<MimeBodyPart>();

    private EmailSender() {
        EmailSender.config(EmailSender.SMTP_163(false), "top_3121@163.com", "HFDRWKCANUXPGEXD");
    }

    public static Properties defaultConfig(Boolean debug) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", null != debug ? debug.toString() : "false");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.port", "465");
        return props;
    }

    /**
     * smtp entnterprise qq
     *
     * @param debug
     * @return
     */
    public static Properties SMTP_ENT_QQ(boolean debug) {
        Properties props = defaultConfig(debug);
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
        return props;
    }

    /**
     * smtp qq
     *
     * @param debug enable debug
     * @return
     */
    public static Properties SMTP_QQ(boolean debug) {
        Properties props = defaultConfig(debug);
        props.put("mail.smtp.host", "smtp.qq.com");
        return props;
    }

    /**
     * smtp 163
     *
     * @param debug enable debug
     * @return
     */
    public static Properties SMTP_163(Boolean debug) {
        Properties props = defaultConfig(debug);
        props.put("mail.smtp.host", "smtp.163.com");
        return props;
    }

    /**
     * config username and password
     *
     * @param props    email property config
     * @param username email auth username
     * @param password email auth password
     */
    public static void config(Properties props, final String username, final String password) {
        props.setProperty("username", username);
        props.setProperty("password", password);
        config(props);
    }

    public static void config(Properties props) {
        final String username = props.getProperty("username");
        final String password = props.getProperty("password");
        user = username;
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * set email subject
     *
     * @param subject subject title
     */
    public static EmailSender subject(String subject) {
        EmailSender EmailSender = new EmailSender();
        EmailSender.msg = new MimeMessage(session);
        try {
            EmailSender.msg.setSubject(subject, "UTF-8");
        } catch (Exception e) {
            log.info(TAG, e);
        }
        return EmailSender;
    }

    /**
     * set email from
     *
     * @param nickName from nickname
     */
    public EmailSender from(String nickName) {
        return from(nickName, user);
    }

    /**
     * set email nickname and from user
     *
     * @param nickName from nickname
     * @param from     from email
     */
    public EmailSender from(String nickName, String from) {
        try {
            String encodeNickName = MimeUtility.encodeText(nickName);
            msg.setFrom(new InternetAddress(encodeNickName + " <" + from + ">"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public EmailSender replyTo(String... replyTo) {
        String result = Arrays.asList(replyTo).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", ",");
        try {
            msg.setReplyTo(InternetAddress.parse(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public EmailSender replyTo(String replyTo) {
        try {
            msg.setReplyTo(InternetAddress.parse(replyTo.replace(";", ",")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public EmailSender to(String... to) throws MessagingException {
        return addRecipients(to, Message.RecipientType.TO);
    }

    public EmailSender to(String to) throws MessagingException {
        return addRecipient(to, Message.RecipientType.TO);
    }

    public EmailSender cc(String... cc) throws MessagingException {
        return addRecipients(cc, Message.RecipientType.CC);
    }

    public EmailSender cc(String cc) throws MessagingException {
        return addRecipient(cc, Message.RecipientType.CC);
    }

    public EmailSender bcc(String... bcc) throws MessagingException {
        return addRecipients(bcc, Message.RecipientType.BCC);
    }

    public EmailSender bcc(String bcc) throws MessagingException {
        return addRecipient(bcc, Message.RecipientType.BCC);
    }

    private EmailSender addRecipients(String[] recipients, Message.RecipientType type) throws MessagingException {
        String result = Arrays.asList(recipients).toString().replace("(^\\[|\\]$)", "").replace(", ", ",");
        msg.setRecipients(type, InternetAddress.parse(result));
        return this;
    }

    private EmailSender addRecipient(String recipient, Message.RecipientType type) throws MessagingException {
        msg.setRecipients(type, InternetAddress.parse(recipient.replace(";", ",")));
        return this;
    }

    public EmailSender text(String text) {
        this.text = text;
        return this;
    }

    public EmailSender html(String html) {
        this.html = html;
        return this;
    }

    public EmailSender attach(File file) {
        attachments.add(createAttachment(file, null));
        return this;
    }

    public EmailSender attach(File file, String fileName) {
        attachments.add(createAttachment(file, fileName));
        return this;
    }

    public EmailSender attachURL(URL url, String fileName) {
        attachments.add(createURLAttachment(url, fileName));
        return this;
    }

    private MimeBodyPart createAttachment(File file, String fileName) {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(file);
        try {
            attachmentPart.setDataHandler(new DataHandler(fds));
            attachmentPart.setFileName(null == fileName ? MimeUtility.encodeText(fds.getName()) : MimeUtility.encodeText(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attachmentPart;
    }

    private MimeBodyPart createURLAttachment(URL url, String fileName) {
        MimeBodyPart attachmentPart = new MimeBodyPart();

        DataHandler dataHandler = new DataHandler(url);
        try {
            attachmentPart.setDataHandler(dataHandler);
            attachmentPart.setFileName(null == fileName ? MimeUtility.encodeText(fileName) : MimeUtility.encodeText(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attachmentPart;
    }

    public void send() {
        if (text == null && html == null) {
            throw new IllegalArgumentException("At least one context has to be provided: Text or Html");
        }

        MimeMultipart cover;
        boolean usingAlternative = false;
        boolean hasAttachments = attachments.size() > 0;

        try {
            if (text != null && html == null) {
                // TEXT ONLY
                cover = new MimeMultipart("mixed");
                cover.addBodyPart(textPart());
            } else if (text == null && html != null) {
                // HTML ONLY
                cover = new MimeMultipart("mixed");
                cover.addBodyPart(htmlPart());
            } else {
                // HTML + TEXT
                cover = new MimeMultipart("alternative");
                cover.addBodyPart(textPart());
                cover.addBodyPart(htmlPart());
                usingAlternative = true;
            }

            MimeMultipart content = cover;
            if (usingAlternative && hasAttachments) {
                content = new MimeMultipart("mixed");
                content.addBodyPart(toBodyPart(cover));
            }

            for (MimeBodyPart attachment : attachments) {
                content.addBodyPart(attachment);
            }

            msg.setContent(content);
            msg.setSentDate(new Date());
            Transport.send(msg);
            log.info("email send...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MimeBodyPart toBodyPart(MimeMultipart cover) throws MessagingException {
        MimeBodyPart wrap = new MimeBodyPart();
        wrap.setContent(cover);
        return wrap;
    }

    private MimeBodyPart textPart() throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(text);
        return bodyPart;
    }

    private MimeBodyPart htmlPart() throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(html, "text/html; charset=utf-8");
        return bodyPart;
    }

    public static void sendRegisterVerifyCode(String code, String address) throws Exception {
        EmailSender.subject("教程网注册验证码")
                .from("教程网")
                .text("您的验证码是：" + code + " 有效期为10分钟，若非本人操作，请忽略此邮件.")
                .to(address)
                .send();
    }
}
