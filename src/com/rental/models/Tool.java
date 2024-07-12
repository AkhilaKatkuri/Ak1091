package com.rental.models;



public class Tool {
    private String code;
    private String type;
    private String brand;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public Tool(String code, String type, String brand, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

	public String getCode() {
		return this.code;
	}

	public String getType() {
		return this.type;
	}

	public String getBrand() {
		return this.brand;
	}

	public double getDailyCharge() {
		return this.dailyCharge;
	}
	
	public boolean getweekendCharge() {
		return this.weekendCharge;
	}
	
	public boolean getholidayCharge() {
		return this.holidayCharge;
	}
}