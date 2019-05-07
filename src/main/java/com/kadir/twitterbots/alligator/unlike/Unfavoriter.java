package com.kadir.twitterbots.alligator.unlike;

import com.kadir.twitterbots.alligator.util.Constants;
import com.kadir.twitterbots.authentication.BotAuthenticator;
import com.kadir.twitterbots.ratelimithandler.handler.RateLimitHandler;
import com.kadir.twitterbots.ratelimithandler.process.ApiProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * @author akadir
 * Date: 2019-05-07
 * Time: 21:28
 */
public class Unfavoriter implements IUnfavoriter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Twitter twitter;

    public void authenticate() {
        this.twitter = BotAuthenticator.authenticate(Constants.AUTH_FILE_NAME, "");
    }

    @Override
    public boolean unfavorite(long statusId) throws TwitterException, InterruptedException {
        Status status = twitter.showStatus(statusId);
        String statusTextLineBreaksRemoved = status.getText().replaceAll("\\r\\n|\\r|\\n", " ");
        String statusLink = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
        RateLimitHandler.handle(twitter.getId(), status.getRateLimitStatus(), ApiProcessType.SHOW_STATUS);
        logger.info("Tweet will be unfavorited. Link:  {} - Text {}", statusLink, statusTextLineBreaksRemoved);
        status = twitter.destroyFavorite(status.getId());
        RateLimitHandler.handle(twitter.getId(), status.getRateLimitStatus(), ApiProcessType.DESTROY_FAVORITE);
        return true;
    }
}
