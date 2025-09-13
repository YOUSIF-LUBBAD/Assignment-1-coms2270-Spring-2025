package hw1;

/**
 * Some simple tests that may help you to get started implementing the Balloon
 * class. Warning: these tests do not cover all of the specifications. Perform
 * your own testing in addition to using the specchecker.
 */
public class SimpleTests {
	public static void main(String args[]) {
		Balloon b = new Balloon(37, 40, 200, 20);
		System.out.println("Test 1:");
		System.out.println("Fuel is " + b.getFuel() + " expected 0.");
		System.out.println("Trying to add 10 to fuel.");
		double actuallyAdded = b.addFuel(10);
		System.out.println("Fuel actually added was " + actuallyAdded);
		System.out.println("Fuel is " + b.getFuel() + " expected 10.0.");

		System.out.println("\nTest 2:");
		System.out.println("Tank capacity is " + b.getTankCapacity() + " expected 20.");
		System.out.println("Trying to add 15 to fuel.");
		actuallyAdded = b.addFuel(15);
		System.out.println("Fuel actually added was " + actuallyAdded);
		System.out.println("Fuel is " + b.getFuel() + " expected 20.0.");
		System.out.println("Set max fuel burn rate to 2.");
		b.setMaxBurnRate(2);
		System.out.println("Max fuel burn rate is "
				+ b.getMaxBurnRate() + " expected 2.0.");
		
		System.out.println("\nTest 3:");
		System.out.println("Adjust vertical velocity +2.");
		b.adjustVerticalVelocity(2);
		System.out.println("Adjust horizontal velocity -4.");
		b.adjustHorizontalVelocity(-4);
		System.out.println("Vertical velocity is " + b.getVerticalVelocity() + " expected 2.");
		System.out.println("Horizontal velocity is " + b.getHorizontalVelocity() + " expected -4.");

		System.out.println("\nTest 4:");
		System.out.println("Current time is " + b.getHours() + ":"
				+ b.getMinutes() + ":" + b.getSeconds()
				+ " expected 0:0:0");
		System.out.println("Setting time to 100.");
		b.setTime(100);
		System.out.println("Current time is " + b.getHours() + ":"
				+ b.getMinutes() + ":" + b.getSeconds()
				+ " expected 0:1:40");
		System.out.println("Setting time to 10000.");
		b.setTime(10000);
		System.out.println("Current time is " + b.getHours() + ":"
				+ b.getMinutes() + ":" + b.getSeconds()
				+ " expected 2:46:40");
		
		System.out.println("\nTest 5:");
		System.out.println("Update.");
		double fuelBurned = b.oneSecondUpdate();
		System.out.println("Ground position " + b.getGroundPosition()
				+ " expected 33.");
		System.out.println("Altitude " + b.getAltitude()
				+ " expected 2.");
		System.out.println("Fuel burned " + fuelBurned
				+ " expected 2.0.");
		System.out.println("Current time is " + b.getHours() + ":"
				+ b.getMinutes() + ":" + b.getSeconds()
				+ " expected 2:46:41");
		System.out.println("Horizontal velocity +12 and update.");
		b.adjustHorizontalVelocity(12);
		b.oneSecondUpdate();
		System.out.println("Ground position " + b.getGroundPosition()
				+ " expected 1.");
	}
}
