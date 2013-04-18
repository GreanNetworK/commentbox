/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.utils;

import com.shipco.commentbox.service.EmailService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author wjirawong
 */
public class EmailUtils {

    private static EmailService emailService;

    static {
        emailService = SpringUtils.getBean(EmailService.class);
    }

    protected EmailUtils() {
    }

    public static void sendCommentBoxMail(String mailMessage) {
        sendCommentBoxMail(mailMessage, new ArrayList<UploadedFile>());
    }

    public static void sendCommentBoxMail(String mailMessage, List<UploadedFile> uploadedFiles) {
        SimpleDateFormat format = new SimpleDateFormat("d/M/y H:m ");
        String date = format.format(new Date());
        List<InternetAddress> recipients = emailService.getAllEmailInternetAddress();
        String subject = "Message from comment box on [" + date + "]";
        sendMail(subject, mailMessage, recipients, uploadedFiles);
    }
    
    public static void sendEmailMessage(String subject,String mailMessage,String recipient){
        List<String> recipients = new ArrayList<String>();
        recipients.add(recipient);
        sendEmailMessage(subject, mailMessage, recipients);
    }
    
    public static void sendEmailMessage(String subject,String mailMessage,List<String> recipients){
        List<InternetAddress> inets = new ArrayList<InternetAddress>();
        for(String recipient : recipients){
            try {
                InternetAddress inet = new InternetAddress(recipient);
                inets.add(inet);
            } catch (AddressException ex) {
                Logger.getLogger(EmailUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sendMail(subject, mailMessage, inets, new ArrayList<UploadedFile>());
    }

    private static void sendMail(String subject, String mailMessage, List<InternetAddress> recipients, List<UploadedFile> uploadedFiles) {
        final String username = "wjirawong";
        final String password = "inong75";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.shipco.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("commentbox.bdc@shipco.com"));
            message.setRecipients(Message.RecipientType.TO, recipients.toArray(new InternetAddress[recipients.size()]));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mailMessage, "text/html; charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);

            for (UploadedFile uploadedFile : uploadedFiles) {
                MimeBodyPart mimeAttachPart = new MimeBodyPart();
                mimeAttachPart.setFileName(uploadedFile.getFileName());
                mimeAttachPart.setContent(uploadedFile.getContents(), uploadedFile.getContentType());
                multipart.addBodyPart(mimeAttachPart);
            }

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
