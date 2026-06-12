package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VideoTagRelationMapper<T, P> extends BaseMapper {
    T selectById(@Param("id") Integer id);
    Integer updateById(@Param("bean") T t, @Param("id") Integer id);
    Integer deleteById(@Param("id") Integer id);

    /** 根据视频ID查询关联标签ID列表 */
    List<Integer> selectTagIdsByVideoId(@Param("videoId") Integer videoId);

    /** 根据标签ID查询关联视频ID列表 */
    List<Integer> selectVideoIdsByTagId(@Param("tagId") Integer tagId);

    /** 根据视频ID和标签ID删除关联 */
    Integer deleteByVideoIdAndTagId(@Param("videoId") Integer videoId, @Param("tagId") Integer tagId);

    /** 根据视频ID删除所有关联 */
    Integer deleteByVideoId(@Param("videoId") Integer videoId);
}
