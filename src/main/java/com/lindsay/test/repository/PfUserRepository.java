package com.lindsay.test.repository;

import com.lindsay.test.dataobject.PfUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * PfUserRepository
 *
 * @auther Lindsay
 * @date 2018/11/26 19:02
 */
@Repository
public interface PfUserRepository extends JpaRepository<PfUser, String>,
    JpaSpecificationExecutor<PfUser>, JpaBatchRepository<PfUser, String> {

  List<PfUser> findByDepartmentId(String departmentId);

}
