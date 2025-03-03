package conf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixMe2Test {

    @Test
    @DisplayName("Helpful exception message!")
    void showNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> App.showNullPointerException());

        try {
            App.showNullPointerException();
        } catch (NullPointerException e) {
            assertTrue(e.getMessage().contains("firstName"));
        }
    }
}