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
    from("timer://testTimer?period=5000").log("API KEY: " + API_KEY + "\n");
  }

}