package ke.co.xhealth.social.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;

/**
 * This processing route posts new entries into twitter
 * 
 * @author nelson.okello
 * 
 */
public class TwitterApp extends RouteBuilder {

  private @Value("${api.key}")
  String API_KEY;

  private @Value("${api.secret}")
  String API_SECRET;

  private @Value("${access.token}")
  String ACCESS_TOKEN;

  private @Value("${access.secret}")
  String ACCESS_TOKEN_SECRET;

  public void configure() {

    from("timer://twitterController?fixedRate=true&period=20000").routeId("twitterApp")

    .setHeader("TWITTER_API_KEY", simple(API_KEY))
        .setHeader("TWITTER_API_SECRET", simple(API_SECRET))
        .setHeader("TWITTER_ACCESS_TOKE", simple(ACCESS_TOKEN))
        .setHeader("TWITTER_ACCESS_SECRET", simple(ACCESS_TOKEN_SECRET))

        .to("bean:twitterUpdateService");

  }
}