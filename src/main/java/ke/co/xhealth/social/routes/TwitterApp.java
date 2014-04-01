package ke.co.xhealth.social.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * This processing route posts new entries into twitter
 * 
 * @author nelson.okello
 * 
 */
public class TwitterApp extends RouteBuilder {

  public void configure() {

    from("timer://twitterController?fixedRate=true&period=20000").routeId("twitterApp")

    .noAutoStartup()

    .setHeader("TWITTER_API_KEY", simple("{{twitter.api.key}}"))
        .setHeader("TWITTER_API_SECRET", simple("{{twitter.api.secret}}"))
        .setHeader("TWITTER_ACCESS_TOKE", simple("{{twitter.access.token}}"))
        .setHeader("TWITTER_ACCESS_SECRET", simple("{{twitter.access.secret}}"))

        .to("bean:twitterAppService");

    /**
     * Handling HTTP Requests
     */
    from("jetty:http://0.0.0.0:8181/xhealth/testService").to("bean:messageDBUpdateService");

  }
}