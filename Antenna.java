package pooproject;
public class Antenna {
    private final Position location;
    private final double coverageRadius;
    private final int capacityLimit;
    private int activeCalls;

    public Antenna(Position location, double coverageRadius, int capacityLimit) {
        this.location = location;
        this.coverageRadius = coverageRadius;
        this.capacityLimit = capacityLimit;
        this.activeCalls = 0;
    }

    public boolean isInRange(Position phoneLocation) {
        return location.distanceTo(phoneLocation) <= coverageRadius;
    }

    public boolean canAcceptNewCall() {
        return activeCalls < capacityLimit;
    }

    public boolean incrementActiveCalls() {
        if (canAcceptNewCall()) {
            activeCalls++;
            return true;
        }
        return false;
    }

    public void decrementActiveCalls() {
        if (activeCalls > 0) {
            activeCalls--;
        }
    }

    public Position getLocation() {
        return location;
    }

    public double getCoverageRadius() {
        return coverageRadius;
    }

    public int getCapacityLimit() {
        return capacityLimit;
    }

    public int getActiveCalls() {
        return activeCalls;
    }
}
