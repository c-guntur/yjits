package conf;

import java.util.UUID;

/**
 * Java has a problem with extremes when it comes to extension.
 * In the past, either a class was final (so no extension) or
 * "open" which implied infinite extensions, such as InvalidAttendee
 * here. With the more modern "sealed" classes it is possible to
 * create a more controlled and finite hierarchy for extensions.
 * In this example, Attendee, Speaker, Staff and VendorSponsor are
 * all allowed extensions, while InvalidAttendee needs to be
 * commented out.
 * <p>
 * NOTE: This class hierarchy shows the usage of sealed classes
 */
//FIXME: 7. Convert to Sealed type
public class AllowedPerson {
    protected String firstName;
    protected String lastName;

    public AllowedPerson() {
    }

    public AllowedPerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "AllowedPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

class Attendee extends AllowedPerson {
    private PaymentType paymentType;
    private final String uniqueId = UUID.randomUUID().toString();

    public Attendee(String firstName, String lastName, PaymentType paymentType) {
        super(firstName, lastName);
        this.paymentType = paymentType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return "\n\tAttendee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}

class Speaker extends AllowedPerson {
    private String shirtSize;
    private final String uniqueId = UUID.randomUUID().toString();

    public Speaker(String firstName, String lastName, String shirtSize) {
        super(firstName, lastName);
        this.shirtSize = shirtSize;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return "\n\tSpeaker{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", shirtSize='" + shirtSize + '\'' +
                '}';
    }
}

class Staff extends AllowedPerson {
    private String hatSize;

    public Staff(String firstName, String lastName, String hatSize) {
        super(firstName, lastName);
        this.hatSize = hatSize;
    }

    public String getHatSize() {
        return hatSize;
    }

    public void setHatSize(String hatSize) {
        this.hatSize = hatSize;
    }

    @Override
    public String toString() {
        return "\n\tStaff{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hatSize='" + hatSize + '\'' +
                '}';
    }
}

class VendorSponsor extends AllowedPerson {
    private String boothName;
    private final String uniqueId = UUID.randomUUID().toString();

    public VendorSponsor(String firstName, String lastName, String boothName) {
        super(firstName, lastName);
        this.boothName = boothName;
    }

    public String getBoothName() {
        return boothName;
    }

    public void setBoothName(String boothName) {
        this.boothName = boothName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return "\n\tVendorSponsor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", boothName='" + boothName + '\'' +
                '}';
    }
}

//FIXME: 7. Show as not allowed in sealed hierarchy
class InvalidAttendee extends AllowedPerson {
    public InvalidAttendee(String firstName, String lastName) {
        super(firstName, lastName);
    }
}

