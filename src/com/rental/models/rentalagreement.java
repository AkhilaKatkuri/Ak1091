package com.rental.models;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class rentalagreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    public void print() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
        DecimalFormat percentFormat = new DecimalFormat("0%");
        
        int chargeDays = calculateChargeDays(checkoutDate, dueDate,rentalDays);
        double preDiscountCharge = chargeDays * tool.getDailyCharge();
        double discountAmount = (preDiscountCharge * discountPercent) / 100;
        double finalCharge = preDiscountCharge - discountAmount;
        

        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate);
        System.out.println("Due date: " + dueDate);
        System.out.println("Daily rental charge: " + currencyFormat.format(tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + currencyFormat.format(preDiscountCharge));
        System.out.println("Discount percent: " + discountPercent);
        System.out.println("Discount amount: " + currencyFormat.format(discountAmount));
        System.out.println("Final charge: " + currencyFormat.format(finalCharge));
    }

	public void setTool(Tool tool2) {
		this.tool = tool2;
	}

	public void setDueDate(LocalDate dueDate2) {
		this.dueDate = dueDate2;
	}

	public void setCheckoutDate(LocalDate checkoutDate2) {
		this.checkoutDate = checkoutDate2;
		
	}

	public void setRentalDays(int rentalDays2) {
		this.rentalDays = rentalDays2;
	}

	public void setDiscountPercent(int discountPercent2) {
		this.discountPercent = discountPercent2;
	}
	
	private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, int rentalDays) {
    	int weekends = this.countWeekendDaysBetween(checkoutDate, dueDate);
    	int julyholiday = this.isJuly4thBetween(checkoutDate, dueDate);
    	int sepholiday = this.isFirstMondayOfSeptemberBetween(checkoutDate, dueDate);
    	
    	int totalChargeDays = rentalDays + 1;
    	if (this.tool.getweekendCharge()) {
    		totalChargeDays = totalChargeDays - weekends;
    	}
    	
    	if (this.tool.getholidayCharge()) {
    		totalChargeDays = totalChargeDays - julyholiday - sepholiday;
    	}
    	
    	return totalChargeDays;
	}

	private double calculatePreDiscountCharge(Tool tool, int rentalDays, LocalDate checkoutDate) {
    	return 0;
    }
	
	private int countWeekendDaysBetween(LocalDate startDate, LocalDate endDate) {
        int weekendDays = 0;

        // Iterate through each day between startDate and endDate
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekendDays++;
            }
        }

        return weekendDays;
    }
	
	private int isJuly4thBetween(LocalDate startDate, LocalDate endDate) {
        // Iterate through each day between startDate and endDate
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            // Check if the date is July 4th
            if (date.getMonth() == Month.JULY && date.getDayOfMonth() == 4) {
                return 1;
            }
        }
        return 0;
    }
	
	private int isFirstMondayOfSeptemberBetween(LocalDate startDate, LocalDate endDate) {
        // Get the year of the start date
        int startYear = startDate.getYear();
        int endYear = endDate.getYear();

        // Iterate through each year from startYear to endYear
        for (int year = startYear; year <= endYear; year++) {
            // Get the first Monday of September for the current year
            LocalDate firstMondayOfSeptember = LocalDate.of(year, Month.SEPTEMBER, 1)
                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

            // Check if the first Monday of September is between the start and end dates
            if ((firstMondayOfSeptember.isEqual(startDate) || firstMondayOfSeptember.isAfter(startDate)) &&
                (firstMondayOfSeptember.isEqual(endDate) || firstMondayOfSeptember.isBefore(endDate))) {
                return 1;
            }
        }

        return 0;
    }
}
