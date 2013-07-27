package com.bingzer.android.ads;

/**
 * Reprsents range.
 * Range has minimum and maximum
 * @author Ricky
 *
 */
public class Range {
	/**
	 * The minimum
	 */
	public float minimum;
	
	/**
	 * The maximum
	 */
	public float maximum;
	
	/**
	 * Checks to see if <code>point</code>
	 * is within range
	 * @param point
	 * @return
	 */
	public boolean inRange(float point){
		return point >= minimum && point <= maximum;
	}
	
}
