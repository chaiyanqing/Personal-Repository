package com.zs.entity;

public class ResponseResult {

	private Boolean success;
	private String msg;
	private Object data;
	
	@Override
	public String toString() {
		return "ResponseResult [success=" + success + ", msg=" + msg + ", data=" + data + "]";
	}

	public ResponseResult(Boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public ResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
