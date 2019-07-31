package com.kadir.twitterbots.alligator.unlike;

import com.kadir.twitterbots.alligator.enumerations.TwitterErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

import java.util.List;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 20:59
 */
public class BulkUnfavoriter implements IBulkUnfavoriter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int unfavoritedCount = 0;

    @Override
    public void unfavorite(List<Long> statusList, IUnfavoriter unfavoriter) {
        for (int i = 0; i < statusList.size(); i++) {
            long statusId = statusList.get(i);
            try {
                logger.info("Check id: {}", statusId);
                if (unfavoriter.unfavorite(statusId)) {
                    unfavoritedCount++;
                }
            } catch (TwitterException e) {
                if (e.getErrorCode() == TwitterErrorCode.STATUS_NOT_FOUND.getValue()) {
                    logger.error("{} id not found", statusId);
                } else if (e.getErrorCode() == TwitterErrorCode.RATE_LIMIT_EXCEEDED.getValue()) {
                    logger.error("Rate limit exceeded, wait 30 sc. {}", e.getErrorMessage());
                    try {
                        Thread.sleep(30 * 1000L);
                    } catch (InterruptedException ex) {
                        logger.error("Thread interrupted: ", ex);
                        Thread.currentThread().interrupt();
                    }
                } else {
                    logger.error("An error occurred: {}", e.getErrorMessage());
                }
            }
        }
        logger.info("{} tweets were unfavorited.", unfavoritedCount);
    }

    @Override
    public void logUnfavoritedCount() {
        logger.info("{} tweets were unfavorited.", unfavoritedCount);
    }
}
