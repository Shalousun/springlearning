package com.sunyu.rocketmq.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;



/**
 * Table:
 * @author yu on 2018/11/05.
 */
public class Province implements Serializable {

    private static final long serialVersionUID = -8910480853281160259L;

   	private Integer id;

	private String provinceId;

	private String provinceName;


	/** getters and setters */
   	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


    @Override
    public String toString() {
        return "Province{" + 
                "id =" + id +
                ",provinceId ='" + provinceId + '\'' +
                ",provinceName ='" + provinceName + '\'' +
                '}';
    }
}