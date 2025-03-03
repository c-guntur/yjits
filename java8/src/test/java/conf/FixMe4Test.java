package conf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixMe4Test {

    @Test
    @DisplayName("Unindented text")
    public void attendeeEmail() {
        String output = "Welcome to this year's conference! We're excited you are attending!!!\n" +
                "Here is a list of things to know:\n" +
                "\tSchedule is located at: https://marsdev.io/schedule\n" +
                "\tSpeakers can be viewed at: https://marsdev.io/speakers\n" +
                "\tOther activities and raffle details are available at: https://mardev.io/foryou\n" +
                "\tCode of conduct: https://marsdev.io/code-of-conduct\n" +
                "We really look forward to seeing you \"here\" and hope you get to both learn as well as enjoy our conference!\n";

        assertEquals(output, Constants.ATTENDEE_EMAIL, "The message should match the expected");
    }

    @Test
    @DisplayName("Indented text")
    public void speakerEmail() {
        String output = "\tWelcome to this year's conference! We're grateful you are speaking!!!" +
                "\n\tHere is a list of things to know:" +
                "\n\t\tSchedule is located at: https://marsdev.io/schedule" +
                "\n\t\tSpeakers can be viewed at: https://marsdev.io/speakers" +
                "\n\t\tSpeaker swag and speaker dinner (requires login): https://mardev.io/restricted/speaker-info" +
                "\n\t\tCode of conduct: https://marsdev.io/code-of-conduct" +
                "\n\tWe really look forward to seeing you \"here\" and hope you enjoy our conference!\n";

        assertEquals(output, Constants.SPEAKER_EMAIL, "The message should match the expected");
    }

}