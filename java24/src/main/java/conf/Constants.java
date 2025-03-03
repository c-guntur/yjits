package conf;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {

	/**
	 * The below text is not easy to copy/paste or modify, given the
	 * string concatenation and indentation. The fix is to replace
	 * this concatenation with a modern Java text block syntax.
	 * It uses a set of three single quotes and indentation begins
	 * from the first line after the triple-quote.
	 */
	//FIXED 4: Java Text blocks: a simple example
	public static final String ATTENDEE_EMAIL =
			"""
					Welcome to this year's conference! We're excited you are attending!!!
					Here is a list of things to know:
						Schedule is located at: https://marsdev.io/schedule
						Speakers can be viewed at: https://marsdev.io/speakers
						Other activities and raffle details are available at: https://mardev.io/foryou
						Code of conduct: https://marsdev.io/code-of-conduct
					We really look forward to seeing you "here" and hope you get to both learn as well as enjoy our conference!
					""";

	/**
	 * The below text is not easy to copy/paste or modify, given the
	 * string concatenation and "nested" indentation. The fix is to replace
	 * this concatenation with a modern Java text block syntax.
	 * It uses a set of three single quotes and indentation begins
	 * from the first line after the triple-quote.
	 */
	//FIXED 4: Java Text blocks: an indentation-based text block
	public static final String SPEAKER_EMAIL =
			"""
					Welcome to this year's conference! We're grateful you are speaking!!!
					Here is a list of things to know:
						Schedule is located at: https://marsdev.io/schedule
						Speakers can be viewed at: https://marsdev.io/speakers
						Speaker swag and speaker dinner (requires login): https://mardev.io/restricted/speaker-info
						Code of conduct: https://marsdev.io/code-of-conduct
					We really look forward to seeing you "here" and hope you enjoy our conference!
				""";

    public static final Set<String> SHIRT_SIZES;
    public static final Set<String> HAT_SIZES;

    static {
        // Create shirt sizes as a Set.
        SHIRT_SIZES = Stream.of("XS", "S", "M", "L", "XL", "2XL", "3XL", "4XL")
                .collect(Collectors.toCollection(HashSet::new));

        // Create hat sizes as a Set.
        HAT_SIZES = Stream.of("S/M", "L", "XL")
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Pull a random shirt size from the set.
     *
     * @return - String - a random shirt size
     */
    public static String randomShirtSize() {
        int i = ThreadLocalRandom.current().nextInt(0, SHIRT_SIZES.size());
        int counter = 0;
        for (String size : SHIRT_SIZES) {
            if (i == counter) {
                return size;
            }
            counter++;
        }
        return "";
    }

    /**
     * Pull a random hat size from the set.
     *
     * @return - String - a random hat size
     */
    public static String randomHatSize() {
        int i = ThreadLocalRandom.current().nextInt(0, HAT_SIZES.size());
        int counter = 0;
        for (String size : HAT_SIZES) {
            if (i == counter) {
                return size;
            }
            counter++;
        }
        return "";
    }
}
