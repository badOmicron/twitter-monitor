package mx.bhit.omicron.app.restful.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.bhit.omicron.app.restful.model.DataItems;
import mx.bhit.omicron.app.restful.model.Response;
import mx.bhit.omicron.app.restful.model.SocialNetworksMonitoredData;
import mx.bhit.omicron.app.restful.service.TwitterMonitorService;

@RestController
@RequestMapping("/twitter")
public class TwitterMonitorTopicRestController {
	private static final Logger logger = LoggerFactory.getLogger(TwitterMonitorTopicRestController.class);

	@RequestMapping("/topic")
	public Response startMonitor() {
		logger.debug("Monitoreo Controller...");
		System.out.println("Monitoreo Controller");

		TwitterMonitorService TwitterMonitorService = new TwitterMonitorService();

		SocialNetworksMonitoredData socialNetworksMonitoredData = new SocialNetworksMonitoredData();
		DataItems dataItems = TwitterMonitorService.getData();
		int numDataItems = dataItems.getDataItems().size();

		socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("searchTimeStamp", new Date());
		socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("numDataItems", numDataItems);
		socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("dataItems", dataItems.getDataItems());

		return socialNetworksMonitoredData;
	}

}
