package com.lindsay.test.event;

import com.lindsay.test.dto.MailDto;
import org.springframework.context.ApplicationEvent;

/**
 * @Auther: Lindsay
 * @Date: 2018/12/1 10:24
 * @Description:
 */
public class SendMailEvent extends ApplicationEvent {

    private MailDto domain;

    public SendMailEvent(Object source, MailDto mailDto) {
        super(source);
        this.domain = mailDto;
    }

    public MailDto getDomain() {
        return this.domain;
    }
}
