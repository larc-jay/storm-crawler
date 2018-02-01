package larc.StormCrawlerTester.storm.bolt;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import twitter4j.HashtagEntity;
import twitter4j.Status;

@SuppressWarnings("serial")
public class HashtagReaderBolt  extends BaseRichBolt {
	   private OutputCollector collector;

	   public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
	      this.collector = collector;
	   }
	   public void execute(Tuple tuple) {
	      Status tweet = (Status) tuple.getValueByField("tweet");
	      for(HashtagEntity hashtage : tweet.getHashtagEntities()) {
	         System.out.println("Hashtag: " + hashtage.getText());
	         this.collector.emit(new Values(hashtage.getText()));
	      }
	   }
	   @Override
	   public void cleanup() {}

	   public void declareOutputFields(OutputFieldsDeclarer declarer) {
	      declarer.declare(new Fields("hashtag"));
	   }
		
	   public Map<String, Object> getComponentConfiguration() {
	      return null;
	   }
		
	}