package com.sunyu.rabbit.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;



/**
 * Table:
 * @author yu
 * @date 2017-04-28 22:50:21
 *
 */
public class SystemLog implements Serializable{

    private static final long serialVersionUID = -7807949631469215320L;

   	private Integer id;
	private Integer orgId;
	private String ip;
	private String userId;
	private String userName;
	private Integer type;
	private String method;
	private String methodDesc;
	private String params;
	private String content;
	private String data;
	private Long createTime;
	private String exceptionCode;
	private String exceptionDetail;

	//getters and setters
   	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getOrgId(){
		return orgId;
	}
	public void setOrgId(Integer orgId){
		this.orgId = orgId;
	}
	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public String getMethod(){
		return method;
	}
	public void setMethod(String method){
		this.method = method;
	}
	public String getMethodDesc(){
		return methodDesc;
	}
	public void setMethodDesc(String methodDesc){
		this.methodDesc = methodDesc;
	}
	public String getParams(){
		return params;
	}
	public void setParams(String params){
		this.params = params;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getData(){
		return data;
	}
	public void setData(String data){
		this.data = data;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}
	public String getExceptionCode(){
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode){
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionDetail(){
		return exceptionDetail;
	}
	public void setExceptionDetail(String exceptionDetail){
		this.exceptionDetail = exceptionDetail;
	}

}