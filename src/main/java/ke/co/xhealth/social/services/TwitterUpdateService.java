package ke.co.xhealth.social.services;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * This posts twitter updates.
 * 
 * It pulls the update message from a Cassandra DB, posts to twitter and mark the message as having
 * been updated to twitter
 * 
 * @author nelson.okello
 * 
 */
@Service(value = "twitterUpdateService")
public class TwitterUpdateService implements Processor {

  Logger logger = LoggerFactory.getLogger(TwitterUpdateService.class);

  @Override
  public void process(Exchange exchange) throws Exception {
    final String apiKey = exchange.getIn().getHeader("TWITTER_API_KEY", String.class);
    final String apiSecret = exchange.getIn().getHeader("TWITTER_API_SECRET", String.class);
    final String accessToken = exchange.getIn().getHeader("TWITTER_ACCESS_TOKE", String.class);
    final String accessTokenSecret = exchange.getIn().getHeader("TWITTER_ACCESS_SECRET",
        String.class);

    logger.info(String.format("\nAPI KEY: %s\nAPI SECRET: %s\nACCESS TOKEN: %s\n\n", apiKey,
        apiSecret, accessToken));

    Twitter twitter = TwitterFactory.getSingleton();
    twitter.setOAuthConsumer(apiKey, apiSecret);

    AccessToken apiAccessToken = new AccessToken(accessToken, accessTokenSecret);
    twitter.setOAuthAccessToken(apiAccessToken);
    twitter.updateStatus("I thought this would be funny:) And sure, it is!!");
  }

}
