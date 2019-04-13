package com.lindsay.test.service.impl;

import com.lindsay.test.dataobject.PfUser;
import com.lindsay.test.dto.StockDto;
import com.lindsay.test.dto.UserDto;
import com.lindsay.test.repository.PfUserRepository;
import com.lindsay.test.service.UserService;
import com.lindsay.test.utils.JsonUtils;
import com.lindsay.test.utils.RedisUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/26 19:05
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PfUserRepository pfUserRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<UserDto> getListByDepartmentId(String departmentId) {
        List<UserDto> userDtoList = new ArrayList<>();
        List<PfUser> byDepartmentId = pfUserRepository.findByDepartmentId(departmentId);
        byDepartmentId.forEach(dto -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(dto, userDto);
            userDtoList.add(userDto);
        });
        return userDtoList;
    }

    @Override
    public List<UserDto> getListByPagination(Integer currentPage) {
        String sqlStr = "SELECT * FROM `pf_user` ORDER BY create_time DESC LIMIT " + (currentPage - 1) * 10 + "," + 10;
        List<PfUser> pfUserList = entityManager.createNativeQuery(sqlStr, PfUser.class).getResultList();
        List<UserDto> userDtoList = new ArrayList<>();
        pfUserList.forEach(dto -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(dto, userDto);
            userDtoList.add(userDto);
        });
        try {
            redisUtils.set("list", JsonUtils.obj2json(userDtoList), 60L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDtoList;
    }

    @Override
    public List<List<Object>> getListByExcel() {
        try {
            List list = new ArrayList<>();
            Resource resource = new ClassPathResource("excel/副本附件3-驻场-工作月报-2017 6月-杨生.xlsx");
            FileInputStream fileInputStream = new FileInputStream(resource.getFile());
            Workbook work = new XSSFWorkbook(fileInputStream);
            if (null == work) {
                throw new Exception("创建Excel工作薄为空！");
            }
            Sheet sheet = null;
            Row row = null;
            Cell cell = null;

            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                sheet = work.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }

                for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row == null || row.getFirstCellNum() == j) {
                        continue;
                    }

                    List<Object> li = new ArrayList<>();
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        li.add(cell);
                    }
                    list.add(li);
                }
            }
            work.close();
            fileInputStream.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StockDto getRedis() {
        String str = redisUtils.get("material:dev:SHOP_MATERIAL_STOCK_110_2");
        try
        {

            return JsonUtils.json2pojo(str, StockDto.class);
        } catch (Exception e) {
            e.printStackTrace();
return null;
        }
    }
}
