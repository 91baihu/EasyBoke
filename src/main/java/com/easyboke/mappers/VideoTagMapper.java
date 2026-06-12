package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoTagMapper<T, P> extends BaseMapper {
    T selectById(@Param("id") Integer id);
    Integer updateById(@Param("bean") T t, @Param("id") Integer id);
    Integer deleteById(@Param("id") Integer id);

    /** 根据标签名查找 */
    T selectByTagName(@Param("tagName") String tagName);

    /** 增加使用次数 */
    Integer incrementUseCount(@Param("id") Integer id);
}
