package pooproject;
import java.util.ArrayList;
import java.util.List;

public class Network {
    private final List<Antenna> antennas;

    public Network() {
        antennas = new ArrayList<>();
    }

    // Add antenna only if it overlaps coverage with at least one existing antenna
    public boolean addAntenna(Antenna newAntenna) {
        if (antennas.isEmpty()) {
            antennas.add(newAntenna);
            System.out.println("First antenna added to network.");
            return true;
        }

        for (Antenna existing : antennas) {
            double distance = existing.getLocation().distanceTo(newAntenna.getLocation());
            if (distance <= existing.getCoverageRadius() + newAntenna.getCoverageRadius()) {
                antennas.add(newAntenna);
                System.out.println("Antenna added to network.");
                return true;
            }
        }
        System.out.println("Antenna not added: no coverage overlap with existing antennas.");
        return false;
    }

    // Find nearest antenna to phone location
    public Antenna findNearestAntenna(Position phoneLocation) {
        Antenna nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Antenna antenna : antennas) {
            if (antenna.isInRange(phoneLocation)) {
                double dist = antenna.getLocation().distanceTo(phoneLocation);
                if (dist < minDistance) {
                    minDistance = dist;
                    nearest = antenna;
                }
            }
        }
        return nearest;
    }

    // Find nearest antenna with capacity to accept new call
    public Antenna findNearestAntennaWithCapacity(Position phoneLocation) {
        Antenna nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Antenna antenna : antennas) {
            if (antenna.isInRange(phoneLocation) && antenna.canAcceptNewCall()) {
                double dist = antenna.getLocation().distanceTo(phoneLocation);
                if (dist < minDistance) {
                    minDistance = dist;
                    nearest = antenna;
                }
            }
        }
        return nearest;
    }
}
