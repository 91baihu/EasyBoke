package com.easyboke.service;

import com.easyboke.entity.query.CategoryQuery;
import com.easyboke.entity.po.Category;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 文章分类表对应的Service
 * @date: 2026/05/10
 */

public interface CategoryService{

	/**
	 * 根据条件查询列表
	 */
	List<Category>findListByParam(CategoryQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(CategoryQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Category> findListByPage(CategoryQuery query );

	/**
	 * 新增
	 */
	Integer add(Category bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<Category> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(Category bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<Category> listBean);
	/**
	 * 根据Id查询
	 */
	 Category getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(Category bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);


}