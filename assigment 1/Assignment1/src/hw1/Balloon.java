package hw1;

/**
 * A class that simulates a hot air balloon.
 * <p>
 * This class models the flight of a hot air balloon taking multiple factors into
 * account, such as vertical/horizontal velocity, available fuel, fuel burn rate,
 * time, etc.
 * 
 * @author Yousif Lubbad
 */

public class Balloon {
    /** 
     * The balloon's current horizontal position on the ground.
     * Wraps around when it reaches maxGroundPosition. 
     */
    private int groundPosition;
    
    /** The maximum ground position (exclusive) for wrapping. */
    private int maxGroundPosition;

    /** The maximum altitude the balloon can reach. */
    private int maxAltitude;

    /** The maximum capacity of the balloon’s fuel tank. */
    private double tankCapacity;

    /** The current amount of fuel in the tank. */
    private double fuel;

    /** The maximum rate at which fuel can be burned, in units per second. */
    private double maxBurnRate;

    /** The balloon’s current altitude. */
    private int altitude;

    /** The balloon’s vertical velocity in altitude units per second. */
    private int verticalVelocity;

    /** The balloon’s horizontal velocity in ground-position units per second. */
    private int horizontalVelocity;

    /** The initial ground position set by the constructor, used to restore later. */
    private int initialGroundPosition;

    /** Total elapsed time in seconds since the start of the simulation. */
    private int timeElapsed;

    
    /**
     * Constructs a Balloon object with the given parameters.
     *
     * @param groundPosition    initial position of the balloon relative to the ground
     * @param maxGroundPosition maximum ground position (exclusive) for wrapping
     * @param maxAltitude       maximum altitude the balloon can reach
     * @param tankCapacity      maximum capacity of the fuel tank
     */
    
        public Balloon(int groundPosition, int maxGroundPosition, int maxAltitude, double tankCapacity) {
        this.groundPosition = groundPosition;
        this.maxGroundPosition = maxGroundPosition;
        this.maxAltitude = maxAltitude;
        this.tankCapacity = tankCapacity;
        this.fuel = 0;
        this.maxBurnRate = 0;
        this.altitude = 0;
        this.verticalVelocity = 0;
        this.horizontalVelocity = 0;
        this.initialGroundPosition = groundPosition;
        this.timeElapsed = 0;
    }

    // Accessor methods (getters)
        
        /**
         * Gets the current vertical velocity of the balloon.
         *
         * @return the balloon's current vertical velocity
         */
        
    public int getVerticalVelocity() {
        return verticalVelocity;
    }

    /**
     * Gets the current horizontal velocity of the balloon.
     *
     * @return the balloon's current horizontal velocity
     */
    public int getHorizontalVelocity() {
        return horizontalVelocity;
    }
    /**
     * Gets the current altitude of the balloon.
     *
     * @return the balloon's current altitude
     */

    public int getAltitude() {
        return altitude;
    }
    /**
     * Gets the maximum altitude the balloon can reach.
     *
     * @return the maximum altitude
     */

    public int getMaxAltitude() {
        return maxAltitude;
    }
    /**
     * Gets the current position of the balloon with respect to the ground.
     *
     * @return the balloon's ground position
     */

    public int getGroundPosition() {
        return groundPosition;
    }
    /**
     * Gets the farthest ground position the balloon can travel 
     * before wrapping around (in a circle) to position 0.
     *
     * @return the maximum ground position
     */

    public int getMaxGroundPosition() {
        return maxGroundPosition;
    }
    /**
     * Gets the amount of fuel the balloon has in its tank.
     *
     * @return the current fuel in the tank
     */

    public double getFuel() {
        return fuel;
    }
    /**
     * Gets the maximum capacity of the balloon’s fuel tank.
     *
     * @return the tank's maximum capacity
     */

    public double getTankCapacity() {
        return tankCapacity;
    }
    /**
     * Gets the maximum burn rate for the balloon (fuel units per second).
     *
     * @return the balloon's maximum burn rate
     */

    public double getMaxBurnRate() {
        return maxBurnRate;
    }
    /**
     * Gets the number of seconds past the current minute.
     *
     * @return seconds in the range 0..59
     */

    public int getSeconds() {
        return timeElapsed % 60;
    }
    /**
     * Gets the number of full minutes past the current hour.
     *
     * @return minutes in the range 0..59
     */

    public int getMinutes() {
        return (timeElapsed / 60) % 60;
    }
    /**
     * Gets the number of full hours that have passed.
     *
     * @return hours in the range 0.. (no upper limit)
     */

    public int getHours() {
        return timeElapsed / 3600;
    }

    // -------------------------
    //        Mutators
    // -------------------------

    /**
     * Sets the capacity of the fuel tank to the given parameter.
     *
     * @param tankCapacity the new tank capacity
     */
    
    // Mutator methods (setters)
    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
    /**
     * Sets the maximum burn rate to the given parameter.
     *
     * @param maxBurnRate the new maximum burn rate
     */

    public void setMaxBurnRate(double maxBurnRate) {
        this.maxBurnRate = maxBurnRate;
    }
    /**
     * Sets the total time the simulation has run (in seconds) 
     * to the given parameter.
     *
     * @param time the total elapsed time to set
     */

    public void setTime(int time) {
        this.timeElapsed = time;
    }
    /**
     * Restores the ground position back to where it was set by the constructor.
     */

    public void restoreInitialGroundPosition() {
        this.groundPosition = initialGroundPosition;
    }
    /**
     * Adjusts the balloon's vertical velocity by adding the given delta.
     *
     * @param delta the amount to change the vertical velocity
     */

    public void adjustVerticalVelocity(int delta) {
        verticalVelocity += delta;
    }
    /**
     * Adjusts the balloon's horizontal velocity by adding the given delta.
     *
     * @param delta the amount to change the horizontal velocity
     */

    public void adjustHorizontalVelocity(int delta) {
        horizontalVelocity += delta;
    }
    /**
     * Adds the given amount of fuel to the tank, up to the tank's capacity.
     *
     * @param amount the amount of fuel to be added
     * @return the amount of fuel actually added
     */

    public double addFuel(double amount) {
        double fuelAdded = Math.min(amount, tankCapacity - fuel);
        fuel += fuelAdded;
        return fuelAdded;
    }
    /**
     * Simulates one second of flight.
     * <p>
     * 1) Burns fuel at the maximum burn rate (or whatever is left if less 
     *    than maxBurnRate).<br>
     * 2) Updates altitude by adding vertical velocity, clamping between 0 and 
     *    maxAltitude.<br>
     * 3) Updates groundPosition by adding horizontal velocity, wrapping around 
     *    maxGroundPosition if needed.<br>
     * 4) Increments total time by one second.
     *
     * @return the amount of fuel actually burned in this one-second update
     */


    public double oneSecondUpdate() {
        // Fuel is burned at the maximum rate or whatever is left, whichever is smaller
        double fuelBurned = Math.min(fuel, maxBurnRate);
        fuel = Math.max(0, fuel - fuelBurned);

        // Clamp altitude between 0 and maxAltitude
        altitude = Math.max(0, Math.min(maxAltitude, altitude + verticalVelocity));

        // Wrap ground position in a circle
        groundPosition = ((groundPosition + horizontalVelocity) % maxGroundPosition + maxGroundPosition) % maxGroundPosition;

        // Advance time by one second
        timeElapsed++;

        return fuelBurned;
    }
}