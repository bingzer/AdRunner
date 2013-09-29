/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	 */
	public boolean inRange(float point){
		return point >= minimum && point <= maximum;
	}
	
}
