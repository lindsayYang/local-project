package com.lindsay.test.utils;

import java.util.UUID;

/**
 * UUIDUtils
 *
 * @author Lindsay
 * @date 2019/9/11 15:34
 **/
public class UUIDUtils {

  private UUIDUtils() {
    throw new IllegalStateException("不能实例化");
  }

  /**
   * 获取UUID
   */
  public static String getUuid() {
    return UUID.randomUUID().toString().replace("-", "").toLowerCase();
  }

}
