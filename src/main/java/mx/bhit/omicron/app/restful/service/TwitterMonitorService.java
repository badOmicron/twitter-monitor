package mx.bhit.omicron.app.restful.service;

import mx.bhit.omicron.app.restful.model.DataItems;
import mx.bhit.omicron.app.restful.model.TwitterMessage;
import mx.bhit.omicron.app.restful.utils.TwitterUtil;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterMonitorService {
	Twitter twitter;
	DataItems dataItems;

	public TwitterMonitorService() {
		// Obtienes una instancia de Twitter.
		this.twitter = new TwitterFactory().getInstance();
	}

	public DataItems getData() {
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

			dataItems = new DataItems();
			for (Status status : homeTL) {
				System.out.println("--------------------------------------- ");
				System.out.println("ID: " + status.getId());
				System.out.println("FECHA: " + status.getCreatedAt());
				System.out.println("USUARIO: " + status.getUser().toString());
				System.out.println("CONTENIDO: " + status.getText());
				String url = TwitterUtil.getURLTwitterTopic(status.getUser(), status.getId());
				System.out.println("URL: " + url);

				dataItems.getDataItems().add(new TwitterMessage(new Long(status.getId()), status.getCreatedAt(),
						status.getText().toString(), status.getUser().toString(), url));
				System.out.println(dataItems.getDataItems().size());

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new DataItems();
		}

		return dataItems;
	}
}
