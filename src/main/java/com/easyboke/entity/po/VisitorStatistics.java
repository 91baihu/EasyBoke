package com.easyboke.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyboke.entity.enums.DateTimePatternEnum;
import com.easyboke.utils.DateUtils;

/**
 * @author 高98
 * @Description: 访客统计表
 * @date: 2026/05/10
 */
public class VisitorStatistics implements Serializable{

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 访问日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date visitDate;

	/**
	 * 页面浏览量
	 */
	private Integer pvCount;

	/**
	 * 独立访客数
	 */
	private Integer uvCount;

	/**
	 * 男性访问量
	 */
	private Integer maleCount;

	/**
	 * 女性访问量
	 */
	private Integer femaleCount;

	/**
	 * 18岁以下访问量
	 */
	private Integer ageUnder18;

	/**
	 * 18-25岁访问量
	 */
	private Integer age1825;

	/**
	 * 26-35岁访问量
	 */
	private Integer age2635;

	/**
	 * 36-45岁访问量
	 */
	private Integer age3645;

	/**
	 * 46岁以上访问量
	 */
	private Integer ageAbove45;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getVisitDate() {
		return this.visitDate;
	}

	public void setPvCount(Integer pvCount) {
		this.pvCount = pvCount;
	}

	public Integer getPvCount() {
		return this.pvCount;
	}

	public void setUvCount(Integer uvCount) {
		this.uvCount = uvCount;
	}

	public Integer getUvCount() {
		return this.uvCount;
	}

	public void setMaleCount(Integer maleCount) {
		this.maleCount = maleCount;
	}

	public Integer getMaleCount() {
		return this.maleCount;
	}

	public void setFemaleCount(Integer femaleCount) {
		this.femaleCount = femaleCount;
	}

	public Integer getFemaleCount() {
		return this.femaleCount;
	}

	public void setAgeUnder18(Integer ageUnder18) {
		this.ageUnder18 = ageUnder18;
	}

	public Integer getAgeUnder18() {
		return this.ageUnder18;
	}

	public void setAge1825(Integer age1825) {
		this.age1825 = age1825;
	}

	public Integer getAge1825() {
		return this.age1825;
	}

	public void setAge2635(Integer age2635) {
		this.age2635 = age2635;
	}

	public Integer getAge2635() {
		return this.age2635;
	}

	public void setAge3645(Integer age3645) {
		this.age3645 = age3645;
	}

	public Integer getAge3645() {
		return this.age3645;
	}

	public void setAgeAbove45(Integer ageAbove45) {
		this.ageAbove45 = ageAbove45;
	}

	public Integer getAgeAbove45() {
		return this.ageAbove45;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	@Override
	public String toString() {
		return "ID:" + (id == null ? "空" : id) + ",访问日期:" + (visitDate == null ? "空" : DateUtils.format(visitDate, DateTimePatternEnum.YYYY_MM_DD.getPattern())) + ",页面浏览量:" + (pvCount == null ? "空" : pvCount) + ",独立访客数:" + (uvCount == null ? "空" : uvCount) + ",男性访问量:" + (maleCount == null ? "空" : maleCount) + ",女性访问量:" + (femaleCount == null ? "空" : femaleCount) + ",18岁以下访问量:" + (ageUnder18 == null ? "空" : ageUnder18) + ",18-25岁访问量:" + (age1825 == null ? "空" : age1825) + ",26-35岁访问量:" + (age2635 == null ? "空" : age2635) + ",36-45岁访问量:" + (age3645 == null ? "空" : age3645) + ",46岁以上访问量:" + (ageAbove45 == null ? "空" : ageAbove45) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}

}