/**
 * This class feeds mocked metrics messages into kafka
 * 
 * 
 * MIT License
 *
 * Copyright (c) 2017 Michael Heijmans <mikeheijmans.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.kitfox.kafka.mock;

import org.apache.kafka.clients.producer.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class StatsProducer extends Thread{

		private KafkaProducer<Integer, String> producer;
		private String topic;
		
		private MetricMocker metricMocker = new MetricMocker();
		
		/**
		 * 
		 * Main loop runs the producer until interrupted.
		 */
		public static void main(String[] args) {
			StatsProducer producer = new StatsProducer("test");
			producer.run();
		}
		
		/**
		 * Setups an object for feeding.
		 * 
		 * @param topic - the topic to feed into
		 */
		public StatsProducer(String topic) {
			Properties props = new Properties();
			props.put("bootstrap.servers", "192.168.99.100:9092");
			props.put("client.id", "MockStatsProducer");
			props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			producer = new KafkaProducer<>(props);
			this.topic = topic;
		}
		
		/**
		 * Feeds mock metrics into kafka until interrupted
		 */
		public void run() {
			int messageNo = 1;
			while (true) {
				
				// Build a mocked metric with some random data
				String metric = metricMocker.MetricAsString();
				
				try {
					producer.send(new ProducerRecord<>(topic, messageNo, metric)).get();
					System.out.println("Sent Message: (" + messageNo + ", " + metric + ")");
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				++messageNo;
			}
		}
	
}
