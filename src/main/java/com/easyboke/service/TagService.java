package com.easyboke.service;

import com.easyboke.entity.query.TagQuery;
import com.easyboke.entity.po.Tag;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 文章标签表对应的Service
 * @date: 2026/05/10
 */

public interface TagService{

	/**
	 * 根据条件查询列表
	 */
	List<Tag>findListByParam(TagQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(TagQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Tag> findListByPage(TagQuery query );

	/**
	 * 新增
	 */
	Integer add(Tag bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<Tag> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(Tag bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<Tag> listBean);
	/**
	 * 根据Id查询
	 */
	 Tag getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(Tag bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);

	/**
	 * 根据TagName查询
	 */
	 Tag getByTagName(String tagName);

	/**
	 * 根据TagName查询
	 */
	 Integer updateByTagName(Tag bean , String tagName);

	/**
	 * 根据TagName删除
	 */
	 Integer deleteByTagName(String tagName);


}