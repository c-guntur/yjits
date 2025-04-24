abstract class Attendee {
	public Attendee() {
		println("Setting discount " + getDiscount());
	}
	public abstract double getDiscount();
}

class Alumni extends Attendee {
	private double discount;
	Alumni(double discount) {
		// this did not compile before Java 23 with preview features enabled.
		super();
		this.discount = discount;
	}
	@Override public double getDiscount() {
		return discount * 2;
	}
}

void main() {
	Attendee attendee = new Alumni(0.1d);
	println("Done setting the discount: " + attendee.getDiscount());
}
