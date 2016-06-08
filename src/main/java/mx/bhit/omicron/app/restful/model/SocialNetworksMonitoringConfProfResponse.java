package mx.bhit.omicron.app.restful.model;

import java.util.HashMap;
import java.util.Map;

public class SocialNetworksMonitoringConfProfResponse extends Response {

	public SocialNetworksMonitoringConfProfResponse() {
		// TODO Auto-generated constructor stub
		this.socialNetworksMonitoringConfProfResult = new HashMap<String, Object>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6352331695082218686L;
	private Map<String, Object> socialNetworksMonitoringConfProfResult;

	public Map<String, Object> getSocialNetworksMonitoringConfProfResult() {
		return socialNetworksMonitoringConfProfResult;
	}

	public void setSocialNetworksMonitoringConfProfResult(Map<String, Object> socialNetworksMonitoringConfProfResult) {
		this.socialNetworksMonitoringConfProfResult = socialNetworksMonitoringConfProfResult;
	}
}
