package top.greathead.jk.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	Invoice
 * @Author:			
 * @Company:		
 * @CreateDate:		2018-1-5 14:51:53
 */

public class Invoice extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String scNo;			//packing.getExportNos S/C就是报运的合同号
	private String blNo;			
	private String tradeTerms;			
	private Double state;			//0草稿 1已上报
	private String createBy;			
	private String createDept;			
	private Date createTime;
	private PackingList packingList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScNo() {
		return scNo;
	}

	public void setScNo(String scNo) {
		this.scNo = scNo;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getTradeTerms() {
		return tradeTerms;
	}

	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}

	public Double getState() {
		return state;
	}

	public void setState(Double state) {
		this.state = state;
	}

	@Override
	public String getCreateBy() {
		return createBy;
	}

	@Override
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Override
	public String getCreateDept() {
		return createDept;
	}

	@Override
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PackingList getPackingList() {
		return packingList;
	}

	public void setPackingList(PackingList packingList) {
		this.packingList = packingList;
	}
}
