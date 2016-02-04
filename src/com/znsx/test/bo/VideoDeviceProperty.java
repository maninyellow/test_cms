package com.znsx.test.bo;

import java.io.Serializable;

/**
 * 视频服务器属性实体
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2013 下午1:17:44
 */
public class VideoDeviceProperty implements Serializable {
	private static final long serialVersionUID = 3050400350113891889L;
	private String id;
	private String userName;
	private String password;
	private Integer heartCycle;
	private Short storeType;
	private String localStorePlan;
	private String centerStorePlan;
	private String protocol;
	private String streamType;
	private String expand;
	private Long imageId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getHeartCycle() {
		return heartCycle;
	}

	public void setHeartCycle(Integer heartCycle) {
		this.heartCycle = heartCycle;
	}

	public Short getStoreType() {
		return storeType;
	}

	public void setStoreType(Short storeType) {
		this.storeType = storeType;
	}

	public String getLocalStorePlan() {
		return localStorePlan;
	}

	public void setLocalStorePlan(String localStorePlan) {
		this.localStorePlan = localStorePlan;
	}

	public String getCenterStorePlan() {
		return centerStorePlan;
	}

	public void setCenterStorePlan(String centerStorePlan) {
		this.centerStorePlan = centerStorePlan;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getStreamType() {
		return streamType;
	}

	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

}
