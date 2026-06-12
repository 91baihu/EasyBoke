package com.easyboke.service.impl;

import com.easyboke.entity.query.CategoryQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.Category;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.CategoryMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.CategoryService;
/**
 * @author 高98
 * @Description: 文章分类表对应的ServiceImpl
 * @date: 2026/05/10
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Resource
	private CategoryMapper<Category,CategoryQuery> categoryMapper;
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Category>findListByParam(CategoryQuery query){
		return this.categoryMapper.selectList(query);
	 }
	/**
	 * 根据条件查询数量
	 */
	@Override
	public Integer findCountByParam(CategoryQuery query){
		return this.categoryMapper.selectCount(query);
	 }
	/**
	 * 分页查询
	 */
	@Override
	public PaginationResultVO<Category> findListByPage(CategoryQuery query ){
		Integer count = this.findCountByParam(query); 
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Category> list = this.findListByParam(query);
		PaginationResultVO<Category> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	 }
	/**
	 * 新增
	 */
	@Override
	public Integer add(Category bean){
		return this.categoryMapper.insert(bean);
	 }
	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Category> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.categoryMapper.insertBatch(listBean);
	 }
	/**
	 * 新增或者修改
	 */
	@Override
	public Integer addOrUpdate(Category category){
		return this.categoryMapper.insertOrUpdate(category);
	 }
	/**
	 * 批量新增或修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Category> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.categoryMapper.insertOrUpdateBatch(listBean);
	 }
	/**
	 * 根据Id查询
	 */
	@Override
	 public Category getById(Integer id){
		return this.categoryMapper.selectById(id);
	 }
	/**
	 * 根据Id更新
	 */
	@Override
	 public Integer updateById(Category bean , Integer id){
		return this.categoryMapper.updateById(bean,id);
	 }
	/**
	 * 根据Id删除
	 */
	@Override
	 public Integer deleteById(Integer id){
		return this.categoryMapper.deleteById(id);
	 }

}