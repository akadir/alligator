package com.kadir.twitterbots.alligator.loader;

import com.kadir.twitterbots.alligator.enumerations.SourceType;
import com.kadir.twitterbots.alligator.exceptions.UnknownSourceException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 20:42
 */
public class SourceLoaderFactory {
    private static Map<SourceType, Loader> loaderMap;

    static {
        loaderMap = new HashMap<>();
        loaderMap.put(SourceType.FAVORITE_LIST, new SourceLoaderFromStatusIdFile());
    }

    private SourceLoaderFactory() {

    }

    public static Loader getLoader(SourceType sourceType) {
        Loader loader = loaderMap.get(sourceType);

        if (loader == null) {
            throw new UnknownSourceException(sourceType);
        } else {
            return loader;
        }
    }
}
