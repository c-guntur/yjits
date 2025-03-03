package conf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Fixme5Test {

	Conference theConference;

	@BeforeEach
	void setUp() {
		theConference = new Conference(
				"test", "test", Year.now(), "testVenue");
		Set<Attendee> attendees = new HashSet<>();

		Attendee attendeeOne = new Attendee(
				"firstOne",
				"lastOne",
				PaymentType.AMEX);
		attendees.add(attendeeOne);
		Attendee attendeeTwo = new Attendee(
				"firstTwo",
				"lastTwo",
				PaymentType.AMEX);
		attendees.add(attendeeTwo);
		Attendee attendeeThree = new Attendee(
				"firstThree",
				"lastThree",
				PaymentType.MASTERCARD);
		attendees.add(attendeeThree);
		Attendee attendeeFour = new Attendee(
				"firstFour",
				"lastFour",
				PaymentType.PAYPAL);
		attendees.add(attendeeFour);
		Attendee attendeeFive = new Attendee(
				"firstFive",
				"lastFive",
				PaymentType.VISA);
		attendees.add(attendeeFive);
		theConference.setAttendees(attendees);
	}

	@Test
	@DisplayName("5 Attendee Fee")
	void getProcessingFee() {
		//AMEX = 0.10
		//AMEX = 0.10
		//MASTERCARD = 0.08
		//VISA = 0.08
		//PAYPAL = 0.11
		//===================
		//Total = 0.47
		assertEquals(0.47D,
				App.getProcessingFee(theConference),
				0.0001D,
				"The processing fee should be 0.47");
	}
}