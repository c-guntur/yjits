package conf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FixMe10Test {

	@Test
	@DisplayName("Valid session instance")
	void getSessionDetailsSession() {
		Speaker speakerModerator = new Speaker("FNS", "LNS", "M");
		Session session = new Session(
				"Title",
				"abstract",
				speakerModerator
		);

		String message = App.getSessionDetails(session);

		assertAll(
				"Grouped Assertions of getSessionDetails",
				() -> assertTrue(message.contains("FNS"), "First name should be in the message"),
				() -> assertTrue(message.contains("LNS"), "Last name should be in the message"),
				() -> assertTrue(message.contains("Title"), "Title should be included in the message")
		);
	}

	@Test
	@DisplayName("Non-session object")
	void getSessionDetailsLocalDate() {
		LocalDate aDate = LocalDate.now();
		String message = App.getSessionDetails(aDate);
		assertEquals(
				"Sorry, not  valid session!",
				message,
				"Message should display as expected"
		);
	}
}