package mx.bhit.omicron.app.restful.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataItems implements Serializable {
	public DataItems() {
		// TODO Auto-generated constructor stub
		this.dataItems = new ArrayList<TwitterMessage>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private ArrayList<HashMap<String, Object>> DataItems;
	private List<TwitterMessage> dataItems;

	public List<TwitterMessage> getDataItems() {
		return dataItems;
	}

	public void setDataItems(List<TwitterMessage> dataItems) {
		this.dataItems = dataItems;
	}

}
