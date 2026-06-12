package com.easyboke.entity.query;

import java.util.Date;

/**
 * @author 高98
 * @Description: 评论表查询对象
 * @date: 2026/05/10
 */
public class CommentQuery extends BaseQuery{

	/**
	 * 评论ID
	 */
	private Integer id;

	/**
	 * 评论内容
	 */
	private String content;

	private String contentFuzzy;

	/**
	 * 评论者ID，0表示游客
	 */
	private Integer userId;

	/**
	 * 评论者用户名（游客填写）
	 */
	private String username;

	private String usernameFuzzy;

	/**
	 * 关联文章ID
	 */
	private Integer articleId;

	/**
	 * 关联视频ID
	 */
	private Integer videoId;

	/**
	 * 父评论ID，0表示一级评论
	 */
	private Integer parentId;

	/**
	 * 回复的评论ID
	 */
	private Integer replyCommentId;

	/**
	 * 状态：0-待审核，1-已通过，2-已删除
	 */
	private Integer status;

	/**
	 * 点赞数
	 */
	private Integer likeCount;

	/**
	 * 评论IP
	 */
	private String ipAddress;

	private String ipAddressFuzzy;

	/**
	 * 评论时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String updateTimeStart;

	private String updateTimeEnd;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public Integer getVideoId() {
		return this.videoId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setReplyCommentId(Integer replyCommentId) {
		this.replyCommentId = replyCommentId;
	}

	public Integer getReplyCommentId() {
		return this.replyCommentId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getLikeCount() {
		return this.likeCount;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setContentFuzzy(String contentFuzzy) {
		this.contentFuzzy = contentFuzzy;
	}

	public String getContentFuzzy() {
		return this.contentFuzzy;
	}

	public void setUsernameFuzzy(String usernameFuzzy) {
		this.usernameFuzzy = usernameFuzzy;
	}

	public String getUsernameFuzzy() {
		return this.usernameFuzzy;
	}

	public void setIpAddressFuzzy(String ipAddressFuzzy) {
		this.ipAddressFuzzy = ipAddressFuzzy;
	}

	public String getIpAddressFuzzy() {
		return this.ipAddressFuzzy;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart() {
		return this.createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeStart() {
		return this.updateTimeStart;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

}