package conf;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Conference {
    private final String name;
    private final String nickName;
    private final Year year;
    private final String venue;
    private Set<Staff> staff;
    private Set<Speaker> speakers;
    private Set<Attendee> attendees;
    private Set<VendorSponsor> vendorSponsors;
    private Set<Session> sessions;
    private AllowedPerson winner1;
    private AllowedPerson winner2;
    private AllowedPerson winner3;

    public Conference(String name, String nickName, Year year, String venue) {
        this.name = name;
        this.nickName = nickName;
        this.year = year;
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public Year getYear() {
        return year;
    }

    public String getVenue() {
        return venue;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Set<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Set<VendorSponsor> getVendorSponsors() {
        return vendorSponsors;
    }

    public void setVendorSponsors(Set<VendorSponsor> vendorSponsors) {
        this.vendorSponsors = vendorSponsors;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public AllowedPerson getWinner1() {
        return winner1;
    }

    public void setWinner1(AllowedPerson winner1) {
        this.winner1 = winner1;
    }

    public AllowedPerson getWinner2() {
        return winner2;
    }

    public void setWinner2(AllowedPerson winner2) {
        this.winner2 = winner2;
    }

    public AllowedPerson getWinner3() {
        return winner3;
    }

    public void setWinner3(AllowedPerson winner3) {
        this.winner3 = winner3;
    }

    public List<? super AllowedPerson> getTotalAllowedPeople() {
        List<AllowedPerson> allowedPeople = new ArrayList<>();
        allowedPeople.addAll(staff);
        allowedPeople.addAll(vendorSponsors);
        allowedPeople.addAll(speakers);
        allowedPeople.addAll(attendees);
        return allowedPeople;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", year=" + year +
                ", venue='" + venue + '\'' +
                ", \nstaff=" + staff +
                ", \nspeakers=" + speakers +
                ", \nattendees=" + attendees +
                ", \nvendorSponsors=" + vendorSponsors +
                ", \nsessions=" + sessions +
                '}';
    }
}
