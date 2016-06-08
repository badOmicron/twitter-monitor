package mx.bhit.omicron.app.restful.model;

import java.util.HashMap;
import java.util.Map;

public class Request {

    public Request() {
        // TODO Auto-generated constructor stub
        setSocialNetworksMonitoringConfProf(new HashMap<String, Object>());
    }

    private Map<String, Object> SocialNetworksMonitoringConfProf;

    public Map<String, Object> getSocialNetworksMonitoringConfProf() {
        return SocialNetworksMonitoringConfProf;
    }

    public void setSocialNetworksMonitoringConfProf(Map<String, Object> SocialNetworksMonitoringConfProf) {
        this.SocialNetworksMonitoringConfProf = SocialNetworksMonitoringConfProf;
    }

}
