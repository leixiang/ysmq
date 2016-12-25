package com.nsyun.server.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class WXQueryResult<T> implements Serializable {
	
	private static final long serialVersionUID = 4127098079837220848L;
	
	private List<T> data;
	
	@JsonProperty("next_page")
	private int next_page;
	
	public int getNext_page() {
		return next_page;
	}

	public void setNext_page(int next_page) {
		this.next_page = next_page;
	}

	@JsonUnwrapped
	@JsonProperty("data")
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}

}
