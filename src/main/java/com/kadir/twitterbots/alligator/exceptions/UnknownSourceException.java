package com.kadir.twitterbots.alligator.exceptions;

import com.kadir.twitterbots.alligator.enumerations.SourceType;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 21:12
 */
public class UnknownSourceException extends RuntimeException {
    private static final String MESSAGE = "Unknown Source Exception: ";

    public UnknownSourceException(SourceType sourceType) {
        super(MESSAGE + sourceType);
    }

}