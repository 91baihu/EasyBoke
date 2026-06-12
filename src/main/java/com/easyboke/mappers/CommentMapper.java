package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author 高98
 * @Description: 评论表的Mapper类
 * @date: 2026/05/10
 */

@Mapper
public interface CommentMapper<T,P> extends BaseMapper {
	/**
	 * 根据Id查询
	 */
	 T selectById(@Param("id") Integer id);

	/**
	 * 根据Id更新
	 */
	 Integer updateById(@Param("bean") T t, @Param("id") Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(@Param("id") Integer id);

	/**
	 * 根据文章ID查询评论列表（含用户头像）
	 */
	java.util.List<T> selectByArticleIdWithAvatar(@Param("articleId") Integer articleId, @Param("status") Integer status);

	/**
	 * 根据视频ID查询评论列表（含用户头像）
	 */
	java.util.List<T> selectByVideoIdWithAvatar(@Param("videoId") Integer videoId, @Param("status") Integer status);

}