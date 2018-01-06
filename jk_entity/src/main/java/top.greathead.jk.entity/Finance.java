package top.greathead.jk.entity;

import java.util.Date;

/**
 * @Description:	Finance
 * @Author:			
 * @Company:		
 * @CreateDate:		2018-1-5 14:50:46
 */

public class Finance extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private Date inputDate;			
	private String inputBy;			

	private Long state;			//0草稿 1已上报
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

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}



	public Long getState() {
		return state;
	}

	public void setState(Long state) {

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
