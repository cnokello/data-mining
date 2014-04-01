package ke.co.xhealth.social.services;

import ke.co.xhealth.social.utils.TwitterAppUtils;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This posts twitter updates.
 * 
 * It pulls the update message from a Cassandra DB, posts to twitter and mark the message as having
 * been updated to twitter
 * 
 * @author nelson.okello
 * 
 */
@Service(value = "twitterAppService")
public class TwitterAppService implements Processor {

  private Logger logger = LoggerFactory.getLogger(TwitterAppService.class);

  private @Autowired
  TwitterAppUtils twitterAppUtils;

  @Override
  public void process(Exchange exchange) throws Exception {
    final String apiKey = exchange.getIn().getHeader("TWITTER_API_KEY", String.class);
    final String apiSecret = exchange.getIn().getHeader("TWITTER_API_SECRET", String.class);
    final String accessToken = exchange.getIn().getHeader("TWITTER_ACCESS_TOKE", String.class);
    final String accessTokenSecret = exchange.getIn().getHeader("TWITTER_ACCESS_SECRET",
        String.class);

    twitterAppUtils.postUpdate(apiKey, apiSecret, accessToken, accessTokenSecret);

    logger.info(String.format("\nAPI KEY: %s\nAPI SECRET: %s\nACCESS TOKEN: %s\n\n", apiKey,
        apiSecret, accessToken));

  }

}
