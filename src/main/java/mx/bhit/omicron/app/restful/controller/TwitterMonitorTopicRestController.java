package mx.bhit.omicron.app.restful.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.bhit.omicron.app.restful.model.Response;
import mx.bhit.omicron.app.restful.model.TwitterMessage;
import mx.bhit.omicron.app.restful.model.TwitterResponse;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@RestController
@RequestMapping("/twitter")
public class TwitterMonitorTopicRestController {
	private static final Logger logger = LoggerFactory.getLogger(TwitterMonitorTopicRestController.class);

	@RequestMapping("/topic")
	public Response startMonitor() {
		logger.debug("Monitoreo Controller...");
		TwitterResponse twitterResponse = getMessages();
		twitterResponse.setCodigo(200);
		twitterResponse.setMensaje("SUCCESS");

		return twitterResponse;
	}

	public TwitterResponse getMessages() {
		TwitterResponse twitterResponse = new TwitterResponse();
		// Obtienes una instancia de Twitter.
		Twitter twitter = new TwitterFactory().getInstance();

		/*
		 * Creas el acceso a tu cuenta, para generar las claves de autorización
		 * desde tu cuenta visita el siguiente sitio: https://apps.twitter.com/
		 * Creas una aplicación. Despues de crearla te vas a la sección Keys and
		 * Access Tokens , ahí podrás ver las llaves que debes poner en la
		 * siguiente sección.
		 */
		twitter.setOAuthConsumer("q8TheJW3NLMdSFKkpMAeeeoqA", "0Uk167lb5OSsgimUpWlnYozC5GzL3TLBC0Um3oieFyWvQF0bOW");
		twitter.setOAuthAccessToken(new AccessToken("3877098085-skJ2Sat6hL3iW6IXWBxAV3MZJVOMi3venxRITjm",
				"RWFECCxUo1Fe4WJyhVrXGO2o0DBl9lPDEqN1vV4W8OaUl"));

		try {

			// Lista de mensajes con paginación
			ResponseList<Status> userTL = twitter.getUserTimeline(new Paging(1, 5));
			// Lista de mensajes sin paginación
			ResponseList<Status> homeTL = twitter.getHomeTimeline();

			for (Status status : userTL) {
				System.out.println("--------------------------------------- con paginacion");
				System.out.println("ID: " + status.getId());
				System.out.println("FECHA: " + status.getCreatedAt());
				System.out.println("USUARIO: " + status.getUser().toString());
				System.out.println("CONTENIDO: " + status.getText());
				twitterResponse.getMessageList().add(new TwitterMessage(status.getId(), status.getCreatedAt(),
						status.getUser().toString(), status.getText()));
			}

			for (Status status : homeTL) {
				System.out.println("--------------------------------------------");
				System.out.println("ID: " + status.getId());
				System.out.println("FECHA: " + status.getCreatedAt());
				System.out.println("USUARIO: " + status.getUser());
				System.out.println("CONTENIDO: " + status.getText());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return twitterResponse;

	}

}
