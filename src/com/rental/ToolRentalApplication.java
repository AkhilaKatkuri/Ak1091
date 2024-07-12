package com.rental;

import com.rental.models.rentalagreement;
import com.rental.services.rentalservice;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class ToolRentalApplication {
    public static void main(String[] args) {
        // Create an instance of the RentalService
        rentalservice rentalService = new rentalservice();

        try {
            LocalDate checkoutDate = LocalDate.of(2024, 7, 3);
            
            // Perform the checkout operation
            rentalagreement agreement = rentalService.checkout("CHNS", 6, 10, checkoutDate);

            // Print the rental agreement details
            agreement.print();
            
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}