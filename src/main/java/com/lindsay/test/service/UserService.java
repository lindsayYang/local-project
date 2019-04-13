package com.lindsay.test.service;

import com.lindsay.test.dataobject.PfUser;
import com.lindsay.test.dto.StockDto;
import com.lindsay.test.dto.UserDto;

import java.util.List;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/26 19:03
 * @Description:
 */
public interface UserService {

    List<UserDto> getListByDepartmentId(String departmentId);

    List<UserDto> getListByPagination(Integer currentPage);

    List<List<Object>> getListByExcel();

    StockDto getRedis();

}
