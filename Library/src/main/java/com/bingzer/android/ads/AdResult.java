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

public class AdResult {

	boolean success = true;
	String message = null;
	
	public AdResult(){
		this(true);
	}
	
	public AdResult(boolean success){
		this(success, null);
	}
	
	public AdResult(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	
	public AdResult(AdResult adResult){
		this.success = adResult.success;
		this.message = adResult.message;
	}
	
	public AdResult message(String message){
		this.message = message;
		return this;
	}
	
	public String message(){
		return message;
	}
	
	public AdResult success(boolean success){
		this.success = success;
		return this;
	}
	
	public boolean success(){
		return success;
	}
	
	/**
	 * Consume from a {@link Throwable}
	 * @param e
	 * @return
	 */
	public AdResult consume(Throwable e){
		success = false;
		message = e.getMessage() == null ? e.toString() : e.getMessage();
		return this;
	}
	
	/**
	 * Good result
	 */
	public static final FixedAdResult Good = new FixedAdResult(true);
	
	/**
	 * Bad result
	 */
	public static final FixedAdResult Bad  = new FixedAdResult(false);
	
	/**
	 * Reresents a fixed result. You can only modify the message
	 * @author Ricky Tobing
	 *
	 */
	public static class FixedAdResult extends AdResult {
		
		public FixedAdResult(boolean success){
			this.success = success;
		}
		
		@Deprecated
		public AdResult success(boolean success){
			return this;
		}
	}
}
