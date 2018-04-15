package com.cj.witcommon.utils.common;


public class Response {

	protected int status;// 状态码
	protected Object body;// JSON格式的值，里面放返回给前端的具体数据
	protected String message;// 状态描述
	protected long currentPage;//当前页
	protected long totalPage;//总页数
	protected long totalRow;//总行数


	public Response() {
	}

	public Response(int status) {
		this.status = status;
	}

	public Response(int status, String message) {
		this(status);
		this.message = message;
	}

	public Response(int status, Object body) {
		this(status);
		this.body = body;
	}

	public Response(int status, String message, Object body) {
		this(status, message);
		this.body = body;
	}
	public Response(int status,  long totalRow, Object body) {
		this.status = status;
		this.totalRow = totalRow;
		this.body = body;
	}


	public Response(short status, long totalRow, long totalPage, Object body,long currentPage) {
		this.status = status;
		this.totalRow = totalRow;
		this.totalPage = totalPage;
		this.body = body;
		this.currentPage = currentPage;
	}

	public Response(short status, long totalRow, long totalPage, Object body) {

		this.status = status;
		this.totalRow = totalRow;
		this.totalPage = totalPage;
		this.body = body;
		//this.currentPage = currentPage;
	}

	public long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
