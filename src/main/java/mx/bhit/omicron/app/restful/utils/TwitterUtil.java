package mx.bhit.omicron.app.restful.utils;

import twitter4j.User;

public class TwitterUtil {

    public static String getURLTwitterTopic(User user, long id) {
        return "https://twitter.com/" + user.getScreenName() + "/status/" + id;
    }

    public static long miliToSec(long mili) {
        return (mili * 1000);
    }

}
