package com.shxt.framework.dict.model;
/**
 * 针对于省市县的DTO数据
 * @author 刘文铭
 *
 */
public class AddressDict {
	
	private Integer id;
	private String name;
	private String code;
	private Integer fk_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getFk_id() {
		return fk_id;
	}
	public void setFk_id(Integer fkId) {
		fk_id = fkId;
	}
	
	
	
	
}
