import java.time.Year;
import java.util.Objects;

class Speakers {
	final String speaker1, speaker2;

	Speakers(String speaker1, String speaker2) {
		this.speaker1 = speaker1;
		this.speaker2 = speaker2;
	}

	public String getSpeaker1() {
		return speaker1;
	}

	public String getSpeaker2() {
		return speaker2;
	}

	@Override
	public final boolean equals(Object o) {
		if (!(o instanceof Speakers)) return false;

		Speakers speakers = (Speakers) o;
		return Objects.equals(speaker1, speakers.speaker1) && Objects.equals(speaker2, speakers.speaker2);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(speaker1);
		result = 31 * result + Objects.hashCode(speaker2);
		return result;
	}

	//Speakers[speaker1=Mala, speaker2=Chandra]
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Speakers[");
		sb.append("speaker1=").append(speaker1);
		sb.append(", speaker2=").append(speaker2);
		sb.append(']');
		return sb.toString();
	}
}

public class HelloJavaOne {
	static String year = Year.now().toString();

	public static void main(String[] args) {
		System.out.println("Hello JavaOne " + year);
		Speakers speakers = new Speakers("Mala", "Chandra");
		System.out.println(speakers);
		System.out.println(
				"\n" +
						"\n" +
						"\t\t\\(^_^)/\n" +
						"\t\t / | \\\n" +
						"\t\t  | |    \n" +
						"\t\t_/   \\_\n"
		);

		Attendee attendee = new Alumni(0.1d);
		System.out.println(attendee.getDiscount());
	}
}

abstract class Attendee {
	public Attendee() {
		System.out.println("Setting discount " + getDiscount());
	}
	public abstract double getDiscount();
}

class Alumni extends Attendee {
	private double discount;
	Alumni(double discount) {
		// this did not compile before Java 23 with preview features enabled.
		this.discount = discount;
		super();
	}
	@Override public double getDiscount() {
		return discount * 2;
	}
}

