package com.lindsay.test.dto;

import lombok.Data;

/**
 * @Auther: Lindsay
 * @Date: 2018/12/1 10:22
 * @Description:
 */
@Data
public class MailDto {

    private String from;

    private String to;

    private String subject;

    private String text;

}
