package com.easyboke.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyboke.service.CommentService;
import com.easyboke.entity.po.Comment;
import com.easyboke.entity.query.CommentQuery;
import com.easyboke.entity.vo.ResponseVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 评论表的Controller类
 * @date: 2026/05/10
 */

@RestController
@RequestMapping("/comment")
public class CommentController extends ABaseController{

	@Resource
	private CommentService commentService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(CommentQuery query) {
		return getSuccessResponseVo(commentService.findListByPage(query));
	}
	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(Comment bean){
		return getSuccessResponseVo(commentService.add(bean));
	 }
	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Comment> listBean){
		return getSuccessResponseVo(commentService.addBatch(listBean));
	 }
	/**
	 * 新增或者修改
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(Comment bean){
		return getSuccessResponseVo(commentService.addOrUpdate(bean));
	 }
	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdate(@RequestBody List<Comment> listBean){
		return getSuccessResponseVo(commentService.addOrUpdateBatch(listBean));
	 }
	/**
	 * 根据Id查询
	 */
	@RequestMapping("getCommentById")
	 public ResponseVO getCommentById(Integer id){
		return getSuccessResponseVo(this.commentService.getById(id));
	 }
	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateCommentById")
	 public ResponseVO updateCommentById(Comment bean,Integer id){
		return getSuccessResponseVo(this.commentService.updateById(bean,id));
	 }
	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteCommentById")
	 public ResponseVO deleteCommentById(Integer id){
		return getSuccessResponseVo(this.commentService.deleteById(id));
	 }

	/**
	 * 发表评论（支持游客和注册用户）
	 */
	@RequestMapping("publish")
	public ResponseVO publishComment(@RequestBody Comment comment, javax.servlet.http.HttpSession session) {
		try {
			com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session.getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
			if (user != null) {
				comment.setUserId(user.getId());
				comment.setUsername(user.getUsername());
			} else {
				comment.setUserId(0);
				if (comment.getUsername() == null || comment.getUsername().trim().isEmpty()) {
					comment.setUsername("匿名用户");
				}
			}
			return getSuccessResponseVo(commentService.publishComment(comment));
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	/**
	 * 回复评论
	 */
	@RequestMapping("reply")
	public ResponseVO replyComment(@RequestBody Comment comment, javax.servlet.http.HttpSession session) {
		try {
			com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session.getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
			if (user != null) {
				comment.setUserId(user.getId());
				comment.setUsername(user.getUsername());
			} else {
				comment.setUserId(0);
				if (comment.getUsername() == null || comment.getUsername().trim().isEmpty()) {
					comment.setUsername("匿名用户");
				}
			}
			return getSuccessResponseVo(commentService.replyComment(comment));
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}


	/**
	 * 根据文章ID查询评论列表
	 */
	@RequestMapping("listByArticle")
	public ResponseVO getCommentsByArticle(Integer articleId) {
		return getSuccessResponseVo(commentService.getCommentsByArticleId(articleId));
	}

	/**
	 * 根据视频ID查询评论列表
	 */
	@RequestMapping("listByVideo")
	public ResponseVO getCommentsByVideo(Integer videoId) {
		return getSuccessResponseVo(commentService.getCommentsByVideoId(videoId));
	}

	/**
	 * 审核评论（管理员）
	 */
	@RequestMapping("audit")
	public ResponseVO auditComment(Integer id, Integer status) {
		return getSuccessResponseVo(commentService.auditComment(id, status));
	}
}