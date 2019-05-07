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
        for (Long statusId : statusList) {
            try {
                logger.info("Check id: {}", statusId);
                if (unfavoriter.unfavorite(statusId)) {
                    unfavoritedCount++;
                }
            } catch (TwitterException e) {
                if (e.getErrorCode() == TwitterErrorCode.STATUS_NOT_FOUND.getValue()) {
                    logger.error("{} id not found", statusId);
                } else {
                    logger.error("An error occurred: ", e);
                }
            } catch (InterruptedException e) {
                logger.error("Thread interrupted: ", e);
                Thread.currentThread().interrupt();
            }
        }
        logger.info("{} tweets were unfavorited.", unfavoritedCount);
    }

    @Override
    public void logUnfavoritedCount() {
        logger.info("{} tweets were unfavorited.", unfavoritedCount);
    }
}
