package cn.hmjiaxin.model;

public class ReturnResult {
	private int status;
	private String msg;
	private Object otherObject;
	public Object getOtherObject() {
		return otherObject;
	}
	public void setOtherObject(Object otherObject) {
		this.otherObject = otherObject;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ReturnResult(int status, String msg, Object otherObject) {
		super();
		this.status = status;
		this.msg = msg;
		this.otherObject = otherObject;
	}
	public ReturnResult(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
}
