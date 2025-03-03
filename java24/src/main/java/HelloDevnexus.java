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

	//Speakers[speaker1=Rodrigo, speaker2=Chandra]
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Speakers[");
		sb.append("speaker1=").append(speaker1);
		sb.append(", speaker2=").append(speaker2);
		sb.append(']');
		return sb.toString();
	}
}

public class HelloDevnexus {
	static String year = Year.now().toString();

	public static void main(String[] args) {
		System.out.println("Hello Devnexus " + year);
		Speakers speakers = new Speakers("Rodrigo","Chandra");
		System.out.println(speakers);
	}
}


















// Instance main()
// 	Class variables don't need to be static to be accessed in main()
// Unnamed class

// import module - default import module java.base

// Use var instead of specifying Type twice

// Use IO.println

// Terminal => java --enable-preview HelloDevnexus.java











//record Speakers(String speaker1, String speaker2) { }
//
//String year = Year.now().toString();
//
//void main() {
//	IO.println("Hello Devnexus " + year);
//	var speakers = new Speakers("Rodrigo","Chandra");
//	IO.println(speakers);
//}

