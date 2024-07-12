package com.rental;

import com.rental.models.rentalagreement;
import com.rental.services.rentalservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ToolRentalApplicationTests {
    private rentalservice rentalService;
    private SimpleDateFormat dateFormat;

    @BeforeEach
    public void setup() {
        rentalService = new RentalService();
        dateFormat = new SimpleDateFormat("MM/dd/yy");
    }

    @Test
    public void testCheckout_InvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> {
            Date checkoutDate = dateFormat.parse("09/03/15");
            rentalService.checkout("JAKR", 5, 101, checkoutDate);
        });
    }

    private void assertThrows(Class<IllegalArgumentException> class1, Object object) {
		// TODO Auto-generated method stub
		
	}

	private void assertThrows(Class<IllegalArgumentException> class1, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void testCheckout_ValidScenario() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/20");
        RentalAgreement agreement = rentalService.checkout("LADW", 3, 10, checkoutDate);
        assertNotNull(agreement);
        assertEquals(1.99, agreement.getTool().getDailyCharge());
    }

    @Test
    public void testCheckout_InvalidRentalDays() {
        assertThrows(IllegalArgumentException.class, () -> {
            Date checkoutDate = dateFormat.parse("09/03/15");
            rentalService.checkout("JAKR", 0, 10, checkoutDate);
        });
    }

    @Test
    public void testCheckout_NoDiscount() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/15");
        RentalAgreement agreement = rentalService.checkout("JAKR", 9, 0, checkoutDate);
        assertNotNull(agreement);
        assertEquals(2.99, agreement.getTool().getDailyCharge());
    }

    @Test
    public void testCheckout_HighDiscount() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/20");
        RentalAgreement agreement = rentalService.checkout("JAKR", 4, 50, checkoutDate);
        assertNotNull(agreement);
        assertEquals(2.99, agreement.getTool().getDailyCharge());
    }

    @Test
    public void testCheckout_LargeRentalPeriod() throws ParseException {
        Date checkoutDate = dateFormat.parse("07/02/15");
        RentalAgreement agreement = rentalService.checkout("CHNS", 56, 25, checkoutDate);
        assertNotNull(agreement);
        assertEquals(1.49, agreement.getTool().getDailyCharge());
    }
}