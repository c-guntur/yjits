package conf;

import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static conf.Constants.ATTENDEE_EMAIL;
import static conf.Constants.SPEAKER_EMAIL;

public class App {

    //NOTE: Future versions of Java will auto-box primitives.
    //FIXED 1: Replace redundant boxing
    public static final Integer ZERO_INTEGER = 0;

    /**
     * Orchestrates the conference creation, logistics and awards.
     * <p>
     * NOTE: This method covers the usage of 'var' in Java
     */
    //FIXED 11: Java 21+ benefit
    void main() {

        Conference theConference = new Seeder().seed();

        displayEmails();

        displayBadgeCount(theConference);
        displayShirtCount(theConference);
        displayHatCount(theConference);

        displayPaymentInvoicing(theConference);

        displayDiscountStatement();

        displayRaffleWinners(theConference);

        displayRandomSpotlightSession(theConference);
    }

    static void displayEmails() {
        System.out.println("\nAttendee email: ");
        System.out.println(ATTENDEE_EMAIL);
        System.out.println();
        System.out.println("Indented Speaker email: ");
        System.out.println(SPEAKER_EMAIL);
    }

    /**
     * Count the number of badges to be ordered by counting all
     * people who will be at the conference.
     *
     * @param theConference - the current conference
     * @return - an Integer with the count of badges
     */
    static Integer determineBadgeCount(Conference theConference) {
        int badgeCount = theConference.getStaff().size();
        badgeCount += theConference.getSpeakers().size();
        badgeCount += theConference.getVendorSponsors().size();
        badgeCount += theConference.getAttendees().size();
        return badgeCount;
    }

    /**
     * Print the badge count as the total number of allowed people
     *
     * @param theConference - the current conference
     */
    static void displayBadgeCount(Conference theConference) {

        StringBuffer message = new StringBuffer("");
        message.append("\n---------------------------------------------------------------");
        message.append("\n| " + StringUtils.rightPad("Participant type", 50) + " | " + StringUtils.leftPad("Count", 6) + " |");
        message.append("\n===============================================================");
        message.append("\n| " + StringUtils.rightPad(Speaker.class.getSimpleName(), 50) + " | " + StringUtils.leftPad("" + theConference.getSpeakers().size(), 6) + " |");
        message.append("\n| " + StringUtils.rightPad(Attendee.class.getSimpleName(), 50) + " | " + StringUtils.leftPad("" + theConference.getAttendees().size(), 6) + " |");
        message.append("\n| " + StringUtils.rightPad(Staff.class.getSimpleName(), 50) + " | " + StringUtils.leftPad("" + theConference.getStaff().size(), 6) + " |");
        message.append("\n| " + StringUtils.rightPad(VendorSponsor.class.getSimpleName(), 50) + " | " + StringUtils.leftPad("" + theConference.getVendorSponsors().size(), 6) + " |");
        message.append("\n===============================================================");
        message.append("\n| " + StringUtils.rightPad("Total allowed people: ", 50) + " | " + StringUtils.leftPad("" + determineBadgeCount(theConference), 6) + " |");
        message.append("\n---------------------------------------------------------------");

        System.out.println(message);

    }

    static void displayShirtCount(Conference theConference) {
        //FIXED 3: Local Variable Type Inference: Use var instead
        var shirtCountMap = determineShirtCount(theConference.getSpeakers());
        displayShirtCount(shirtCountMap);
    }

    /**
     * Create a map of shirt sizes to counts per shirt size to be ordered
     *
     * @param speakers - a Set of speakers at the current conference
     * @return - a Map of shirt size to counts per size
     */
    static Map<String, Integer> determineShirtCount(Set<Speaker> speakers) {
        Map<String, Integer> shirtCountMap = new HashMap<>();
        for (Speaker speaker : speakers) {
            String shirtSize = speaker.getShirtSize();
            shirtCountMap.putIfAbsent(shirtSize, ZERO_INTEGER);
            shirtCountMap.compute(shirtSize, (k, currentCount) -> currentCount + 1);
        }
        return shirtCountMap;
    }

    /**
     * Print the shirt count per size
     *
     * @param shirtCountMap - A Map of shirt size to counts per size
     */
    static void displayShirtCount(Map<String, Integer> shirtCountMap) {
        System.out.println("\nTotal number of shirts to order: " +
                shirtCountMap.values().stream().reduce(0, Integer::sum));

        for (String shirtSize : shirtCountMap.keySet()) {
            int count = shirtCountMap.get(shirtSize);
            String shirtSizes = "Shirt size: [" + shirtSize + "] " +
                    "-> Count: [" + count + "]";
            System.out.println(shirtSizes);
        }
    }

    static void displayHatCount(Conference theConference) {
        //FIXED 3: Local Variable Type Inference: Use var instead?
        var hatCountMap = determineHatCount(theConference);
        displayHatCount(hatCountMap);
    }

    /**
     * Create a map of hat sizes to counts per hat size to be ordered
     *
     * @param theConference - the current conference
     * @return - a Map of hat size to counts per size
     */
    static Map<String, Integer> determineHatCount(Conference theConference) {
        Map<String, Integer> hatCountMap = new HashMap<>();
        for (Staff staff : theConference.getStaff()) {
            String hatSize = staff.getHatSize();
            hatCountMap.putIfAbsent(hatSize, ZERO_INTEGER);
            hatCountMap.compute(hatSize, (k, currentCount) -> currentCount + 1);
        }
        return hatCountMap;
    }

    /**
     * Print the hat count per size
     *
     * @param hatCountMap - A Map of hat size to counts per size
     */
    static void displayHatCount(Map<String, Integer> hatCountMap) {
        System.out.println("\nTotal number of hats to order: " +
                hatCountMap.values().stream().reduce(0, Integer::sum));

        for (String hatSize : hatCountMap.keySet()) {
            System.out.println("Hat size: [" + hatSize + "] " +
                    "-> Count: [" + hatCountMap.get(hatSize) + "]");
        }
    }

    /**
     * Calculate total processing fee based on individual payment types
     * Each payment type has different processing fee. The sum total of all
     * processing fee is calculated and displayed.
     *
     * @param theConference - the current conference
     */
    static void displayPaymentInvoicing(Conference theConference) {
        Double processingFee = getProcessingFee(theConference);
        System.out.println("\nTotal payment-processing fees: USD[" + String.format("%,.2f", processingFee) + "]\n");
    }

    static double getProcessingFee(Conference theConference) {
        double processingFee = 0.0D;

        for (Attendee attendee : theConference.getAttendees()) {
            // FIXED 5: Replace with switch expression
            processingFee += switch (attendee.getPaymentType()) {
                case AMEX -> 0.10D;
                case VISA, MASTERCARD -> 0.08D;
                case PAYPAL -> 0.11D;
            };
        }
        return processingFee;
    }

    private static void displayDiscountStatement() {
        double discount = ThreadLocalRandom.current().nextDouble();
        System.out.println("You got yourself " +
                getDiscountAdjective(discount) +
                " discount");
    }

    /**
     * Calculate the discount that every attendee will receive.
     *
     * @param discount - A primitive value representing the discount percentage
     * @return a discount adjective to be printed as part of the output
     */
    static String getDiscountAdjective(double discount) {
        String discountAdjective;
        //FIXED 7: Switch - Convert to primitive instance-of switch case with guards
        switch (discount) {
            case double d when (d > 0.0d && d <= 0.1d) ->
                    discountAdjective = "a nice";
            case double d when (d > 0.1d && d <= 0.2d) ->
                    discountAdjective = "a great";
            default ->
                    discountAdjective = "an interesting";
        }
        return discountAdjective;
    }

    /**
     * Create a pool of Attendees, Speakers and Vendor Sponsors.
     * Randomly pick three winners from the pool.
     * If the winner is an Attendee: Print firstname, lastName and paymentType
     * If the winner is a Speaker: Print firstname, lastName and shirtSize
     * If the winner is a VendorSponsor: Print firstname, lastName and boothName
     * <p>
     * NOTE: This method show-cases a switch-case pattern matching.
     *
     * @param theConference - the current conference
     */
    static void displayRaffleWinners(Conference theConference) {
        AllowedPerson[] allowedPersonWinner = selectRaffleWinners(theConference);

        for (AllowedPerson allowedPerson : allowedPersonWinner) {
            System.out.println(displayRaffleWinnerDetails(allowedPerson));
        }
    }

    private static AllowedPerson[] selectRaffleWinners(Conference theConference) {
        List<String> allowedWinnerPool = new ArrayList<>();

        allowedWinnerPool.addAll(
                theConference.getAttendees().stream().map(Attendee::getUniqueId).toList());
        allowedWinnerPool.addAll(
                theConference.getSpeakers().stream().map(Speaker::getUniqueId).toList());
        allowedWinnerPool.addAll(
                theConference.getVendorSponsors().stream().map(VendorSponsor::getUniqueId).toList());
        int[] winners = {-1, -1, -1};
        for (int i = 0; i < winners.length; i++) {
            winners[i] = ThreadLocalRandom.current().nextInt(0, allowedWinnerPool.size());
        }
        while (winners[1] == winners[0]) {
            winners[1] = ThreadLocalRandom.current().nextInt(0, allowedWinnerPool.size());
        }
        while (winners[2] == winners[1] || winners[2] == winners[0]) {
            winners[2] = ThreadLocalRandom.current().nextInt(0, allowedWinnerPool.size());
        }

        AllowedPerson[] allowedPersonWinner = new AllowedPerson[3];

        for (int i = 0; i < winners.length; i++) {
            for (Attendee attendee : theConference.getAttendees()) {
                if (attendee.getUniqueId().equals(allowedWinnerPool.get(winners[i]))) {
                    allowedPersonWinner[i] = attendee;
                    break;
                }
            }
            for (Speaker speaker : theConference.getSpeakers()) {
                if (speaker.getUniqueId().equals(allowedWinnerPool.get(winners[i]))) {
                    allowedPersonWinner[i] = speaker;
                    break;
                }
            }
            for (VendorSponsor vendorSponsor : theConference.getVendorSponsors()) {
                if (vendorSponsor.getUniqueId().equals(allowedWinnerPool.get(winners[i]))) {
                    allowedPersonWinner[i] = vendorSponsor;
                    break;
                }
            }
        }
        return allowedPersonWinner;
    }

    static String displayRaffleWinnerDetails(AllowedPerson allowedPerson) {
        String message = "Winner is ";

        //FIXED 6: Replace the below with a switch pattern-matched instanceof
        switch (allowedPerson) {
            case Attendee attendee -> message += "an attendee: " +
                    attendee.getFirstName() + " " + attendee.getLastName() +
                    ", payment: " + attendee.getPaymentType();
            case Speaker speaker -> message += "a speaker: " +
                    speaker.getFirstName() + " " + speaker.getLastName() +
                    ", shirt size: " + speaker.getShirtSize();
            case VendorSponsor vendorSponsor ->
                    message += "a vendor/sponsor: " +
                            vendorSponsor.getFirstName() + " " + vendorSponsor.getLastName() +
                            ", booth: " + vendorSponsor.getBoothName();
            default -> throw new IllegalStateException("Person not allowed: " + allowedPerson);
        }
        return message;
    }

    /**
     * Determine the most voted session in the conference.
     * Uses a random number to pick one from a list of sessions.
     * <p>
     *  NOTE: This method show-cases the getter method signature
     *   change for Record instances.
     *
     * @param theConference - the current conference
     */
    static void displayRandomSpotlightSession(Conference theConference) {
        //FIXED 9: Replace to a Record getter and
        //          upgrade to a toList() instead of Collectors.toList()
        List<String> sessions = theConference.getSessions().stream().
                map(Session::sessionTitle).toList();

        String spotlightSessionTitle = sessions.get(
                ThreadLocalRandom.current().nextInt(0, sessions.size()));

        //FIXED 9: Replace to a Record getter
        Optional<Session> spotlightSession = theConference.getSessions().stream()
                .filter(session -> session.sessionTitle().equals(spotlightSessionTitle))
                .findFirst();

        spotlightSession.ifPresent(session -> System.out.println(getSessionDetails(session)));
    }

    /**
     * Print the details of a session, passed in as an Object:
     * the session title, speaker first and last name.
     * <p>
     * NOTE: This method highlights the usage of a
     *       record deconstruction pattern
     *
     * @param object An object that is intended to be a
     *               Session instance
     */
    static String getSessionDetails(Object object) {
        String message = "Sorry, not  valid session!";

        //FIXED 10: Use a record pattern
        if (object instanceof Session(String title, String _, Speaker speaker)) {
            message = String.format("\nThe most voted session: [%s] by [%s %s]",
                    title, speaker.getFirstName(), speaker.getLastName()) ;

        }
        return message;
    }

    /**
     * Executed when the first few commented lines of
     * the main method are uncommented. This code is
     * only used to display the change in exception
     * stack trace between Java 8 and post-Java 14.
     * <p>
     * NOTE: This method ONLY exists to highlight
     *       "Helpful NullPointerException"
     */
    static void showNullPointerException() {
        Conference fakeConference = new Conference(
                "fake", "fake", Year.now(), "fake");
        Session session = new Session(
                "fake",
                "fake",
                new Speaker(null, "fake", "fake"));
        Set<Session> sessions = Set.of(session);
        fakeConference.setSessions(sessions);
        //FIXED 9: Replace to a Record getter
        Object aSpeakerFirstNameLength =
                ((Session) fakeConference.getSessions().
                        toArray()[0]).mainSpeakerModerator().firstName.length();
    }
}
