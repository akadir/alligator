package com.kadir.twitterbots.alligator.unlike;

import twitter4j.TwitterException;

/**
 * @author akadir
 * Date: 2019-05-07
 * Time: 21:39
 */
public interface IUnfavoriter {
    boolean unfavorite(long statusId) throws TwitterException;
}
