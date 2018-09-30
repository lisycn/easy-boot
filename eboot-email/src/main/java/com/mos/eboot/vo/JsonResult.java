package com.mos.eboot.vo;

import lombok.Data;

@Data
public class JsonResult<T> {

    private int code;
    private String msg;
    private T data;


    public JsonResult() {
        this.code = 0;
        this.msg = "success";
    }


    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public JsonResult(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "success";
    }


    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = 0;
        this.msg = msg;
    }


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}
    
}