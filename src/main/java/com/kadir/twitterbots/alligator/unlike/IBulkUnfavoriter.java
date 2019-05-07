package com.kadir.twitterbots.alligator.unlike;

import java.util.List;

/**
 * @author akadir
 * Date: 2019-05-06
 * Time: 21:00
 */
public interface IBulkUnfavoriter {
    void unfavorite(List<Long> statusList, IUnfavoriter unfavoriter);

    void logUnfavoritedCount();
}
