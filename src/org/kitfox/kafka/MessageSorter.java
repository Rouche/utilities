/**
 * This class sorts messages into different topics based on the contents of the message
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

package org.kitfox.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.apache.kafka.connect.json.JsonSerializer;
import org.apache.kafka.streams.kstream.Produced;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Properties;

public class MessageSorter {

	// The topic we want the source data to come from
	public static final String MAIN_TOPIC = "test";
	
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "message-sorter");

		// Change this to YOUR kafka server or just set the KAFKA ENV variable!
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("KAFKA"));
		// Change this to YOUR zookeeper server or just set the ZOOKEEPER ENV variable!
//		props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, System.getenv("ZOOKEEPER"));

		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		// Reset to the earliest position so we can reprocess the messags for the demo
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		// load a simple json serializer
		final Serializer<JsonNode> jsonSerializer = new JsonSerializer();
		// load a simple json deserializer
        final Deserializer<JsonNode> jsonDeserializer = new JsonDeserializer();
        // use the simple json serializer and deserialzer we just made and load a Serde for streaming data
        final Serde<JsonNode> jsonSerde = Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
        
        // Setup a string Serde for the key portion of the messages
        final Serde<String> stringSerde = Serdes.String();
		
        // Setup a builder for the streams
     	StreamsBuilder builder = new StreamsBuilder();
        
		// Just so we know everything is working.. let's report that we are about to start working
		System.out.println("Starting Sorting Job");
		
		// Lets start a source and set it to deserialize the key as a String and the value as a JSON object.
		//
		// We know that all of our messages are keyed with a string and the payload is a string representation of
		//   a JSON object. By assigning our jsonSerde to the value, Kafka, will automatically deserialize the value
		//   as a JSON object so we can navigate the data in the JSON tree without any extra work.
		KStream<String, JsonNode> source = builder.stream(MAIN_TOPIC);
		
		
		// Predicates... these are fun to figure out...
		// Think of Predicates as simple matchers that know how to traverse the data and return a boolean
		//
		// At this point we want to setup a few Predicates to use for sorting
		
		// This Predicate is checking to see if the Hostname is mockedhost1.mikeheijmans.com
		Predicate<String, JsonNode> isFirstHost = (k, v) ->
			v.path("host")                           // Read the value of "host" from the json object
			.asText()                                // Turn it into a String -> This is a gotcha for newbies like me...
			.equals("mockedhost1.mikeheijmans.com"); // Return a boolean of what we want
		
		// This Predicate is checking if the metrics object is of the type "Counter"
		Predicate<String, JsonNode> isCounter = (k, v) -> 
			v.path("type")       // Read the value of "type" from the json object
			.asText()            // Turn it into a String -> This is a gotcha for newbies like me...
			.equals("counter");  // Return a boolean of what we want
				
		// This Predicate is checking if the metrics object is of the type "Gauge"
		Predicate<String, JsonNode> isGauge = (k, v) -> 
			v.path("type") 		 // Read the value of "type" from the json object
			.asText()     		 // Turn it into a String -> This is a gotcha for newbies like me...
			.equals("gauge");    // Return a boolean of what we want
		
		
		// Simple filtering processor
		// We setup another stream called firstHost to receive messages that match the filter with the
		//   the "isFirstHost" predicate
		KStream<String, JsonNode> firstHost = source.filter(isFirstHost);
		
		// Take all messages coming into the firstHost steam and sink them to the 'firsthost' topic
		//   we are re-serializing the object back into a string using our jsonSerde on the value field. 
		firstHost.to("firsthost", Produced.with(stringSerde, jsonSerde));
		
		// Branching processor
		// Here we setup a branching filter to branch gauges and counters into seperate streams
		//   Everything that matches the first Predicate is put into index 0 of the array and
		//   everything that matches the second Predicate is put into the index of 1 of the array
		//   and so on for multiple branching Predicates
		KStream<String, JsonNode>[] metricTypes = source.branch(new Predicate[]{isGauge, isCounter});

		///////////////////////////////
		// ROUCHE_DOCS: IDEE DE SOLUTION
		///////////////////////////////
		// - Garder toutes les cles de transaction pendant le 1er read du fichier.
		// - creer une topic par transaction differente
		// - Faire un branch du source pour chaque topic avec un predicate sur la transaction
		// - Relire chaque topic 1 par une et remettre dans une topic de fin.

		// Take all messages coming into the metricTypes streams and sink them to the proper topics
		// In this case, metricTypes[0] is filled with gauge events and metricTypes[1] is filled with counter
		//   events. We sink them to appropriately named topics. And again, we use the stringSerde for the key
		//   and the jsonSerde for the value so that the json object gets turned back to a string properly on egress.
		metricTypes[0].to("gauges", Produced.with(stringSerde, jsonSerde));
		metricTypes[1].to("counters", Produced.with(stringSerde, jsonSerde));
		
		// Now lets user that builder and the props we set to setup a streams object
		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		
		// Start the streaming processor
		streams.start();
		
		// Gracefully shutdown on an interrupt
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}
	
}
