package com.kadir.twitterbots.alligator;

import com.kadir.twitterbots.alligator.enumerations.SourceType;
import com.kadir.twitterbots.alligator.loader.Loader;
import com.kadir.twitterbots.alligator.loader.SourceLoaderFactory;
import com.kadir.twitterbots.alligator.unlike.BulkUnfavoriter;
import com.kadir.twitterbots.alligator.unlike.Unfavoriter;
import com.kadir.twitterbots.alligator.util.Config;
import com.kadir.twitterbots.alligator.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author akadir
 * Date: 2019-05-07
 * Time: 21:15
 */
public class Alligator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BulkUnfavoriter bulkUnfavoriter;

    public Alligator() {
        this.bulkUnfavoriter = new BulkUnfavoriter();
    }

    public static void main(String[] args) {
        Alligator alligator = new Alligator();
        Runtime.getRuntime().addShutdownHook(new Thread(alligator::logUnfavoriteCount));
        alligator.start();
    }

    public void start() {
        List<Long> statusIdList = loadStatusList();
        Unfavoriter unfavoriter = new Unfavoriter();
        unfavoriter.authenticate();
        bulkUnfavoriter.unfavorite(statusIdList, unfavoriter);

    }

    private List<Long> loadStatusList() {
        SourceType sourceType = SourceType.values()[Config.getPropertyAsInt(Constants.SOURCE_TYPE_KEY)];
        logger.info("Get sourceType as {}", sourceType);
        String filePath = Config.getPropertyAsString(Constants.STATUS_LIST_FILE_NAME_LOCATION_KEY);
        logger.info("Get filePath as {}", filePath);
        Loader loader = SourceLoaderFactory.getLoader(sourceType);

        return loader.load(filePath);
    }

    public void logUnfavoriteCount() {
        bulkUnfavoriter.logUnfavoritedCount();
    }
}
