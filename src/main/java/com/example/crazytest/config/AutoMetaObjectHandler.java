package com.example.crazytest.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.crazytest.utils.BaseContext;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author
 * @name Menghui
 * @date 2025/3/18 21:25
 * @DESRIPTION 自动不全字段信息
 */

@Component
public class AutoMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
    this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    this.strictInsertFill(metaObject, "createById",
        () -> Optional.ofNullable(BaseContext.getUserId()).orElse(0L), Long.class);
    this.strictInsertFill(metaObject, "createByName",
        () -> Optional.ofNullable(BaseContext.getUserName()).orElse(""), String.class);
    this.strictInsertFill(metaObject, "updateById",
        () -> Optional.ofNullable(BaseContext.getUserId()).orElse(0L), Long.class);
    this.strictInsertFill(metaObject, "updateByName",
        () -> Optional.ofNullable(BaseContext.getUserName()).orElse(""), String.class);
  }


  @Override
  public void updateFill(MetaObject metaObject) {
    this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    this.strictUpdateFill(metaObject, "updateById",
        () -> Optional.ofNullable(BaseContext.getUserId()).orElse(0L), Long.class);
    this.strictUpdateFill(metaObject, "updateByName",
        () -> Optional.ofNullable(BaseContext.getUserName()).orElse(""), String.class);

  }
}
