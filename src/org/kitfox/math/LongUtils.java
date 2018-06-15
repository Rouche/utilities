package org.kitfox.math;

public class LongUtils {
	private LongUtils() {
	}

	public static void main(String args[]) throws Exception {
		String text = "3546356356 %".replaceAll("\\%| ", "");
		Long val = Long.valueOf(text);
		System.out.println(val);
	}
}