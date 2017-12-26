package top.greathead.jk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {
    @Autowired
    private SimpleMailMessage mailMessage;
    @Autowired
    private JavaMailSender mailSender;


    public void send(String title, String text, String to){
        mailMessage.setSubject(title);
        mailMessage.setText(text);
        mailMessage.setTo(to);
        mailSender.send(mailMessage);
        //System.out.println("sending mail...");
    }
}
