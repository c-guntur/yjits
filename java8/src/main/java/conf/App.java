package conf;

import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static conf.Constants.ATTENDEE_EMAIL;
import static conf.Constants.SPEAKER_EMAIL;

public class App {

    /**
     * NOTE: Future versions of Java will auto-box primitives.
     *       Forced boxing will be removed.
     */
    //FIXME: 0. Replace redundant boxing
    public static final Integer ZERO_INTEGER = new Integer(0);

    //FIXME: 12. Java 21 benefit
    /**
     * Orchestrates the conference creation, logistics and awards.
     * <p>
     * NOTE: This method covers the usage of 'var' in Java
     *
     * @param args Command line arguments (not used in this instance)
     */
    public static void main(String[] args) {
        //FIXME: 5. Uncomment/Comment below code.
        //showNullPointerException();

        Conference theConference = new Seeder().seed();

        System.out.println(theConference);

        System.out.println("\nAttendee email: ");
        System.out.println(ATTENDEE_EMAIL);
        System.out.println();
        System.out.println("Indented Speaker email: ");
        System.out.println(SPEAKER_EMAIL);

        //FIXME: 1. Use var instead
        Map<String, Integer> shirtCountMap = determineShirtCount(theConference);
        displayShirtCounts(shirtCountMap);

        //FIXME: 1. Use var instead
        Map<String, Integer> hatCountMap = null;
        hatCountMap = determineHatCount(theConference);
        displayHatCounts(hatCountMap);

        displayPaymentInvoicing(theConference);

        displayBadgeCount(theConference);

        displayRaffleWinners(theConference);

        displayRandomWinningSession(theConference);
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
        //FIXME: 11. Convert to String Template FMT
        //NOTE: FMT is a StringTemplate processor that allows String formatting.
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

    /**
     * Create a map of shirt sizes to counts per shirt size to be ordered
     *
     * @param theConference - the current conference
     * @return - a Map of shirt size to counts per size
     */
    static Map<String, Integer> determineShirtCount(Conference theConference) {
        Map<String, Integer> shirtCountMap = new HashMap<>();
        for (Speaker speaker : theConference.getSpeakers()) {
            String shirtSize = speaker.getShirtSize();
            shirtCountMap.putIfAbsent(shirtSize, ZERO_INTEGER);
            int currentCount = shirtCountMap.get(shirtSize);
            shirtCountMap.put(shirtSize, currentCount + 1);
        }
        return shirtCountMap;
    }

    /**
     * Print the shirt count per size
     *
     * @param shirtCountMap - A Map of shirt size to counts per size
     */
    static void displayShirtCounts(Map<String, Integer> shirtCountMap) {
        System.out.println("\nTotal number of shirts to order: " +
                shirtCountMap.values().stream().reduce(0, Integer::sum));

        for (String shirtSize : shirtCountMap.keySet()) {

            int count = shirtCountMap.get(shirtSize);

            //FIXME: 9. Convert to String Template STR
            //NOTE: STR is the most basic StringTemplate processor.
            String shirtSizes = "Shirt size: [" + shirtSize + "] " +
                    "-> Count: [" + count + "]";

            System.out.println(shirtSizes);
        }
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
            int currentCount = hatCountMap.get(hatSize);
            hatCountMap.put(hatSize, currentCount + 1);
        }
        return hatCountMap;
    }

    /**
     * Print the hat count per size
     *
     * @param hatCountMap - A Map of hat size to counts per size
     */
    static void displayHatCounts(Map<String, Integer> hatCountMap) {
        System.out.println("\nTotal number of hats to order: " +
                hatCountMap.values().stream().reduce(0, Integer::sum));

        for (String hatSize : hatCountMap.keySet()) {
            //FIXME: 10. Convert to String Template RAW
            //NOTE: RAW is a StringTemplate processor that can defer 'process'ing.
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
        double processingFee = 0.0D;
        for (Attendee attendee : theConference.getAttendees()) {
            //FIXME: 2. Replace with switch expression
            switch (attendee.getPaymentType()) {
                case AMEX:
                    processingFee += 0.10D;
                    break;
                case VISA:
                case MASTERCARD:
                    processingFee += 0.08D;
                    break;
                case PAYPAL:
                    processingFee += 0.11D;
                    break;
            }
        }
        System.out.println("\nTotal payment-processing fees: USD[" + String.format("%,.2f", processingFee) + "]\n");
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
        List<String> allowedWinnerPool = new ArrayList<>();

        allowedWinnerPool.addAll(
                theConference.getAttendees().stream().map(Attendee::getUniqueId).collect(Collectors.toList()));
        allowedWinnerPool.addAll(
                theConference.getSpeakers().stream().map(Speaker::getUniqueId).collect(Collectors.toList()));
        allowedWinnerPool.addAll(
                theConference.getVendorSponsors().stream().map(VendorSponsor::getUniqueId).collect(Collectors.toList()));
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

        boolean allowedPersonFound = false;
        //FIXME: 4. Replace the below with a switch pattern-matched instanceof
        for (AllowedPerson allowedPerson : allowedPersonWinner) {
            if (allowedPerson instanceof Attendee) {
                Attendee attendee = (Attendee) allowedPerson;
                System.out.println("Winner is an attendee: " +
                        attendee.getFirstName() + " " + attendee.getLastName() +
                        ", payment: " + attendee.getPaymentType());
                allowedPersonFound = true;
            }
            if (allowedPerson instanceof Speaker) {
                Speaker speaker = (Speaker) allowedPerson;
                System.out.println("Winner is a speaker: " +
                        speaker.getFirstName() + " " + speaker.getLastName() +
                        ", shirt size: " + speaker.getShirtSize());
                allowedPersonFound = true;
            }
            if (allowedPerson instanceof VendorSponsor) {
                VendorSponsor vendorSponsor = (VendorSponsor) allowedPerson;
                        System.out.println("Winner is a vendor/sponsor: " +
                                vendorSponsor.getFirstName() + " " + vendorSponsor.getLastName() +
                                ", booth: " + vendorSponsor.getBoothName());
                allowedPersonFound = true;
            }
            if (!allowedPersonFound) {
                throw new IllegalStateException("Person not allowed: " + allowedPerson);
            }
        }
    }

    /**
     * Determine the most voted session in the conference.
     * Uses a random number to pick one from a list of sessions.
     * <p>
     * NOTE: This method show-cases the getter method signature
     *   change for Record instances.
     * @param theConference - the current conference
     */
    static void displayRandomWinningSession(Conference theConference) {
        //FIXME: 6. Replace to a Record getter and
        //          upgrade to a toList() instead of Collectors.toList()
        List<String> sessions = theConference.getSessions().stream().
                map(Session::getSessionTitle).collect(Collectors.toList());

        String mostVotedSessionTitle = sessions.get(
                ThreadLocalRandom.current().nextInt(0, sessions.size()));

        //FIXME: 6. Replace to a Record getter
        Optional<Session> sessionObject = theConference.getSessions().stream()
                .filter(session -> session.getSessionTitle().equals(mostVotedSessionTitle))
                .findFirst();

        sessionObject.ifPresent(App::displaySessionDetails);
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
    static void displaySessionDetails(Object object) {

        //FIXME: 8. Use a record pattern
        if (object instanceof Session) {
            Session session = (Session) object;

            String title = session.getSessionTitle();
            Speaker speaker = session.getMainSpeakerModerator();

            String message = "\nThe most voted session: [" +
                    title +
                    "] by [" +
                    speaker.firstName +
                    " " +
                    speaker.lastName + "]";

            System.out.println(message);
        }
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
    private static void showNullPointerException() {
        Conference fakeConference = new Conference("fake", "fake", Year.now(), "fake");
        Session session = new Session(
                "fake", "fake", new Speaker(null, "fake", "fake"));
        Set<Session> sessions = new HashSet<>();
        sessions.add(session);
        fakeConference.setSessions(sessions);
        //FIXME: 6. Replace to a Record getter
        Object aSpeakerFirstNameLength =
                ((Session) fakeConference.getSessions().
                        toArray()[0]).getMainSpeakerModerator().firstName.length();
    }
}
