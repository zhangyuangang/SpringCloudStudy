package com.jiagouedu.services.common;import com.jiagouedu.core.dao.page.PagerModel;import java.io.Serializable;/** * 会员等级 * @author mainidear * */public class AccountRank extends PagerModel implements Serializable {	private static final long serialVersionUID = 1L;	private String id;	private String code;	private String name;	private int minScore;	private int maxScore;	private String remark;	public void clear() {		super.clear();		id = null;		code = null;		name = null;		minScore = 0;		maxScore = 0;		remark = null;	}	public String getId() {		return id;	}	public void setId(String id) {		this.id = id;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public int getMinScore() {		return minScore;	}	public void setMinScore(int minScore) {		this.minScore = minScore;	}	public int getMaxScore() {		return maxScore;	}	public void setMaxScore(int maxScore) {		this.maxScore = maxScore;	}	public String getRemark() {		return remark;	}	public void setRemark(String remark) {		this.remark = remark;	}}