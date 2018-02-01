package larc.StormCrawlerTester.storm.topology;


import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import larc.StormCrawlerTester.storm.bolt.HashtagCounterBolt;
import larc.StormCrawlerTester.storm.bolt.HashtagReaderBolt;
import larc.StormCrawlerTester.storm.spout.TwitterSampleSpout;

public class CrawlerTopology {
	public static void main(String[] args) throws Exception{
/*		String consumerKey = args[0];
		String consumerSecret = args[1];

		String accessToken = args[2];
		String accessTokenSecret = args[3];

		String[] arguments = args.clone();
		String[] keyWords = Arrays.copyOfRange(arguments, 4, arguments.length);*/
				String[] keyWords = {"cook","cooking","livemusic","android","bigdata"};
				String consumerSecret="3a4Mosi8RAjJzq8OiCsq4uzsduZ2X0xG2kpPkle4t6cLNOMHrq";
				String  consumerKey="wzQouiDWsbVCIJU0wylEXkYmw";
				String accessToken="2562485102-xXxKvzdDCI3pnnthoA626UM1ZQ6FmaDlAGZp805";
				String accessTokenSecret="dbgIsb19LZv77XBpAeIecCbaIUUuHMa7bcxVj7s84vKgp";
		
		
				long numberOfFollowUsers = 1000;
				int numSpoutExecutors =(int)Math.ceil(numberOfFollowUsers/(double)10);
				TwitterSampleSpout spout =new TwitterSampleSpout(consumerKey,consumerSecret, accessToken, accessTokenSecret, keyWords);
				TopologyBuilder builder = new TopologyBuilder();
				builder.setSpout("test-twitter-spout", spout,numSpoutExecutors).setMaxSpoutPending(100);
				
				int numWorker = (int)Math.ceil(numSpoutExecutors/4.0);
				int numBoltExecutors = numWorker;
				builder.setBolt("twitter-hashtag-reader-bolt", new HashtagReaderBolt(), numBoltExecutors).shuffleGrouping("test-twitter-spout");
				builder.setBolt("twitter-hashtag-counter-bolt", new HashtagCounterBolt()).fieldsGrouping("twitter-hashtag-reader-bolt", new Fields("hashtag"));
				
				Config config = new Config();
				config.setDebug(true);
				config.setNumWorkers(numWorker + (numBoltExecutors/2));
				
				//LocalCluster cluster = new LocalCluster();
				StormSubmitter.submitTopology("twitter-hashtag-storm",config, builder.createTopology());
				/*
				System.out.println("Shutdown cluster");
				System.out.println("cluster sleep  for 2 minute");
				Thread.sleep(120000);*/
	}

}
