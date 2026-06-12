package com.easyboke.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyboke.entity.enums.DateTimePatternEnum;
import com.easyboke.utils.DateUtils;

/**
 * @author 高98
 * @Description: 访问日志表
 * @date: 2026/05/10
 */
public class VisitLog implements Serializable{

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 访问类型：article/video
	 */
	private String targetType;

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

	/**
	 * 浏览器信息
	 */
	private String userAgent;

	/**
	 * 访问时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date visitTime;

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

	@Override
	public String toString() {
		return "ID:" + (id == null ? "空" : id) + ",访问类型：article/video:" + (targetType == null ? "空" : targetType) + ",目标ID:" + (targetId == null ? "空" : targetId) + ",访问者ID，0表示游客:" + (userId == null ? "空" : userId) + ",访问者性别：0-未知，1-男，2-女:" + (gender == null ? "空" : gender) + ",访问者年龄，0表示未知:" + (age == null ? "空" : age) + ",访问IP:" + (ipAddress == null ? "空" : ipAddress) + ",浏览器信息:" + (userAgent == null ? "空" : userAgent) + ",访问时间:" + (visitTime == null ? "空" : DateUtils.format(visitTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}

}