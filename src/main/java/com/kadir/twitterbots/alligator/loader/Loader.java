package com.kadir.twitterbots.alligator.loader;

import java.util.List;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 20:41
 */
public interface Loader {
    List<Long> load(String filePath);
}
