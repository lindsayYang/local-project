package com.lindsay.test.listener;

import com.lindsay.test.event.SendMailEvent;
import com.lindsay.test.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Auther: Lindsay
 * @Date: 2018/12/1 10:26
 * @Description:
 */
@Component
public class SendMailEventListener implements ApplicationListener<SendMailEvent> {

    @Autowired
    private MailUtils mailUtils;

    @Async
    @Override
    public void onApplicationEvent(SendMailEvent sendMailEvent){
        mailUtils.sendSimpleMail(sendMailEvent.getDomain());
    }
}
