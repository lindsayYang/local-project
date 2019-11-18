package com.lindsay.test.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.lindsay.test.dto.EasyPOIModel;
import com.lindsay.test.dto.MailDto;
import com.lindsay.test.dto.PriceDto;
import com.lindsay.test.dto.ResultVO;
import com.lindsay.test.event.SendMailEvent;
import com.lindsay.test.service.UserService;
import com.lindsay.test.utils.RedisUtils;
import com.lindsay.test.utils.TimeUtlis;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(
            "当前楼盘:    " + v.getDesc() + " , " + v.getNum() + " , 价格 = " + v.getPrice() + "元/平");
        System.out.println("------------");
      });


    } catch (Exception e) {

    }

    return success();
  }

  @RequestMapping("/export")
  public ResponseEntity<?> export(
      HttpServletResponse response) throws Exception {
    // 查询数据,此处省略
    List<EasyPOIModel> list = new ArrayList<>();
    EasyPOIModel easyPOIModel11 = new EasyPOIModel(1, "信科", "张三", "男", 20);
    EasyPOIModel easyPOIModel12 = new EasyPOIModel(2, "信科", "李四", "男", 17);
    list.add(easyPOIModel11);
    list.add(easyPOIModel12);
    Map<String, Object> data = new HashMap<>();
    data.put("list", list);

    // 获取导出excel指定模版
    Resource resource = new ClassPathResource("excel/实时库存报表.xlsx");
    TemplateExportParams params = new TemplateExportParams(
        ((ClassPathResource) resource).getPath());
    /*// 标题开始行
    params.setHeadingStartRow(0);
    // 标题行数
    params.setHeadingRows(2);
    // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
    params.setSheetName("班级信息");
    params.setHeadingRows(2);
    params.setHeadingStartRow(2);
    params.setTempParams("t");*/

    response.setContentType("application/vnd.ms-excel;charset=utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Content-disposition",
        "attachment;filename=" + java.net.URLEncoder.encode("啦啦啦", "UTF-8")
            + ".xls");

    // todo
    data = null;
    Workbook workbook = ExcelExportUtil.exportExcel(params, data);
    workbook.write(response.getOutputStream());
    response.flushBuffer();
    workbook.close();
    return ResponseEntity.ok().body("");
  }

  @RequestMapping("/export1")
  public ResponseEntity<?> export1(
      HttpServletResponse response) throws Exception {
    return exportExcelWithTemplate(response, null);
  }

  private ResponseEntity<?> exportExcelWithTemplate(
      HttpServletResponse response, Map<String, Object> map) throws Exception {
    Resource resource = new ClassPathResource("excel/实时库存报表.xlsx");
    TemplateExportParams params = new TemplateExportParams(
        ((ClassPathResource) resource).getPath());
    response.setContentType("application/vnd.ms-excel;charset=utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Content-disposition",
        "attachment;filename=" + java.net.URLEncoder.encode("啦啦啦", "UTF-8")
            + ".xls");
    Workbook workbook = ExcelExportUtil.exportExcel(params, map);
    workbook.write(response.getOutputStream());
    response.flushBuffer();
    workbook.close();
    return ResponseEntity.ok().body("");
  }

}
