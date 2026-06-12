package com.easyboke.entity.query;

import java.util.Date;

/**
 * @author 高98
 * @Description: 访客统计表查询对象
 * @date: 2026/05/10
 */
public class VisitorStatisticsQuery extends BaseQuery{

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 访问日期
	 */
	private Date visitDate;

	private String visitDateStart;

	private String visitDateEnd;

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
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

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

	public void setVisitDateStart(String visitDateStart) {
		this.visitDateStart = visitDateStart;
	}

	public String getVisitDateStart() {
		return this.visitDateStart;
	}

	public void setVisitDateEnd(String visitDateEnd) {
		this.visitDateEnd = visitDateEnd;
	}

	public String getVisitDateEnd() {
		return this.visitDateEnd;
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

}