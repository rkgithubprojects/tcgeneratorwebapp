package com.cigniti.airlines.reports;

public class Result {

	private int stepCount;
	private String stepDescription;
	private boolean status = false;
	private String operation;
	private String screenShotName;

	public Result(String operation,int stepCount, String stepDescription, boolean status,String screenShotName) {
		this.stepCount = stepCount;
		this.stepDescription = stepDescription;
		this.status = status;
		this.operation=operation;
		this.screenShotName=screenShotName;
	}

	public String getScreenShotName() {
		return screenShotName;
	}
	public int getStepCount() {
		return stepCount;
	}

	public String getStepDescription() {
		return stepDescription;
	}

	public String getOperation() {
		return operation;
	}
	
	public boolean getStatus() {
		return status;
	}
	

}
