package com.nzb.eshop.inventory.vo;

/**
 * @Description: 请求的响应
 * @author M
 *
 */
public class Response {

	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";

	private String status;
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Response(String message) {
		super();
		this.message = message;
	}

	public Response() {
		super();
	}

}
