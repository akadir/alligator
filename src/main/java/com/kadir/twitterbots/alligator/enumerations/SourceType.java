package com.kadir.twitterbots.alligator.enumerations;

/**
 * @author akadir
 * Date: 2019-05-07
 * Time: 21:38
 */
public enum SourceType {
    FAVORITE_LIST, TWITTER_DATA_JS;

    private final int value;

    SourceType() {
        this.value = ordinal();
    }
}
