package larc.StormCrawlerTester.config;

import java.util.concurrent.TimeUnit;

public class Constant {
	public static final String PREFIX = "plr_id_";
	public static final String TWITTER_STREAM_API_FOLDER = "twitter_api_key";
	public static final String TWITTER_GEOSTREAM_API_FOLDER = "twitter_geostream_api_key";
	public static final int MAX_TRACK_USERS = 10;
	public static final int MAX_QUEUE_CAPACITY = 100000;
	public static final long SLEEP_PERIOD = 5000;
	public static final long CHECK_PERIOD = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
	public static final String CONFIG_FILE = "config.properties";
}
