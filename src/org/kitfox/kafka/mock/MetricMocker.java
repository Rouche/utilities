/**
 * This class mocks a simple metrics object for feeding into Kafka
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

import org.json.simple.*;
import java.util.*;

public class MetricMocker {

	/**
     * Returns a json object of mocked metrics data
     *
     * @return  a mocked metrics json object
     */
	public JSONObject metricAsJSON() {
		// Returns a loaded mock metrics json object
		
		JSONObject metricObj = new JSONObject();
		
		metricObj.put("tags", mockTags());
		metricObj.put("metric", mockMetricString());
		metricObj.put("interval", new Float(10.0));
		metricObj.put("device_name", null);
		metricObj.put("host", mockHostname());
		metricObj.put("points", mockPoints());
		metricObj.put("type", mockType());
		
		return metricObj;
		
	}
	
	/**
     * Returns a string representation of a json object of mocked metrics data
     *
     * @return  a json string of mocked metrics data
     */
	public String MetricAsString() {
		// Returns a loaded mock metrics json object
		JSONObject metricObj = metricAsJSON();
		return metricObj.toString();
	}
	
	
	// Private Methods
	
	// Returns a random tag array
	private JSONArray mockTags() {
		JSONArray tags = new JSONArray();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("env:production");
		list.add("env:staging");
		list.add("env:development");
		
		Random randomizer = new Random();
		String random = list.get(randomizer.nextInt(list.size()));
		
		tags.add(random);
		
		return tags;
	}
	
	// Returns a random metric name
	private String mockMetricString() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("datadog.dogstatsd.mocked.count");
		list.add("datadog.dogstatsd.mocked.other");
		
		Random randomizer = new Random();
		String random = list.get(randomizer.nextInt(list.size()));
		return random;
	}
	
	// Returns a mocked datapoints array
	private JSONArray mockPoints() {
		JSONArray points = new JSONArray();
		
		JSONArray point1 = new JSONArray();
		JSONArray point2 = new JSONArray();
		
		point1.add(new Long(1484321980));
		point1.add(new Integer(1));
		
		point2.add(new Long(1484331980));
		point2.add(new Integer(10));
		
		points.add(point1);
		points.add(point2);
		
		return points;
	}
	
	// Returns a random metric type from the list
	private String mockType() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("gauge");
		list.add("counter");
		
		Random randomizer = new Random();
		String random = list.get(randomizer.nextInt(list.size()));
		return random;
	}
	
	// Returns a random hostname from the list
	private String mockHostname() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("mockedhost1.mikeheijmans.com");
		list.add("mockedhost2.mikeheijmans.com");
		
		Random randomizer = new Random();
		String random = list.get(randomizer.nextInt(list.size()));
		return random;
	}
	
}
