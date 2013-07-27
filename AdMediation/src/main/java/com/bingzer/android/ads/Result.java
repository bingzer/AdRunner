package com.bingzer.android.ads;

public class Result {

	boolean success = true;
	String message = null;
	
	public Result(){
		this(true);
	}
	
	public Result(boolean success){
		this(success, null);
	}
	
	public Result(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	
	public Result(Result result){
		this.success = result.success;
		this.message = result.message;
	}
	
	public Result message(String message){
		this.message = message;
		return this;
	}
	
	public String message(){
		return message;
	}
	
	public Result success(boolean success){
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
	public Result consume(Throwable e){
		success = false;
		message = e.getMessage() == null ? e.toString() : e.getMessage();
		return this;
	}
	
	/**
	 * Good result
	 */
	public static final FixedResult Good = new FixedResult(true);
	
	/**
	 * Bad result
	 */
	public static final FixedResult Bad  = new FixedResult(false);
	
	/**
	 * Reresents a fixed result. You can only modify the message
	 * @author 11856
	 *
	 */
	public static class FixedResult extends Result {
		
		public FixedResult(boolean success){
			this.success = success;
		}
		
		@Deprecated
		public Result success(boolean success){
			return this;
		}
	}
}
