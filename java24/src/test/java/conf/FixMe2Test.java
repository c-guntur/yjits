package conf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//FIXED 2: Fix the JDK to get a helpful NullPointerException message
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