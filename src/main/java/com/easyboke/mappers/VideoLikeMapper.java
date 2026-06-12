package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoLikeMapper<T, P> extends BaseMapper {
    T selectById(@Param("id") Integer id);
    Integer updateById(@Param("bean") T t, @Param("id") Integer id);
    Integer deleteById(@Param("id") Integer id);

    /** 根据视频ID和用户ID查询点赞记录 */
    T selectByVideoIdAndUserId(@Param("videoId") Integer videoId, @Param("userId") Integer userId);

    /** 根据视频ID和用户ID删除点赞记录 */
    Integer deleteByVideoIdAndUserId(@Param("videoId") Integer videoId, @Param("userId") Integer userId);

    /** 统计视频点赞数 */
    Integer countByVideoId(@Param("videoId") Integer videoId);
}
