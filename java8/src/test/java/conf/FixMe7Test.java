package conf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class FixMe7Test {

	@Test
    @DisplayName("Discount > 0.0d and < 0.1d")
    @Order(1)
	void getDiscountAdjectiveNice() {
		double discount = 0.05d;
		assertEquals("a nice",
				App.getDiscountAdjective(discount),
				"Discount adjective is not computed correctly.");
	}

	@Test
    @DisplayName("Discount > 0.1d and < 0.2d")
    @Order(2)
	void getDiscountAdjectiveGreat() {
		double discount = 0.15d;
		assertEquals("a great",
				App.getDiscountAdjective(discount),
				"Discount adjective is not computed correctly.");
	}

	@Test
    @DisplayName("Discount = 0.0d")
    @Order(3)
	void getDiscountAdjectiveInteresting0() {
		double discount = 0.00d;
		assertEquals("an interesting",
				App.getDiscountAdjective(discount),
				"Discount adjective is not computed correctly.");
	}

	@Test
    @DisplayName("Discount < 0.0d")
    @Order(4)
	void getDiscountAdjectiveInterestingNegative() {
		double discount = -0.1d;
		assertEquals("an interesting",
				App.getDiscountAdjective(discount),
				"Discount adjective is not computed correctly.");
	}

	@Test
    @DisplayName("Discount > 0.2d")
    @Order(5)
	void getDiscountAdjectiveInteresting5() {
		double discount = 0.5d;
		assertEquals("an interesting",
				App.getDiscountAdjective(discount),
				"Discount adjective is not computed correctly.");
	}
}