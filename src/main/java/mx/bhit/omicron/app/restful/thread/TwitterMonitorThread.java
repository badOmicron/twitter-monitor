package mx.bhit.omicron.app.restful.thread;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bhit.omicron.app.restful.model.DataItems;
import mx.bhit.omicron.app.restful.model.SocialNetworksMonitoredData;
import mx.bhit.omicron.app.restful.model.TwitterMessage;
import mx.bhit.omicron.app.restful.task.TwitterTask;
import mx.bhit.omicron.app.restful.utils.TwitterUtil;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * TODO [Agregar documentación de la clase]
 * @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
 * @version 1.0
 */
public class TwitterMonitorThread implements Callable<SocialNetworksMonitoredData> {
    private static final Logger logger = LoggerFactory.getLogger(TwitterMonitorThread.class);

    private Twitter twitter;
    private DataItems dataItems;
    private SocialNetworksMonitoredData socialNetworksMonitoredData;
    private String expression;
    private Boolean query;
    private Query queryTwitter;

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public TwitterMonitorThread() {
        logger.info("configurando thread de monitor");
        // Obtienes una instancia de Twitter.
        this.twitter = new TwitterFactory().getInstance();
        /*
         * Creas el acceso a tu cuenta, para generar las claves de autorización
         * desde tu cuenta visita el siguiente sitio: https://apps.twitter.com/
         * Creas una aplicación. Despues de crearla te vas a la sección Keys and
         * Access Tokens , ahí podrás ver las llaves que debes poner en la
         * siguiente sección.
         */
        this.twitter.setOAuthConsumer("q8TheJW3NLMdSFKkpMAeeeoqA",
            "0Uk167lb5OSsgimUpWlnYozC5GzL3TLBC0Um3oieFyWvQF0bOW");
        this.twitter.setOAuthAccessToken(new AccessToken("3877098085-skJ2Sat6hL3iW6IXWBxAV3MZJVOMi3venxRITjm",
            "RWFECCxUo1Fe4WJyhVrXGO2o0DBl9lPDEqN1vV4W8OaUl"));
        socialNetworksMonitoredData = new SocialNetworksMonitoredData();
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param expression
     * @param query
     */
    public TwitterMonitorThread(Boolean query, String expression) {
        this.query = query;
        this.expression = expression;
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @return Objecto de respuesta {@link SocialNetworksMonitoredData}
     */
    private SocialNetworksMonitoredData getData() {
        dataItems = new DataItems();
        try {
            logger.info("query false");
            ResponseList<Status> userTL;
            // Lista de mensajes UserTimeline
            userTL = twitter.getUserTimeline();
            // Lista de mensajes HomeTimeLine
            // ResponseList<Status> homeTL = twitter.getHomeTimeline();
            for (Status status : userTL) {
                String url = TwitterUtil.getURLTwitterTopic(status.getUser(), status.getId());
                dataItems.getDataItems().add(new TwitterMessage(new Long(status.getId()), status.getCreatedAt(),
                    status.getText().toString(), "@" + status.getUser().getScreenName(), url));
                int numDataItems = dataItems.getDataItems().size();
                socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("searchTimeStamp", new Date());
                socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("numDataItems", numDataItems);
                socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("dataItems",
                    dataItems.getDataItems());
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("error...");
            System.out.println(e.getMessage());
            e.printStackTrace();
            TwitterTask.stop();
            return new SocialNetworksMonitoredData();
        }
        return getSocialNetworksMonitoredData();
    }

    private SocialNetworksMonitoredData getQueryData() {
        logger.info("query true");
        queryTwitter = new Query(getExpression());
        QueryResult result;
        try {
            do {
                result = twitter.search(queryTwitter);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());

                    String url = TwitterUtil.getURLTwitterTopic(tweet.getUser(), tweet.getId());
                    dataItems.getDataItems().add(new TwitterMessage(new Long(tweet.getId()), tweet.getCreatedAt(),
                        tweet.getText().toString(), "@" + tweet.getUser().getScreenName(), url));
                    int numDataItems = dataItems.getDataItems().size();
                    socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("searchTimeStamp",
                        new Date());
                    socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("numDataItems", numDataItems);
                    socialNetworksMonitoredData.getSocialNetworksMonitoredData().put("dataItems",
                        dataItems.getDataItems());
                }
            } while ((queryTwitter = result.nextQuery()) != null);
        } catch (Exception e) {
            // TODO: handle exception
            // TODO: handle exception
            logger.error("error...");
            System.out.println(e.getMessage());
            e.printStackTrace();
            TwitterTask.stop();
            return new SocialNetworksMonitoredData();
        }
        return getSocialNetworksMonitoredData();
    }

    /* La documentación de este método se encuentra en la clase o interface que
     * lo declara (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public SocialNetworksMonitoredData call() throws Exception {
        // TODO [codificar el cuerpo del método]
        logger.info("validando query");
        if (getQuery()) {
            return getQueryData();
        }
        return getData();
    }

    /**
     * @return el atributo socialNetworksMonitoredData
     */
    public SocialNetworksMonitoredData getSocialNetworksMonitoredData() {
        return socialNetworksMonitoredData;
    }

    /**
     * @param socialNetworksMonitoredData parametro socialNetworksMonitoredData a actualizar
     */
    public void setSocialNetworksMonitoredData(SocialNetworksMonitoredData socialNetworksMonitoredData) {
        this.socialNetworksMonitoredData = socialNetworksMonitoredData;
    }

    /**
     * @return el atributo expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * @param expression parametro expression a actualizar
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * @return el atributo query
     */
    public Boolean getQuery() {
        return query;
    }

    /**
     * @param query parametro query a actualizar
     */
    public void setQuery(Boolean query) {
        this.query = query;
    }

}
