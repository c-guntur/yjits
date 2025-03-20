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

void main() {
	Attendee attendee = new Alumni(0.1d);
	println(attendee.getDiscount());
}
