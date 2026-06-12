package com.easyboke.service;

import com.easyboke.entity.query.ArticleTagQuery;
import com.easyboke.entity.po.ArticleTag;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 文章标签关联表对应的Service
 * @date: 2026/05/10
 */

public interface ArticleTagService{

	/**
	 * 根据条件查询列表
	 */
	List<ArticleTag>findListByParam(ArticleTagQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(ArticleTagQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ArticleTag> findListByPage(ArticleTagQuery query );

	/**
	 * 新增
	 */
	Integer add(ArticleTag bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<ArticleTag> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(ArticleTag bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<ArticleTag> listBean);
	/**
	 * 根据Id查询
	 */
	 ArticleTag getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(ArticleTag bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);

	/**
	 * 根据ArticleIdAndTagId查询
	 */
	 ArticleTag getByArticleIdAndTagId(Integer articleId,Integer tagId);

	/**
	 * 根据ArticleIdAndTagId查询
	 */
	 Integer updateByArticleIdAndTagId(ArticleTag bean , Integer articleId,Integer tagId);

	/**
	 * 根据ArticleIdAndTagId删除
	 */
	 Integer deleteByArticleIdAndTagId(Integer articleId,Integer tagId);


}