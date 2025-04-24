abstract class Attendee {
	public Attendee() {
		System.out.println("Setting discount " + getDiscount());
	}
	public abstract double getDiscount();
}

class Alumni extends Attendee {
	private double discount;
	Alumni(double discount) {
		super();
		// Would like to set discount ahead of invoking the super().
		this.discount = discount;
	}
	@Override public double getDiscount() {
		return discount * 2;
	}
}

void main() {
	Attendee attendee = new Alumni(0.1d);
	println("Done setting the discount " + attendee.getDiscount());
}
