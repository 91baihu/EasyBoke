package com.easyboke.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author 高98
 * @Description: 文章标签关联表的Mapper类
 * @date: 2026/05/10
 */

@Mapper
public interface ArticleTagMapper<T,P> extends BaseMapper {
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
	 * 根据ArticleIdAndTagId查询
	 */
	 T selectByArticleIdAndTagId(@Param("articleId") Integer articleId,@Param("tagId") Integer tagId);

	/**
	 * 根据ArticleIdAndTagId更新
	 */
	 Integer updateByArticleIdAndTagId(@Param("bean") T t, @Param("articleId") Integer articleId,@Param("tagId") Integer tagId);

	/**
	 * 根据ArticleIdAndTagId删除
	 */
	 Integer deleteByArticleIdAndTagId(@Param("articleId") Integer articleId,@Param("tagId") Integer tagId);

	/**
	 * 根据文章ID删除所有关联
	 */
	Integer deleteByArticleId(@Param("articleId") Integer articleId);


}