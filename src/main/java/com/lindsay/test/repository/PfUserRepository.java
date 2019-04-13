package com.lindsay.test.repository;

import com.lindsay.test.dataobject.PfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/26 19:02
 * @Description:
 */
public interface PfUserRepository extends JpaRepository<PfUser, String>, JpaSpecificationExecutor<PfUser> {

    List<PfUser> findByDepartmentId(String departmentId);

}
