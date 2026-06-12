package com.easyboke.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyboke.service.CategoryService;
import com.easyboke.entity.po.Category;
import com.easyboke.entity.query.CategoryQuery;
import com.easyboke.entity.vo.ResponseVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 文章分类表的Controller类
 * @date: 2026/05/10
 */

@RestController
@RequestMapping("/category")
public class CategoryController extends ABaseController{

	@Resource
	private CategoryService categoryService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(CategoryQuery query) {
		return getSuccessResponseVo(categoryService.findListByPage(query));
	}
	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(Category bean){
		return getSuccessResponseVo(categoryService.add(bean));
	 }
	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Category> listBean){
		return getSuccessResponseVo(categoryService.addBatch(listBean));
	 }
	/**
	 * 新增或者修改
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(Category bean){
		return getSuccessResponseVo(categoryService.addOrUpdate(bean));
	 }
	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdate(@RequestBody List<Category> listBean){
		return getSuccessResponseVo(categoryService.addOrUpdateBatch(listBean));
	 }
	/**
	 * 根据Id查询
	 */
	@RequestMapping("getCategoryById")
	 public ResponseVO getCategoryById(Integer id){
		return getSuccessResponseVo(this.categoryService.getById(id));
	 }
	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateCategoryById")
	 public ResponseVO updateCategoryById(Category bean,Integer id){
		return getSuccessResponseVo(this.categoryService.updateById(bean,id));
	 }
	/**
	 * 更新分类（简化版）
	 */
	@RequestMapping("update")
	public ResponseVO update(Category bean){
		return getSuccessResponseVo(this.categoryService.updateById(bean, bean.getId()));
	}
	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteCategoryById")
	 public ResponseVO deleteCategoryById(Integer id){
		return getSuccessResponseVo(this.categoryService.deleteById(id));
	 }

}