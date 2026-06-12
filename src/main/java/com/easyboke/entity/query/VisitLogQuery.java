package com.easyboke.entity.query;

import java.util.Date;

/**
 * @author 高98
 * @Description: 访问日志表查询对象
 * @date: 2026/05/10
 */
public class VisitLogQuery extends BaseQuery{

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 访问类型：article/video
	 */
	private String targetType;

	private String targetTypeFuzzy;

	/**
	 * 目标ID
	 */
	private Integer targetId;

	/**
	 * 访问者ID，0表示游客
	 */
	private Integer userId;

	/**
	 * 访问者性别：0-未知，1-男，2-女
	 */
	private Integer gender;

	/**
	 * 访问者年龄，0表示未知
	 */
	private Integer age;

	/**
	 * 访问IP
	 */
	private String ipAddress;

	private String ipAddressFuzzy;

	/**
	 * 浏览器信息
	 */
	private String userAgent;

	private String userAgentFuzzy;

	/**
	 * 访问时间
	 */
	private Date visitTime;

	private String visitTimeStart;

	private String visitTimeEnd;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetType() {
		return this.targetType;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getTargetId() {
		return this.targetId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setTargetTypeFuzzy(String targetTypeFuzzy) {
		this.targetTypeFuzzy = targetTypeFuzzy;
	}

	public String getTargetTypeFuzzy() {
		return this.targetTypeFuzzy;
	}

	public void setIpAddressFuzzy(String ipAddressFuzzy) {
		this.ipAddressFuzzy = ipAddressFuzzy;
	}

	public String getIpAddressFuzzy() {
		return this.ipAddressFuzzy;
	}

	public void setUserAgentFuzzy(String userAgentFuzzy) {
		this.userAgentFuzzy = userAgentFuzzy;
	}

	public String getUserAgentFuzzy() {
		return this.userAgentFuzzy;
	}

	public void setVisitTimeStart(String visitTimeStart) {
		this.visitTimeStart = visitTimeStart;
	}

	public String getVisitTimeStart() {
		return this.visitTimeStart;
	}

	public void setVisitTimeEnd(String visitTimeEnd) {
		this.visitTimeEnd = visitTimeEnd;
	}

	public String getVisitTimeEnd() {
		return this.visitTimeEnd;
	}

}