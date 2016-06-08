package mx.bhit.omicron.app.restful.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.bhit.omicron.app.restful.model.DataItems;
import mx.bhit.omicron.app.restful.model.Request;
import mx.bhit.omicron.app.restful.model.Response;
import mx.bhit.omicron.app.restful.model.SocialNetworksMonitoredData;
import mx.bhit.omicron.app.restful.model.TwitterMessage;

@RestController
@RequestMapping("/twitter")
public class TwitterMonitorTopicRestController {
	private static final Logger logger = LoggerFactory.getLogger(TwitterMonitorTopicRestController.class);

	@RequestMapping("/topic")
	public Response startMonitor() {
		logger.debug("Monitoreo Controller...");
		System.out.println("Monitoreo Controller");
		return new Response();
	}

	@RequestMapping("/config")
	public Response addConfiguration(@RequestBody Request request) {
		System.out.println("Config Controller");
		SocialNetworksMonitoredData socialNetworksMonitoredData = new SocialNetworksMonitoredData();
		DataItems dataItems = new DataItems();
		dataItems.getDataItems()
				.add(new TwitterMessage(new Long(734894106967703552L), new Date(),
						"Game on. Big ten network in 10 mins. Hoop for wàter. Flint we got ya back", "@SnoopDogg",
						"https://twitter.com/SnoopDogg/status/734894106967703552"));

		socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("searchTimeStamp", "");
		socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("numDataItems", 2);
		socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("DataItems", dataItems.getDataItems());

		return socialNetworksMonitoredData;
	}

	// public TwitterResponse getMessages() {
	// TwitterResponse twitterResponse = new TwitterResponse();
	// // Obtienes una instancia de Twitter.
	// Twitter twitter = new TwitterFactory().getInstance();
	//
	// /*
	// * Creas el acceso a tu cuenta, para generar las claves de autorización
	// * desde tu cuenta visita el siguiente sitio: https://apps.twitter.com/
	// * Creas una aplicación. Despues de crearla te vas a la sección Keys and
	// * Access Tokens , ahí podrás ver las llaves que debes poner en la
	// * siguiente sección.
	// */
	// twitter.setOAuthConsumer("q8TheJW3NLMdSFKkpMAeeeoqA",
	// "0Uk167lb5OSsgimUpWlnYozC5GzL3TLBC0Um3oieFyWvQF0bOW");
	// twitter.setOAuthAccessToken(new
	// AccessToken("3877098085-skJ2Sat6hL3iW6IXWBxAV3MZJVOMi3venxRITjm",
	// "RWFECCxUo1Fe4WJyhVrXGO2o0DBl9lPDEqN1vV4W8OaUl"));
	//
	// try {
	//
	// // Lista de mensajes con paginación
	// ResponseList<Status> userTL = twitter.getUserTimeline(new Paging(1, 5));
	// // Lista de mensajes sin paginación
	// ResponseList<Status> homeTL = twitter.getHomeTimeline();
	//
	// for (Status status : userTL) {
	// System.out.println("--------------------------------------- con
	// paginacion");
	// System.out.println("ID: " + status.getId());
	// System.out.println("FECHA: " + status.getCreatedAt());
	// System.out.println("USUARIO: " + status.getUser().toString());
	// System.out.println("CONTENIDO: " + status.getText());
	//
	//
	//// for (Status status : homeTL) {
	//// System.out.println("--------------------------------------------");
	//// System.out.println("ID: " + status.getId());
	//// System.out.println("FECHA: " + status.getCreatedAt());
	//// System.out.println("USUARIO: " + status.getUser());
	//// System.out.println("CONTENIDO: " + status.getText());
	//// }
	//
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// }
	// return twitterResponse;
	//
	// }

}
