package org.kitfox.operators;

import java.util.Collection;

public class UnaryAnd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection a = null;

		System.out.println(a != null & 1 > 0);
	}

}
