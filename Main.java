package pooproject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a network
        Network network = new Network();

        // Add antennas to the network
        Antenna antenna1 = new Antenna(new Position(0, 0), 10, 2);
        Antenna antenna2 = new Antenna(new Position(15, 0), 10, 2);

        network.addAntenna(antenna1);
        network.addAntenna(antenna2);

        // Create SIM cards with initial credit
        SimCard sim1 = new SimCard("123-456-7890", 5.0);
        SimCard sim2 = new SimCard("987-654-3210", 3.0);

        // Create phones at initial positions linked to the network
        Phone phone1 = new Phone(50, sim1, new Position(2, 3), network);
        Phone phone2 = new Phone(60, sim2, new Position(14, 1), network);

        // Phone1 makes a stationary call
        System.out.println("Phone1 attempts stationary call:");
        if (phone1.startStationaryCall()) {
            System.out.println("Phone1 is now on a call.");
        }

        // Phone2 tries to call Phone1 (incoming call)
        System.out.println("\nPhone2 attempts to call Phone1:");
        if (phone1.receiveCall(phone2)) {
            System.out.println("Phone1 accepted the call from Phone2.");
        } else {
            System.out.println("Phone1 rejected the call from Phone2.");
        }

        // Phone1 ends the call
        phone1.endCall();

        // Phone1 makes a moving call along a path
        System.out.println("\nPhone1 attempts moving call:");
        List<Position> path = new ArrayList<>();
        path.add(new Position(3, 3));
        path.add(new Position(5, 5));
        path.add(new Position(11, 0)); // Possibly moves out of coverage

        phone1.startMovingCall(path);

        // Phone1 charges battery
        phone1.chargeBattery(20);
        System.out.println("\nPhone1 battery after charging: " + phone1.getBatteryLevel() + "%");
    }
}
