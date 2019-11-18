package com.lindsay.test.repository;

import com.lindsay.test.utils.ModelToSQL;
import com.lindsay.test.utils.ModelToSQL.SqlType;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.util.CollectionUtils;

/**
 * JpaBatchRepositoryImpl
 *
 * @author Lindsay
 * @date 2019/9/11 14:27
 **/
@Slf4j
public class JpaBatchRepositoryImpl implements JpaBatchRepository {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public void batchInsert(List list) {
    if (CollectionUtils.isEmpty(list)) {
      return;
    }

    ModelToSQL modelToSQL = new ModelToSQL(SqlType.INSERT, list.get(0));
    String sql = modelToSQL.getSqlBuffer();
    log.info("make sql {} Param {}", sql, list);
    namedParameterJdbcTemplate
        .batchUpdate(sql, SqlParameterSourceUtils.createBatch(list));
  }

  @Override
  public void batchUpdate(List list) {
    if (CollectionUtils.isEmpty(list)) {
      return;
    }

    ModelToSQL modelToSQL = new ModelToSQL(SqlType.UPDATE, list.get(0));
    String sql = modelToSQL.getSqlBuffer();
    log.info("make sql {} Param {}", sql, list);
    namedParameterJdbcTemplate
        .batchUpdate(sql, SqlParameterSourceUtils.createBatch(list));
  }

}
