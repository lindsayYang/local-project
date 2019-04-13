package com.lindsay.test.controller;

import com.lindsay.test.dto.MailDto;
import com.lindsay.test.dto.PriceDto;
import com.lindsay.test.dto.ResultVO;
import com.lindsay.test.dto.StockDto;
import com.lindsay.test.event.SendMailEvent;
import com.lindsay.test.service.UserService;
import com.lindsay.test.utils.RedisUtils;
import com.lindsay.test.utils.TimeUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/26 18:47
 * @Description:
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/get-list")
    public ResultVO getList(@RequestParam("current_page") Integer currentPage) {
        return success(userService.getListByPagination(currentPage));
    }

    @GetMapping("/send-mail")
    public ResultVO testMail() throws Exception {
        System.out.println(TimeUtlis.getTimeSpan());
        MailDto dto = new MailDto();
        dto.setFrom("1318268897@qq.com");
        dto.setTo("yangsheng@chupinxiu.com");
        dto.setSubject("哈哈哈测试=====--------");
        dto.setText("这是测试内容哦");
        SendMailEvent event = new SendMailEvent(this, dto);
        applicationEventPublisher.publishEvent(event);
        System.out.println(TimeUtlis.getTimeSpan());
        return success();
    }

    @GetMapping("/test-set")
    public ResultVO test(String name) {
        redisUtils.set("name", name, 5);
        return success(null);
    }

    @GetMapping("/test-get")
    public ResultVO test1() {
        return success(redisUtils.get("name"));
    }

    @GetMapping("/l")
    public ResultVO l() {
        try {
            Resource resource = new ClassPathResource("static/price.txt");
            FileInputStream fileInputStream = new FileInputStream(resource.getFile());
            int length;
            byte[] bytes = new byte[1024];
            String res = "";
            while ((length = fileInputStream.read(bytes)) != -1) {
                res += new String(bytes, 0, length);
            }
            fileInputStream.close();//关闭流

            res = res.replaceAll("\n", "").replaceAll("\r", "");

            List<PriceDto> priceDtos = new ArrayList<>();

            String[] splits = res.split("=");
            for (String spl : splits) {
                String[] strs = spl.split(",");

                PriceDto priceDto = new PriceDto();
                priceDto.setDesc(strs[0]);
                priceDto.setNum(strs[1]);
                priceDto.setPrice(strs[2]);
                priceDtos.add(priceDto);

            }

            List<PriceDto> collect = priceDtos.stream().sorted((x, y) -> {
                return x.getPrice().compareTo(y.getPrice());
            }).collect(Collectors.toList());

            collect.forEach(v -> {
                System.out.println("当前楼盘:    " + v.getDesc() + " , " + v.getNum() + " , 价格 = " + v.getPrice() + "元/平");
                System.out.println("------------");
            });



        } catch (Exception e) {

        }

        return success();
    }

}
