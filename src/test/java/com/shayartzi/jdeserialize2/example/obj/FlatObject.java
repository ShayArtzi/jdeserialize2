package com.shayartzi.jdeserialize2.example.obj;

import java.io.Serializable;

public class FlatObject implements Serializable {	
	
	private static final long serialVersionUID = 2672769041823858400L;

	private int intField;
	
	private Integer integerField;
	
	private String strField;

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public Integer getIntegerField() {
		return integerField;
	}

	public void setIntegerField(Integer integerField) {
		this.integerField = integerField;
	}

	public String getStrField() {
		return strField;
	}

	public void setStrField(String strField) {
		this.strField = strField;
	}
	
	@Override
	public String toString() {
		return "FlatObject [intField=" + intField + ", integerField=" + integerField + ", strField=" + strField + "]";
	}
	
}
