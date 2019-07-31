package com.kadir.twitterbots.alligator.enumerations;

/**
 * @author akadir
 * Date: 2019-05-07
 * Time: 18:53
 */
public enum TwitterErrorCode {
    STATUS_NOT_FOUND(144), RATE_LIMIT_EXCEEDED(88), BLOCKED_BY_USER(136),
    USER_SUSPENDED(63), PAGE_NOT_EXIST(34), NOT_AUTHORIZED_TO_SEE_THAT(136);

    private int value;

    TwitterErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
