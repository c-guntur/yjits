package conf;

import com.github.javafaker.Faker;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class seeds a conference and its nested structure.
 */
public class Seeder {
    private Faker faker = new Faker();

    public static void main(String[] args) {
        Seeder seeder = new Seeder();
        System.out.println(seeder.seed());
    }

    public Conference seed() {
        Conference conference = seedConference();
        seedStaff(conference);
        seedSessions(conference);
        seedVendorSponsors(conference);
        seedAttendees(conference);
        return conference;
    }

    private Conference seedConference() {
        String title = "Mars Developer Summit";
        String nickname = "MDS";
        String venue = "Elysium Planitia";

        return new Conference(title, nickname, Year.now(), venue);
    }

    private void seedStaff(Conference conference) {
        Set<Staff> staffSet = new HashSet<>();
        for (int i = 0; i < 25; i++) {
            Staff staff = new Staff(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    Constants.randomHatSize());
            staffSet.add(staff);
        }
        conference.setStaff(staffSet);
    }

    private void seedSessions(Conference conference) {
        Set<Session> sessions = new HashSet<>();
        Set<Speaker> speakers = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            Speaker speaker = new Speaker(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    Constants.randomShirtSize());
            speakers.add(speaker);

            Session session = new Session(
                    faker.hitchhikersGuideToTheGalaxy().character(),
                    faker.lorem().sentence(150, 50),
                    speaker);
            sessions.add(session);
        }
        conference.setSpeakers(speakers);
        conference.setSessions(sessions);
    }

    private void seedVendorSponsors(Conference conference) {
        Set<VendorSponsor> vendorSponsors = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            String booth = faker.color().name() + '_' + i;
            for (int allowedPerson = 0; allowedPerson < 3; allowedPerson++) {
                VendorSponsor vendorSponsor = new VendorSponsor(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        booth);
                vendorSponsors.add(vendorSponsor);
            }
        }
        conference.setVendorSponsors(vendorSponsors);
    }

    private void seedAttendees(Conference conference) {
        Set<Attendee> attendees = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            PaymentType[] paymentTypes = PaymentType.values();
            int randomNum = ThreadLocalRandom.current().nextInt(0, paymentTypes.length);

            Attendee attendee = new Attendee(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    paymentTypes[randomNum]);
            attendees.add(attendee);
        }
        conference.setAttendees(attendees);
    }
}
