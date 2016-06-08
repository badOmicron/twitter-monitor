package mx.bhit.omicron.app.restful.model;

import java.util.HashMap;
import java.util.Map;

public class SocialNetworksMonitoredData extends Response {
	public SocialNetworksMonitoredData() {
		// TODO Auto-generated constructor stub
		SocialNetworksMonitoredData = new HashMap<String, Object>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> SocialNetworksMonitoredData;

	public Map<String, Object> getSocialNetworksMonitoredData() {
		return SocialNetworksMonitoredData;
	}

	public void setSocialNetworksMonitoredData(Map<String, Object> socialNetworksMonitoredData) {
		SocialNetworksMonitoredData = socialNetworksMonitoredData;
	}

}
