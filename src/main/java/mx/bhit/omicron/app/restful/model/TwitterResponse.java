package mx.bhit.omicron.app.restful.model;

import java.util.ArrayList;
import java.util.List;

public class TwitterResponse extends Response {

	public TwitterResponse() {
		// TODO Auto-generated constructor stub
		messageList = new ArrayList<TwitterMessage>();
	}

	public List<TwitterMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<TwitterMessage> messageList) {
		this.messageList = messageList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -331218346543560164L;
	private List<TwitterMessage> messageList;

}
