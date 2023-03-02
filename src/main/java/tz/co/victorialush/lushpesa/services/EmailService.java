package tz.co.victorialush.lushpesa.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import tz.co.victorialush.lushpesa.models.Email;
import tz.co.victorialush.lushpesa.models.EmailGroup;

import java.io.File;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}") private String sender;

//    public void sendEmail(Email email){
//        SimpleMailMessage messenger = new SimpleMailMessage();
//        messenger.setFrom(sender);
//        messenger.setTo(email.getTo());
//        messenger.setSubject(email.getSubject());
//        messenger.setText(email.getBody());
//        mailSender.send(messenger);
//    }

    public void sendEmailWithAttachment(Email email, String fileName) {
        try{
            FileSystemResource file = new FileSystemResource(new File(email.getAttachment()));
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            String attachment = email.getAttachment();

            //If attachment is there place multipart otherwise ignore
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, attachment != null);
            helper.setFrom(sender);
            helper.setTo(email.getAddress());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody());

            //Only send an attachment if it's attached
            if(attachment != null){
                helper.addAttachment("report_"+fileName, file);
            }

            mailSender.send(mimeMessage);

            String cc = email.getCc();
            if(cc != null){
                //We split cc by space because we want to grab hold of different carbon copy recipients
                String []ccRecipiebts = cc.split(" ");
                helper.setCc(ccRecipiebts);
//                for(String ccRecipiebt:ccRecipiebts){
//                    Email ccEmail = new Email(email);
//                    ccEmail.setCc(null);
//                    ccEmail.setAddress(ccRecipiebt);
//                    sendEmailWithAttachment(ccEmail);
//                }
            }
        }catch (MessagingException exc){
        }
    }
}
