package com.jhbim.bimvr.pub;

public class CheckMsg {
	private String msgInfo;
	private boolean success = true;
	private Object data;

	public CheckMsg() {
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public CheckMsg setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public CheckMsg setSuccess(boolean stu) {
		this.success = stu;
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData( Object data ) {
		this.data = data;
	}

	public CheckMsg success( CheckCallback callback) throws Exception {
		if (this.isSuccess()) {
			return callback.doCallback();
		}
		return this;
	}

	public CheckMsg fail(CheckCallback callback) throws Exception {
		if (!this.isSuccess()) {
			callback.doCallback();
		}
		return this;
	}

	public static CheckMsg fail(String... msg) {
		CheckMsg check = new CheckMsg();
		check.setSuccess(false);
		if (msg != null && msg.length > 0) {
			check.setMsgInfo(msg[0]);
		}
		return check;
	}

	public static CheckMsg success(String... msg) {
		CheckMsg check = new CheckMsg();
		if (msg != null && msg.length > 0) {
			check.setMsgInfo(msg[0]);
		}
		return check;
	}

	public static interface CheckCallback {
		public CheckMsg doCallback() throws Exception;
	}

}
