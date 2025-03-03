package conf;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class FixMe6Test {

	static Attendee attendee;
	static Speaker speaker;
	static VendorSponsor vendorSponsor;
	static Staff staff;
	static AllowedPerson allowedPerson;

	@BeforeAll
	static void setUp() {
		attendee = new Attendee("FNA", "LNA", PaymentType.AMEX);
		speaker = new Speaker("FNS", "LNS", "L");
		vendorSponsor = new VendorSponsor("FNV", "LNV", "BN");
		staff = new Staff("FNF", "LNF", "M");
		allowedPerson = new AllowedPerson("FNP", "LNP");
	}

	@Test
	@DisplayName("Attendee - Valid Winner")
	@Order(1)
	void displayRaffleWinnerDetailsAttendee() {
		String message = App.displayRaffleWinnerDetails(attendee);
		assertAll(
				"Grouped Assertions of Attendee",
				() -> assertTrue(message.contains("FNA"), "First name should be in the message"),
				() -> assertTrue(message.contains("LNA"), "Last name should be in the message"),
				() -> assertTrue(message.contains("attendee"), "Allowed person type should match")
		);
	}

	@Test
	@DisplayName("Speaker - Valid Winner")
	@Order(2)
	void displayRaffleWinnerDetailsSpeaker() {
		String message = App.displayRaffleWinnerDetails(speaker);
		assertAll(
				"Grouped Assertions of Speaker",
				() -> assertTrue(message.contains("FNS"), "First name should be in the message"),
				() -> assertTrue(message.contains("LNS"), "Last name should be in the message"),
				() -> assertTrue(message.contains("speaker"), "Allowed person type should match")
		);
	}

	@Test
	@DisplayName("VendorSponsor - Valid Winner")
	@Order(3)
	void displayRaffleWinnerDetailsVendorSponsor() {
		String message = App.displayRaffleWinnerDetails(vendorSponsor);
		assertAll(
				"Grouped Assertions of VendorSponsor",
				() -> assertTrue(message.contains("FNV"), "First name should be in the message"),
				() -> assertTrue(message.contains("LNV"), "Last name should be in the message"),
				() -> assertTrue(message.contains("vendor/sponsor"), "Allowed person type should match")
		);
	}

	@Test
	@DisplayName("Staff - Invalid Winner")
	@Order(4)
	void displayRaffleWinnerDetailsStaff() {
		assertThrows(IllegalStateException.class, () -> App.displayRaffleWinnerDetails(staff));
	}

	@Test
	@DisplayName("Random Person - Invalid Winner")
	@Order(5)
	void displayRaffleWinnerDetailsVendorAllowedPerson() {
		assertThrows(IllegalStateException.class, () -> App.displayRaffleWinnerDetails(allowedPerson));
	}

}