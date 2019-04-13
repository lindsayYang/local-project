package com.lindsay.test.utils;

import com.lindsay.test.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Auther: Lindsay
 * @Date: 2018/12/1 10:01
 * @Description:
 */
@Component
public class MailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(MailDto mailDto){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailDto.getFrom());
            message.setTo(mailDto.getTo());
            message.setSubject(mailDto.getSubject());
            message.setText(mailDto.getText());

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
