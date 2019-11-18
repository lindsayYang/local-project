package com.lindsay.test.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Id;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @author pengzai
 * @Description: ${END}
 * @date 2019/9/5 6:16 PM
 */
public class ModelToSQL {

  private Object target;

  private String idName;

  private Object idValue;

  private SqlType currentType;

  public enum SqlType {
    INSERT, UPDATE, DELETE
  }

  public ModelToSQL(SqlType sqlType, Object target) {
    this.target = target;
    switch (sqlType) {
      case INSERT:
        currentType = SqlType.INSERT;
        createInsert();
        break;
      case UPDATE:
        currentType = SqlType.UPDATE;
        createUpdate();
        break;
      case DELETE:
        currentType = SqlType.DELETE;
        createDelete();
        break;
    }
  }

  public ModelToSQL(Class<?> target) {
    String tableName = getTableNameForClass(target);
    getFields(target);

    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("DELETE FROM ").append(tableName).append(" WHERE ");
    for (Field field : fields) {
      if (!Modifier.isStatic(field.getModifiers())) {
        Id id = field.getAnnotation(Id.class);
        if (null != id) {
          sqlBuffer.append(field.getName()).append("=?");
        }
      }
    }
    this.sqlBuffer = sqlBuffer.toString();
  }

  /**
   * 创建跟删除
   */
  private void createDelete() {
    String tableName = getTableName();
    getFields(target.getClass());
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("DELETE FROM ").append(tableName).append(" WHERE ");
    for (Field field : fields) {
      if (!Modifier.isStatic(field.getModifiers())) {
        Id id = field.getAnnotation(Id.class);
        if (null != id) {
          sqlBuffer.append(field.getName()).append(" = ? ");
          param.add(readField(field));
        }
      }
    }
    System.err.println("delete:\t" + sqlBuffer.toString());
    this.sqlBuffer = sqlBuffer.toString();
  }

  protected Object readField(Field field) {
    try {
      return FieldUtils.readField(field, target, true);
    } catch (Exception e) {
      throw new RuntimeException(currentType.name(), e);
    }
  }

  /**
   * 创建更新语句
   */
  private void createUpdate() {
    String tableName = getTableName();
    getFields(target.getClass());
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("UPDATE ").append(tableName).append(" SET ");

    for (Field field : fields) {
      if (!Modifier.isStatic(field.getModifiers())) {
        Id id = field.getAnnotation(Id.class);
        if (id == null) {
          sqlBuffer.append(humpToLine2(field.getName())).append("=:" + field.getName() + " , ");
          param.add(readField(field));
        } else {
          idName = field.getName();
          idValue = readField(field);
        }
      }
    }
    sqlBuffer.replace(sqlBuffer.length() - 2, sqlBuffer.length() - 1, " ");
    if (idName == null) {
      throw new RuntimeException("not found of " + target.getClass() + "'s ID");
    }
    sqlBuffer.append(" WHERE ").append(idName).append("=:" + idName);
    param.add(idValue);
    System.err.println("update:\t" + sqlBuffer.toString());
    this.sqlBuffer = sqlBuffer.toString();
  }


  private static Pattern humpPattern = Pattern.compile("[A-Z]");

  /**
   * 驼峰转下划线,效率比上面高
   */
  public static String humpToLine(String str) {
    Matcher matcher = humpPattern.matcher(str);
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  /**
   * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
   */
  public static String humpToLine2(String str) {
    return str.replaceAll("[A-Z]", "_$0").toLowerCase();
  }

  /**
   * 根据注解获取表名
   */
  private String getTableName() {
    String tableName = null;
    Class<?> clazz = target.getClass();
    tableName = getTableNameForClass(clazz);
    return humpToLine2(tableName.trim());
  }

  private String getTableNameForClass(Class<?> clazz) {
    String tableName;
    Table table = clazz.getAnnotation(Table.class);
    if (null != table) {
      tableName = table.name();
      if ("".equalsIgnoreCase(tableName)) {
        tableName = clazz.getSimpleName();
      }
    } else {
      tableName = clazz.getSimpleName();
    }
    return humpToLine2(tableName.trim()).substring(1);
  }

  /**
   * 创建插入语句
   */
  private void createInsert() {
    String tableName = getTableName();
    getFields(target.getClass());
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("INSERT INTO ").append(tableName).append("(");

    for (Field field : fields) {
      if (!Modifier.isStatic(field.getModifiers())) {
        // TODO 优化
        //Id id = field.getAnnotation(Id.class);
        //if (id == null) {
        sqlBuffer.append(humpToLine2(field.getName())).append(",");
        param.add(readField(field));
        //}
      }
    }
    int length = sqlBuffer.length();
    sqlBuffer.delete(length - 1, length).append(")values(");
    int size = param.size();
    for (int x = 0; x < size; x++) {
      if (x != 0) {
        sqlBuffer.append(",");
      }
      sqlBuffer.append(":" + fields.get(x).getName());
    }
    sqlBuffer.append(")");
    System.err.println("insert:\t" + sqlBuffer.toString());
    this.sqlBuffer = sqlBuffer.toString();
  }

  private List<Object> param = new Vector<Object>();

  private String sqlBuffer;

  public List<Object> getParam() {
    return param;
  }

  public String getSqlBuffer() {
    return sqlBuffer;
  }

  public String getIdName() {
    return idName;
  }

  public Object getIdValue() {
    return idValue;
  }

  List<Field> fields = new Vector<Field>();

  protected void getFields(Class<?> clazz) {
    if (Object.class.equals(clazz)) {
      return;
    }
    Field[] fieldArray = clazz.getDeclaredFields();
    for (Field file : fieldArray) {
      if (!file.getName().equals("serialVersionUID")) {
        fields.add(file);
      }
    }
    getFields(clazz.getSuperclass());
  }

  //创建注解,标识该model的table名
  @java.lang.annotation.Target(value = {java.lang.annotation.ElementType.TYPE})
  @java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface Table {

    String name() default "";
  }

}
