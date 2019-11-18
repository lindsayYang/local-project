package com.lindsay.test.repository;

import java.util.List;

/**
 * JpaBatchRepository
 *
 * @author Lindsay
 * @date 2019/9/11 14:25
 **/
public interface JpaBatchRepository<T, ID> {

  void batchInsert(List list);

  void batchUpdate(List list);

}
