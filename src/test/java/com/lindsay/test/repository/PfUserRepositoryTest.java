package com.lindsay.test.repository;

import com.lindsay.test.dataobject.PfUser;
import com.lindsay.test.utils.UUIDUtils;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * PfUserRepositoryTest
 *
 * @author Lindsay
 * @date 2019/9/11 14:35
 * @since 0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PfUserRepositoryTest {

  @Autowired
  private PfUserRepository pfUserRepository;

  @Test
  public void batchInsert() {
    List<PfUser> pfUserList = Lists.newArrayList();
    PfUser pfUser = new PfUser();
    pfUser.setUserId(UUIDUtils.getUuid());
    pfUserList.add(pfUser);
    pfUserRepository.batchInsert(pfUserList);
  }

}